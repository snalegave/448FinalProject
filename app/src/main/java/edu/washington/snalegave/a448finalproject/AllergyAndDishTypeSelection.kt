package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import com.google.android.flexbox.FlexboxLayout

class AllergyAndDishTypeSelection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergy_and_dish_type_selection)


        val bundle: Bundle = intent.getBundleExtra("restaurant")
        val restaurant = bundle.getSerializable("restaurant") as Restaurant

        val submitButton = findViewById(R.id.submitButton) as Button

        val allergenChecklist = findViewById(R.id.allergensCheckList) as FlexboxLayout

        val allergenCheckBox: CheckBox
        val allergenList = restaurant.allergenList



        //Array Containg all of the checkBoxs for allergens
        val arrayOfAllergenCheckBox = arrayListOf<CheckBox>()
        //Array Containg selected Allergens
        val selectedAllergen = arrayListOf<String>()
        for (i in 0 until allergenList.size) {
            var allergenCheckBox = CheckBox(this)
            allergenCheckBox.setId(i)
            allergenCheckBox.setText(allergenList.get(i))
            allergenCheckBox.setTag(allergenList.get(i))
            allergenCheckBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
                    //If the checkbox is selected, add Allergen to selected allergen List
                    if (b) {
                        selectedAllergen.add(compoundButton.getText().toString())
                    } else {
                        //If the checkbox is not selected, remove Allergen from selected allergen List
                        selectedAllergen.remove(compoundButton.getText().toString())
                    }
                }
            })
            arrayOfAllergenCheckBox.add(allergenCheckBox)
            allergenChecklist.addView(allergenCheckBox)
        }


        val dishTypeChecklist = findViewById(R.id.dishTypeCheckList) as FlexboxLayout
        val dishTypeCheckBox: CheckBox
        val dishTypeList = restaurant.dishTypeList



        //Array Containing all of the checkBoxs for dishTypes
        val arrayOfDishTypeCheckBox = arrayListOf<CheckBox>()
        //Array Containing selected dishTypes
        val selectedDishType = arrayListOf<String>()
        for (i in 0 until dishTypeList.size) {
            var dishTypeCheckBox = CheckBox(this)
            dishTypeCheckBox.setId(i)
            dishTypeCheckBox.setText(dishTypeList.get(i))
            dishTypeCheckBox.setTag(dishTypeList.get(i))
            dishTypeCheckBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(compoundButton: CompoundButton, b: Boolean) {
                    //If the checkbox is selected, add dish type to selected dish type List
                    if (b) {
                        selectedDishType.add(compoundButton.getText().toString())
                    } else {
                        //If the checkbox is not selected, remove dish type from selected dish type List
                        selectedDishType.remove(compoundButton.getText().toString())
                    }
                }
            })
            arrayOfDishTypeCheckBox.add(dishTypeCheckBox)
            dishTypeChecklist.addView(dishTypeCheckBox)
        }


        submitButton.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("restaurant", restaurant)

            val intent = Intent(this, FoodMenu::class.java).apply {
                putExtra("selectedAllergen", selectedAllergen)
                putExtra("selectedDishType", selectedDishType)
                putExtra("restaurant",bundle)
            }
            startActivity(intent)

        }
    }
}


        //        val milkCheckBox = findViewById(R.id.milkCheckBox) as CheckBox
//        val eggCheckBox = findViewById(R.id.eggCheckBox) as CheckBox
//        val fishCheckBox = findViewById(R.id.fishCheckBox) as CheckBox
//        val shellFishCheckBox = findViewById(R.id.shellFishCheckBox) as CheckBox
//        val wheatCheckBox = findViewById(R.id.wheatCheckBox) as CheckBox
//        val soyCheckBox = findViewById(R.id.soyCheckBox) as CheckBox
//        val peanutCheckBox = findViewById(R.id.peanutCheckBox) as CheckBox
//        val glutenCheckBox = findViewById(R.id.glutenCheckBox) as CheckBox
//
//
//        val appetizerCheckBox = findViewById(R.id.appetizerCheckBox) as CheckBox
//        val soupCheckBox = findViewById(R.id.soupCheckBox) as CheckBox
//        val saladCheckBox = findViewById(R.id.saladCheckBox) as CheckBox
//        val sandwichCheckBox = findViewById(R.id.sandwichCheckBox) as CheckBox
//        val wokAndSauteeCheckBox = findViewById(R.id.wokAndSauteeCheckBox) as CheckBox
//        val steakCheckBox = findViewById(R.id.steakCheckBox) as CheckBox
//        val entreeCheckBox = findViewById(R.id.entreeCheckBox) as CheckBox
//        val dessertCheckBox = findViewById(R.id.dessertCheckBox) as CheckBox
//        val sideCheckBox = findViewById(R.id.sideCheckBox) as CheckBox


//            intent.putExtra("milkCheckBox", milkCheckBox.isChecked)
//            intent.putExtra("eggCheckBox", eggCheckBox.isChecked)
//            intent.putExtra("fishCheckBox", fishCheckBox.isChecked)
//            intent.putExtra("shellFishCheckBox", shellFishCheckBox.isChecked)
//            intent.putExtra("wheatCheckBox", wheatCheckBox.isChecked)
//            intent.putExtra("soyCheckBox", soyCheckBox.isChecked)
//            intent.putExtra("peanutCheckBox", peanutCheckBox.isChecked)
//            intent.putExtra("glutenCheckBox", glutenCheckBox.isChecked)
//
//            intent.putExtra("appetizerCheckBox", appetizerCheckBox.isChecked)
//            intent.putExtra("soupCheckBox", soupCheckBox.isChecked)
//            intent.putExtra("saladCheckBox", saladCheckBox.isChecked)
//            intent.putExtra("sandwichCheckBox", sandwichCheckBox.isChecked)
//            intent.putExtra("wokAndSauteeCheckBox", wokAndSauteeCheckBox.isChecked)
//            intent.putExtra("steakCheckBox", steakCheckBox.isChecked)
//            intent.putExtra("entreeCheckBox", entreeCheckBox.isChecked)
//            intent.putExtra("dessertCheckBox", dessertCheckBox.isChecked)
//            intent.putExtra("sideCheckBox", sideCheckBox.isChecked)






