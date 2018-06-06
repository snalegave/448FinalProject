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

        // this grabs the JSON file from the ROOT DIRECTORY of the phone
        // which is under storage/emulated/0/
        // you can upload the file through View --> Tools Window --> Device File Explorer
        // (idk how to parse it from the assets folder)
        // val restaurant = File(Environment.getExternalStorageDirectory(), "menu.json")
        // val restaurantInput = FileInputStream(restaurant)
        // val input = restaurantInput.bufferedReader().use {it.readText() }

        val input = TEST.bufferedReader().use {it.readText() }

        val questionsJSON = JSONArray(input)

        val restaurants = mutableListOf<Restaurant>()
        for (i in 0..(questionsJSON.length() - 1)) {
            val section = questionsJSON.getJSONObject(i)
            val name = section.getString("restaurant")
            val desc = section.getString("desc")
            val address = section.getString("address")
            val phone = section.getString("phone")
            val items = section.getJSONArray("Menu")

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
            restaurants.add(Restaurant(name, desc, address, phone, menu))
        }

        fun getRestaurants():List<Restaurant> {
            return dataObject.getRestaurantList()
        }


    }


}
data class Restaurant(val name: String,
                      val desc: String,
                      val address: String,
                      val phone: String,
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