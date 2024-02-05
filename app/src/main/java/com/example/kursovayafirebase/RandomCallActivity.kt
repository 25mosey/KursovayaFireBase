package com.example.kursovayafirebase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class RandomCallActivity : AppCompatActivity() {

    private lateinit var textViewForId: TextView
    private lateinit var textViewForName: TextView
    private lateinit var textViewForAddress: TextView
    private lateinit var textViewForProblem: TextView
    private lateinit var btnBack: Button
    private lateinit var request: Request

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        btnBack = findViewById(R.id.btnBack)
        btnBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }


}

