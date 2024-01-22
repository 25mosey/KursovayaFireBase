package com.example.kursovayafirebase

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class RandomCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    // Обработка нажатия кнопки "назад"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                Log.d("RandomCallActivity", "Navigating back to MainActivity")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}