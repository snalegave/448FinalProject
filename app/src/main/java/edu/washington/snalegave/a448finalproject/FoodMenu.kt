package edu.washington.snalegave.a448finalproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView

class FoodMenu : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu)
        var foods:ArrayList<String> = ArrayList()
        foods.add("dog")
        foods.add("cat")
        foods.add("fish")
        val listView = findViewById<ListView>(R.id.ListView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, foods)
        listView.adapter = adapter



    }
}