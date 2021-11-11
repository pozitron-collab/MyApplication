package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val dat=intent.getStringExtra("name")
        val textview:TextView=findViewById(R.id.textView15)
        textview.text=dat

        // creating thank you page
        val showbtntq: Button = findViewById(R.id.thanku)

        showbtntq.setOnClickListener {
            val intent= Intent(this,MainActivity3::class.java).apply {
                putExtra("name","made by sayan")
            }
            startActivity(intent)
        }

    }
}