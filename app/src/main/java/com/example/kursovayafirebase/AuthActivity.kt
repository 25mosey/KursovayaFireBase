package com.example.kursovayafirebase

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var rememberMeCheckbox: CheckBox
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        mAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)
        rememberMeCheckbox = findViewById(R.id.checkBoxRememberMe)

        // Загрузка состояния "запомнить меня" при запуске
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val rememberMe = sharedPreferences.getBoolean("rememberMe", false)
        rememberMeCheckbox.isChecked = rememberMe

        // Если флажок "Запомнить меня" установлен и есть сохраненные email и password, выполнить автоматический вход
        if (rememberMe) {
            val savedEmail = sharedPreferences.getString("email", "")
            val savedPassword = sharedPreferences.getString("password", "")

            if (!savedEmail.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
                emailEditText.setText(savedEmail)
                passwordEditText.setText(savedPassword)
            }

        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Сохранение состояния "запомнить меня"
            val editor = sharedPreferences.edit()
            editor.putBoolean("rememberMe", rememberMeCheckbox.isChecked)

            // Если флажок "Запомнить меня" установлен и введены непустые email и password, выполнить вход
            if (rememberMeCheckbox.isChecked && email.isNotEmpty() && password.isNotEmpty()) {
                editor.putString("email", email)
                editor.putString("password", password)
                loginUser(email, password)
            } else {
               loginUser(email, password)
            }

            editor.apply()
        }
    }

    private fun loginUser(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@AuthActivity, "Вход успешен", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@AuthActivity, "Ошибка входа: " + task.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
    // Добавлен метод для выхода из аккаунта и очистки сохраненных данных
    private fun logoutUser() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("email")
        editor.remove("password")
        editor.apply()

        mAuth.signOut()

        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
