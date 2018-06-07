package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val name = findViewById<TextView>(R.id.name)
        val type = findViewById<TextView>(R.id.type)
        val ingredients = findViewById<TextView>(R.id.ingredients)
        val allergens = findViewById<TextView>(R.id.allergens)
        val desc = findViewById<TextView>(R.id.desc)

        val intent: Intent = getIntent()
        val bundle: Bundle = intent.getExtras()
        val fooditem = bundle.getSerializable("name") as FoodItem
        var stringIngredients: String = ""
        stringIngredients += fooditem.ingredients.get(0)
        if(fooditem.ingredients.isEmpty()) {
            stringIngredients = "No Ingredients"
        }
        for(i in 1..fooditem.ingredients.size-1) {
            stringIngredients += ", " + fooditem.ingredients.get(i)
        }
        stringIngredients = "Ingredients: " + stringIngredients

        var stringAllergens: String = ""
        stringAllergens += fooditem.allergens.get(0)
        if(fooditem.allergens.isEmpty()) {
            stringAllergens = "No Allergens"
        }
        for(i in 1..fooditem.allergens.size - 1) {
            stringAllergens += ", " + fooditem.allergens.get(i)
        }



        stringAllergens = "Allergens: " + stringAllergens
        name.setText("Name of Dish: " + fooditem.name)
        type.setText("Type of Dish: " + fooditem.type)
        ingredients.setText(stringIngredients)
        allergens.setText(stringAllergens)
        desc.setText("Description of Dish: " + fooditem.desc)

        val reactionButton = findViewById<Button>(R.id.allergyAlert)
        allergyAlert.setOnClickListener{


        }
    }
}
