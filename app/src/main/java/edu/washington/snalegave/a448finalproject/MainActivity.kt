package edu.washington.snalegave.a448finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseUser




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val profile = findViewById<Button>(R.id.profile)
        val restaurantList = findViewById<Button>(R.id.profile)

        val user = FirebaseAuth.getInstance().currentUser
        val auth = FirebaseAuth.getInstance()



        profile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
            Log.i("mainActivity", "pressed the profile button")
        }
    }
}

