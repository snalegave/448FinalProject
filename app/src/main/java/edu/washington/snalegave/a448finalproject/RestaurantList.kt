package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class RestaurantList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        val joey = findViewById<Button>(R.id.joeyButton)
        joey.setOnClickListener {
            startActivity(Intent(this, FoodMenu::class.java))
            Log.i("mainActivity", "pressed the profile button")
        }

    }



}
