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
import kotlin.collections.HashSet


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
        Log.i("fod1", foods.toString())

        val intent: Intent = getIntent()
        if (intent.hasExtra("selectedAllergen") &&!intent.getStringArrayListExtra("selectedAllergen").isEmpty()) {
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
        if(intent.hasExtra("selectedDiet") && !intent.getStringArrayListExtra("selectedDiet").isEmpty()) {

            Log.i("u4", "intentExist")
            var FodDietIngredients: MutableSet<String> = hashSetOf("Onions", "Garlic", "Cabbage", "Broccoli",
                    "Cauliflower", "Snow peas", "Asparagus", "Artichokes", "Leeks", "Beetroot", "Celery", "Sweet corn",
                    "Brussels sprouts", "Mushrooms", "Peaches", "Apricots", "Nectarines", "Plums", "Prunes",
                    "Mangoes", "Apples", "Pears", "Watermelon", "Cherries", "Blackberries",
                    "Beans", "lentils", "Wheat", "rye", "Breads", "Cereals", "Pastas", "Crackers", "Pizza", "Milk",
                    "Soft cheese", "Yogurt", "Ice cream", "Custard", "Pudding", "Cottage cheese")

            var NoSugarDietIngredients: MutableSet<String> = hashSetOf("Sugar", "High Fructose Corn Syrup", "Brown Sugar")

            var dietList: ArrayList<String> = intent.getStringArrayListExtra("selectedDiet")

            for (i in 0..(menu.size - 1)) {
                var ingredientList: List<String> = menu.get(i).ingredients
                for (h in 0..(ingredientList.size - 1)) {
                    for(k in 0..(dietList.size -1)) {
                        Log.i("u6", dietList.get(k))
                        if(dietList.get(k).equals("FodMap")) {
                            Log.i("u5", menu.get(i).name)
                            for (s in FodDietIngredients) {
                                if (s.equals(ingredientList.get(h), ignoreCase = true)) {
                                    foods.remove(menu.get(i).name)
                                    Log.i("u2", menu.get(i).name)
                                }
                            }
                        }
                        if(dietList.get(k).equals("No artificially added sugar")){
                            Log.i("u3", menu.get(i).name)
                            for (s in NoSugarDietIngredients) {

                                if (s.equals(ingredientList.get(h), ignoreCase = true)) {
                                    foods.remove(menu.get(i).name)
                                    Log.i("u3", menu.get(i).name)
                                }
                            }
                            }
                        }
                    }
                }
            }

        if (intent.hasExtra("selectedDishType") &&!intent.getStringArrayListExtra("selectedDishType").isEmpty()) {
            var selectedDishList: ArrayList<String> = intent.getStringArrayListExtra("selectedDishType")
            var lowerCaseDish :ArrayList<String> = arrayListOf()
            for(s in selectedDishList) {
                lowerCaseDish.add(s.toLowerCase())
            }
            for (i in 0..(menu.size - 1)) {
                var foodItemType: String = menu.get(i).type
                Log.i("dog", "selectedAllergens: " + selectedDishList)
                if (!lowerCaseDish.contains(foodItemType)) {
                            foods.remove(menu.get(i).name)
                            Log.i("asdf132", "werew" + foods.toString())
                }
            }
        }

        var newMenu: ArrayList<FoodItem> = arrayListOf()
        var newMenu1: ArrayList<FoodItem> = arrayListOf()

        for(i in 0..menu.size - 1) {
            newMenu.add(menu[i])
        }
        Log.i("hey", foods.size.toString())
        Log.i("hey", newMenu.size.toString())
        for (s in 0 until foods.size -1) {
            for(i in 0 until newMenu.size -1) {
                if(foods[s].equals(newMenu[i].name,true)) {
                    newMenu1.add(newMenu[i])
                }
            }
        }


        val listView = findViewById<ListView>(R.id.ListView)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, foods)
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, v, position, id ->
            val intent = Intent(this, AboutActivity::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("name", newMenu1[position])
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val filter = findViewById<Button>(R.id.filterButton)
        filter.setOnClickListener {

            val bundle = Bundle()
            bundle.putSerializable("restaurant", restaurant)
            val intent = Intent(this, AllergyAndDishTypeSelection::class.java).apply{
                putExtra("restaurant", bundle)
            }

            Log.i("foodMenu", restaurant.toString())

            Log.i("mainActivity", "pressed the filter button")
            startActivity(intent)
        }


        val info = findViewById<Button>(R.id.about)
        info.setOnClickListener {
            val intent = Intent(this, restaurauntInfo::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("restInfo", restaurant)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.back)
        backButton.setOnClickListener{
            val intent = Intent(this, RestaurantList::class.java)
            startActivity(intent)
        }
    }
}

