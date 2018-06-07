package edu.washington.snalegave.a448finalproject

import android.app.Application
import android.os.Environment
import android.util.Log
import org.json.JSONArray
import java.io.BufferedReader
import java.io.File
import java.io.Serializable
import java.util.ArrayList

class MenuData: Application(){
    lateinit var dataObject: MenuObject

    override fun onCreate() {
        super.onCreate()

        val TEST = applicationContext.getAssets().open("menu.json")

        val input = TEST.bufferedReader().use {it.readText() }
        dataObject = JsonMenuData(input)

    }
    fun getRestaurants():List<Restaurant> {
        return dataObject.getRestaurantList()
    }

}

class JsonMenuData (JsonFile:String):MenuObject{
    val restaurants = mutableListOf<Restaurant>()

    init {

        val questionsJSON = JSONArray(JsonFile)

        for (i in 0..(questionsJSON.length() - 1)) {
            val section = questionsJSON.getJSONObject(i)
            val name = section.getString("restaurant")
            val desc = section.getString("desc")
            val address = section.getString("address")
            val phone = section.getString("phone")
            val allergenArray = section.getJSONArray("allergens")
            val dishTypeArray = section.getJSONArray("dishType")
            val items = section.getJSONArray("menu")


            val allergenList = mutableListOf<String>()
            for (i in 0..(allergenArray.length() - 1)) {
                allergenList.add(allergenArray[i].toString())
            }
            val dishTypeList= mutableListOf<String>()
            for (i in 0..(dishTypeArray.length() - 1)) {
                dishTypeList.add(dishTypeArray[i].toString())
            }
            // loop that iterates over every item on the food menu
            val menu = mutableListOf<FoodItem>()
            for (j in 0..(items.length() - 1)) {
                val menuSection = items.getJSONObject(j)
                val foodName = menuSection.getString("name")
                val type = menuSection.getString("type")

                val ingredientsString = menuSection.getString("ingredients")
                val ingredients = (ingredientsString.split(", "))

                val allergensString = menuSection.getString("allergens")
                val allergens = (allergensString.split(", "))

                val description = menuSection.getString("description")

                menu.add(FoodItem(foodName, type, ingredients, allergens, description))
            }
            restaurants.add(Restaurant(name, desc, address, phone, allergenList, dishTypeList, menu))
        }
    }

    override fun getRestaurantList(): List<Restaurant> {
        return restaurants
    }

}
data class Restaurant(val name: String,
                      val desc: String,
                      val address: String,
                      val phone: String,
                      val allergenList: List<String>,
                      val dishTypeList: List<String>,
                      val menu: MutableList<FoodItem>) : Serializable

// food item domain object
// INGREDIENTS AND ALLERGENS are stored as a LIST for sorting
data class FoodItem(val name: String,
                    val type: String,
                    val ingredients: List<String>,
                    val allergens: List<String>,
                    val desc: String) : Serializable

interface MenuObject {
    fun  getRestaurantList(): List<Restaurant>
}