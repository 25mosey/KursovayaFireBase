package com.example.kursovayafirebase

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RequestDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_details)

        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }

}