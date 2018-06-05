package edu.washington.snalegave.a448finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast

class AllergyAndDishTypeSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergy_and_dish_type_selection)

        val submitButton = findViewById(R.id.submitButton) as Button

        val milkCheckBox = findViewById(R.id.milkCheckBox) as CheckBox
        val eggCheckBox = findViewById(R.id.eggCheckBox) as CheckBox
        val fishCheckBox = findViewById(R.id.fishCheckBox) as CheckBox
        val shellFishCheckBox = findViewById(R.id.shellFishCheckBox) as CheckBox
        val wheatCheckBox = findViewById(R.id.wheatCheckBox) as CheckBox
        val soyCheckBox = findViewById(R.id.soyCheckBox) as CheckBox
        val peanutCheckBox = findViewById(R.id.peanutCheckBox) as CheckBox
        val glutenCheckBox = findViewById(R.id.glutenCheckBox) as CheckBox


        val appetizerCheckBox = findViewById(R.id.appetizerCheckBox) as CheckBox
        val soupCheckBox = findViewById(R.id.soupCheckBox) as CheckBox
        val saladCheckBox = findViewById(R.id.saladCheckBox) as CheckBox
        val sandwichCheckBox = findViewById(R.id.sandwichCheckBox) as CheckBox
        val wokAndSauteeCheckBox = findViewById(R.id.wokAndSauteeCheckBox) as CheckBox
        val steakCheckBox = findViewById(R.id.steakCheckBox) as CheckBox
        val entreeCheckBox = findViewById(R.id.entreeCheckBox) as CheckBox
        val dessertCheckBox = findViewById(R.id.dessertCheckBox) as CheckBox
        val sideCheckBox = findViewById(R.id.sideCheckBox) as CheckBox


        submitButton.setOnClickListener {

            intent.putExtra("milkCheckBox", milkCheckBox.isChecked)
            intent.putExtra("eggCheckBox", eggCheckBox.isChecked)
            intent.putExtra("fishCheckBox", fishCheckBox.isChecked)
            intent.putExtra("shellFishCheckBox", shellFishCheckBox.isChecked)
            intent.putExtra("wheatCheckBox", wheatCheckBox.isChecked)
            intent.putExtra("soyCheckBox", soyCheckBox.isChecked)
            intent.putExtra("peanutCheckBox", peanutCheckBox.isChecked)
            intent.putExtra("glutenCheckBox", glutenCheckBox.isChecked)

            intent.putExtra("appetizerCheckBox", appetizerCheckBox.isChecked)
            intent.putExtra("soupCheckBox", soupCheckBox.isChecked)
            intent.putExtra("saladCheckBox", saladCheckBox.isChecked)
            intent.putExtra("sandwichCheckBox", sandwichCheckBox.isChecked)
            intent.putExtra("wokAndSauteeCheckBox", wokAndSauteeCheckBox.isChecked)
            intent.putExtra("steakCheckBox", steakCheckBox.isChecked)
            intent.putExtra("entreeCheckBox", entreeCheckBox.isChecked)
            intent.putExtra("dessertCheckBox", dessertCheckBox.isChecked)
            intent.putExtra("sideCheckBox", sideCheckBox.isChecked)


        }





    }



}
