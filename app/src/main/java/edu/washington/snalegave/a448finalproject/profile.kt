package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val logOut = findViewById<Button>(R.id.logOutButton)
        val displayEmail = findViewById<TextView>(R.id.displayEmail)


        val frequency = findViewById<TextView>(R.id.frequency)
        val generateFreq = findViewById<Button>(R.id.generateFreq)
        generateFreq.isEnabled=false

        val user = FirebaseAuth.getInstance().currentUser
        val auth = FirebaseAuth.getInstance()


        logOut.setOnClickListener{
            if(user!=null) {
                auth.signOut()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LogInActivity::class.java))
                finish()
            }
        }

        var refForIngredients: DatabaseReference
        val ingredientsFreq = mutableMapOf<String, Int>()


        if (user != null) {
            displayEmail.text = "welcome, " + user.email
            logOut.text = "Log Out"
            generateFreq.isEnabled = true

            val uid = user.uid
            refForIngredients = FirebaseDatabase.getInstance().getReference("users/"+uid+"/ingredients")

            refForIngredients.addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    for (child: DataSnapshot in dataSnapshot.children) {
                        val item = child.getValue().toString().toLowerCase()
                        if (ingredientsFreq.containsKey(item)){
                            var freq = ingredientsFreq[item]!!
                            ingredientsFreq.set(item, freq++ )
                        } else {
                            ingredientsFreq.set(item, 1)
                        }
                    }
                    Log.i("profile", "wawaw:   "+ingredientsFreq.toString())

                }


                override fun onCancelled(error: DatabaseError) {
                    Log.i("AboutActivity", "loadPost:onCancelled ${error.toException()}")
                }
            })

        } else {
            logOut.text = "Sign In"
            frequency.text= ""
        }

        generateFreq.setOnClickListener{
            val sorted = ingredientsFreq.toList().sortedBy { (_, value) -> value}.toMap()
            var flag = false
            for(i in sorted.keys){
                if (sorted[i]!!>1){
                    frequency.text = i + ": " + sorted[i]
                    flag = true
                }
            }
            if (flag == false){
            frequency.text= "We need more data to determine if you have any allergies"
        }
        }
    }
}
