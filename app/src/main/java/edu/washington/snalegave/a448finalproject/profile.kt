package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val logOut = findViewById<Button>(R.id.logOutButton)
        val displayEmail = findViewById<TextView>(R.id.displayEmail)


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

        if (user != null) {
            displayEmail.text = "welcome, " + user.email
            logOut.text = "Log Out"

        } else {
            logOut.text = "Sign In"
        }

    }
}
