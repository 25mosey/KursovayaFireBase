package com.example.kursovayafirebase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RandomCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener(View.OnClickListener {
            finish()
            Log.d("RandomCallActivity", "Navigating back to MainActivity")
        })
    }
}
