package edu.washington.snalegave.a448finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import android.widget.Toast


class LogInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        setContentView(R.layout.activity_log_in)
        val inputEmail = findViewById<EditText>(R.id.email2)
        val inputPassword = findViewById<EditText>(R.id.password2)
        val logInButton = findViewById<Button>(R.id.signIn)
        val signUpButton = findViewById<Button>(R.id.signUp)

        auth = FirebaseAuth.getInstance()

        signUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        logInButton.setOnClickListener {
            val email = inputEmail.text.toString().trim()
            val password = inputPassword.text.toString().trim()
            var validEmail = false
            var validPassword = false

            if (email.equals("")) {
                inputEmail.error = "Email cannot be blank"
            } else {
                validEmail = true
            }
            if (password.length < 6) {
                inputPassword.error = "Password cannot be less than 6 characters"
            } else {
                validPassword = true
            }


            if (validEmail && validPassword) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
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
