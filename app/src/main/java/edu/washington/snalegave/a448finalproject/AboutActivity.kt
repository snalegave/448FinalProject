package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

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
        val fooditem: objects.FoodItem = bundle.getSerializable("name") as objects.FoodItem
        var stringIngredients: String = ""
        stringIngredients += fooditem.ingredients.get(0)
        for(i in fooditem.ingredients) {
            stringIngredients += "," + i
        }
        var stringAllergens: String = ""
        stringAllergens += fooditem.allergens.get(0)
        for(i in fooditem.allergens) {
            stringAllergens += "," + i
        }
        name.setText(fooditem.name)
        type.setText(fooditem.type)
        ingredients.setText(stringIngredients)
        allergens.setText(stringAllergens)
        desc.setText(fooditem.desc)



    }
}
