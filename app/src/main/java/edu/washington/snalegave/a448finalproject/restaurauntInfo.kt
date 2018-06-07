package edu.washington.snalegave.a448finalproject

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class restaurauntInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restauraunt_info)

        val intent: Intent = getIntent()
        val bundle: Bundle = intent.getExtras()
        val rest = bundle.getSerializable("restInfo") as Restaurant


        val name = findViewById<TextView>(R.id.name)
        val desc = findViewById<TextView>(R.id.desc)
        val phone = findViewById<TextView>(R.id.call)
        val address = findViewById<TextView>(R.id.address)

        name.setText(rest.name)
        desc.setText("Description: " + rest.desc.toUpperCase())
        address.setText("Address: " + rest.address)
        phone.setText(rest.phone)

        val number = "tel:"+rest.phone

        phone.setOnClickListener() {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse(number)
            startActivity(intent)
        }
    }
}
