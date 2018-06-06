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


class FoodMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu)


        val bundle: Bundle = intent.getBundleExtra("restaurant")

        val restaurant = bundle.getSerializable("restaurant") as Restaurant

        val menu = restaurant.menu


        var foods: ArrayList<String> = ArrayList()


        for (i in 0..(menu.size - 1)) {

            foods.add(menu[i].name)

        }


        val intent: Intent = getIntent()
        if (intent.hasExtra("selectedAllergen")) {
            var allergensList: ArrayList<String> = intent.getStringArrayListExtra("selectedAllergen")
            for (i in 0..(menu.size - 1)) {
                var foodItemAllergenList: List<String> = menu.get(i).allergens
                Log.i("dog", "allergens: " + menu.get(i).allergens.toString())
                Log.i("dog", "selectedAllergens: " + allergensList)
                for (j in 0..foodItemAllergenList.size - 1) {
                    for (k in 0..allergensList.size - 1) {
                        if (foodItemAllergenList.get(j).equals(allergensList.get(k), true)) {
                            foods.remove(menu.get(i).name)

                            Log.i("asdf132", "werew" + foods.toString())

                        }
                        Log.i("asdfasdf", "test")
                    }
                }
            }
        }


            Log.i("dog", "after:" + foods.size.toString())


            val listView = findViewById<ListView>(R.id.ListView)
            val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, foods)
            listView.adapter = adapter
            listView.setOnItemClickListener { parent, v, position, id ->
                val intent = Intent(this, AboutActivity::class.java)
                val bundle: Bundle = Bundle()
                bundle.putSerializable("name", menu[position])
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

