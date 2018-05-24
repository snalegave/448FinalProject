package edu.washington.snalegave.a448finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseUser




class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val logIn = findViewById<Button>(R.id.logInButton)
        val signUp = findViewById<Button>(R.id.signUpButton)
        val logOut = findViewById<Button>(R.id.logOutButton)
        val user = FirebaseAuth.getInstance().currentUser
        val auth = FirebaseAuth.getInstance()

        logOut.isEnabled = false;
        logIn.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
        }

        signUp.setOnClickListener{
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        logOut.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if (user != null) {
            displayEmail.text = "welcome, " + user.email
            logIn.isEnabled = false
            signUp.isEnabled = false
            logOut.isEnabled = true

        }

    }
}

