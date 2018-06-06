package edu.washington.snalegave.a448finalproject

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import org.json.JSONArray
import java.io.File
import java.io.FileInputStream

import java.io.Serializable
import java.util.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class FoodMenu : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu)




        val TEST = applicationContext.getAssets().open("menu.json")
        val restaurants = ArrayList<objects.Restaurant>()

        // this grabs the JSON file from the ROOT DIRECTORY of the phone
        // which is under storage/emulated/0/
        // you can upload the file through View --> Tools Window --> Device File Explorer
        // (idk how to parse it from the assets folder)
        // val restaurant = File(Environment.getExternalStorageDirectory(), "menu.json")
        // val restaurantInput = FileInputStream(restaurant)
        // val input = restaurantInput.bufferedReader().use {it.readText() }

        val input = TEST.bufferedReader().use {it.readText() }

        val questionsJSON = JSONArray(input)


        for (i in 0..(questionsJSON.length() - 1)) {
            val section = questionsJSON.getJSONObject(i)
            val name = section.getString("restaurant")
            val desc = section.getString("desc")
            val address = section.getString("address")
            val phone = section.getString("phone")
            val items = section.getJSONArray("Menu")

            // loop that iterates over every item on the food menu
            val menu = arrayListOf<objects.FoodItem>()
            for (j in 0..(items.length() - 1)) {
                val menuSection = items.getJSONObject(j)
                val foodName = menuSection.getString("name")
                val type = menuSection.getString("type")

                val ingredientsString = menuSection.getString("ingredients")
                val ingredients = (ingredientsString.split(", "))

                val allergensString = menuSection.getString("allergens")
                val allergens = (allergensString.split(", "))

                val description = menuSection.getString("description")

                menu.add(objects.FoodItem(foodName, type, ingredients, allergens, description))
            }
            restaurants.add(objects.Restaurant(name, desc, address, phone, menu))
        }


        var foods:ArrayList<String> = ArrayList()
        for (i in 0..(restaurants.get(0).menu.size - 1)) {



            foods.add(restaurants.get(0).menu.get(i).name)
        }

        // HOW TO RETRIEVE THE DATA :
        // restaurants.get(0).name would return JOEY KITCHEN
        // restaurants.get(0).menu.get(0).name would return SUSHI CONE
        // restaurants.get(0).menu.get(1).name would return GYOZA


        val listView = findViewById<ListView>(R.id.ListView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, foods)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, v, position, id ->
            val intent = Intent(this, AboutActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("name", restaurants.get(0).menu.get(position))
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val filter = findViewById<Button>(R.id.filterButton)
        filter.setOnClickListener {
            startActivity(Intent(this, AllergyAndDishTypeSelection::class.java))
            Log.i("mainActivity", "pressed the profile button")
        }

    }
}

// interface to store multiple restaurant / menu items
interface objects {
    // restaurant domain object
    // serializable so these objects can pass through intent if needed through type serializable
    data class Restaurant(val name: String,
                          val desc: String,
                          val address: String,
                          val phone: String,    
                          val menu: ArrayList<FoodItem>) : Serializable

    // food item domain object
    // INGREDIENTS AND ALLERGENS are stored as a LIST for sorting
    data class FoodItem(val name: String,
                        val type: String,
                        val ingredients: List<String>,
                        val allergens: List<String>,
                        val desc: String) : Serializable

}