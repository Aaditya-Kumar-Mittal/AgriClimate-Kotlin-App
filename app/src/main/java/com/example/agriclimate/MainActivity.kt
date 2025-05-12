package com.example.agriclimate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.agriclimate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fileName = "login_details"

    // ✅ Store data in SharedPreferences
    private fun storeDataUsingSharedPreferences(username: String, password: String) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString("username", username)
            putString("password", password)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sharedPreferences: SharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")

        // ✅ Auto-login if credentials exist
        if (!savedUsername.isNullOrEmpty() && !savedPassword.isNullOrEmpty()) {
            val intent = Intent(this, AgriHomeScreen::class.java)
            startActivity(intent)
            finish()
            return
        }

        // ✅ Show login screen
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Handle login button click
        binding.loginButton1.setOnClickListener {
            val username = binding.inputUsername1.text.toString().trim()
            val password = binding.inputPassword1.text.toString().trim()
            val saveLoginDetails = binding.saveLoginDetails1.isChecked

            // ✅ Show error if empty
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Save credentials if checkbox is checked
            if (saveLoginDetails) {
                storeDataUsingSharedPreferences(username, password)
            }

            // ✅ Proceed to next screen
            val intent = Intent(this, AgriHomeScreen::class.java)
            startActivity(intent)
            finish()
        }
    }
}
