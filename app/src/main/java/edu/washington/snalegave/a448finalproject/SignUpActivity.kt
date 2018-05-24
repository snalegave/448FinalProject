package edu.washington.snalegave.a448finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.widget.Toast


class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val auth = FirebaseAuth.getInstance()
        val signUpButton= findViewById<Button>(R.id.signUpButton)
        val inputEmail= findViewById<EditText>(R.id.email)
        val inputPassword= findViewById<EditText>(R.id.password)
        val signInButton= findViewById<Button>(R.id.logInButton)

        signInButton.setOnClickListener{
            startActivity(Intent(this, LogInActivity::class.java))
        }

        signUpButton.setOnClickListener{
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            var validEmail= false
            var validPassword= false

            if(email.equals("")){
                inputEmail.error= "Email cannot be blank"
            } else {
                validEmail = true
            }
            if(password.equals("") || password.length < 6){
                inputPassword.error= "Password cannot be less than 6 characters"
            } else {
                validPassword = true
            }


            if(validEmail && validPassword){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Authentication failed." + it.exception,
                                Toast.LENGTH_SHORT).show()
                    }
                }
            }


        }

    }
}
