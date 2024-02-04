package com.example.kursovayafirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//class RequestDetailsActivity : AppCompatActivity() {
//
//    private lateinit var binding: ActivityRequestDetailsBinding
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityRequestDetailsBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Получение данных из Intent
//        val request: Request? = intent.getParcelableExtra("request")
//
//        // Отображение подробностей в вашем макете
//        request?.let {
//            binding.textViewId.text = it.id
//            binding.textViewName.text = it.nameLiver
//            binding.textViewAddress.text = it.address
//            binding.textViewProblem.text = it.issue
//            // Добавьте другие View для отображения дополнительных данных
//        }
//    }
//}