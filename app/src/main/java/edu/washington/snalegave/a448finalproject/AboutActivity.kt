package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
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

        val user = FirebaseAuth.getInstance().currentUser

        val reactionButton = findViewById<Button>(R.id.allergyAlert)

        var refForFoodItem: DatabaseReference
        var refForIngredients: DatabaseReference
        val existingFoodItems = mutableListOf<String>()

        if(user == null){
            reactionButton.isEnabled= false
        } else {
            val uid = user.uid
            refForFoodItem = FirebaseDatabase.getInstance().getReference("users/"+uid+"/foodItem")
            refForIngredients = FirebaseDatabase.getInstance().getReference("users/"+uid+"/ingredients")

            refForFoodItem.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (child: DataSnapshot in dataSnapshot.children) {
                        Log.i("dog", "we have    ${child.getValue()}")
                        existingFoodItems.add(child.getValue().toString())

                    }
                    Log.i("dog", "the set:    ${existingFoodItems}")

                }


                override fun onCancelled(error: DatabaseError) {
                    Log.i("AboutActivity", "loadPost:onCancelled ${error.toException()}")
                }
            })

        }


        allergyAlert.setOnClickListener{
            val uid = user!!.uid
            refForFoodItem = FirebaseDatabase.getInstance().getReference("users/"+uid+"/foodItem")
            refForIngredients = FirebaseDatabase.getInstance().getReference("users/"+uid+"/ingredients")

//            val foodItemListener = object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    for (child: DataSnapshot in snapshot.children) {
//                        Log.i("dog", "we have    ${child.getValue()}")
//                    }
//                }
//
//                override fun onCancelled(databaseError: DatabaseError) {
//                    Log.i("AboutActivity", "loadPost:onCancelled ${databaseError.toException()}")
//                }
//            }
//            refForFoodItem.child("foodItem").addListenerForSingleValueEvent(foodItemListener)
//
//
//            refForFoodItem.addListenerForSingleValueEvent(foodItemListener)

            // Add all polls in ref as rows



            if(!existingFoodItems.contains(fooditem.name)){
                Log.i("AboutActivity", existingFoodItems.toString())
                existingFoodItems.add(fooditem.name)
                val newChildRef = refForFoodItem.push()
                newChildRef.setValue(fooditem.name)
                for(i in fooditem.ingredients){
                    val newIngredientRef = refForIngredients.push()
                    newIngredientRef.setValue(i)
                }
            } else{
                Toast.makeText(this, "This food item has been noted.", Toast.LENGTH_SHORT).show()
            }


        }
    }
}
