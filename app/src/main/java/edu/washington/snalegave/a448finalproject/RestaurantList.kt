package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class RestaurantList : AppCompatActivity() {

    private lateinit var app: MenuData
    private lateinit var restaurantList: List<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        app = application as MenuData
        restaurantList = app.getRestaurants()

        var arrayOfRestaurantNames: Array<String> = Array(restaurantList.size, { "" })
        for(i in 0..arrayOfRestaurantNames.size-1){
            arrayOfRestaurantNames[i]=restaurantList[i].name
        }


        var listView = findViewById<ListView>(R.id.RestaurantList)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOfRestaurantNames)

        listView.adapter = adapter

        listView.setOnItemClickListener{_, _, position, _ ->
            val bundle = Bundle()
            bundle.putSerializable("restaurant", restaurantList[position])

            val intent= Intent(this, FoodMenu::class.java).apply{
                putExtra("restaurant", bundle)
            }
            startActivity(intent)

        }
    }
}
