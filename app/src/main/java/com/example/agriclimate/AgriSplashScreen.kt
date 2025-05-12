package com.example.agriclimate

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.agriclimate.databinding.ActivityAgriSplashScreenBinding
import com.google.android.material.progressindicator.CircularProgressIndicator

class AgriSplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivityAgriSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAgriSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make the splash screen truly full screen (hide status + nav bars)
        window.decorView.systemUiVisibility = (
                WindowManager.LayoutParams.FLAG_FULLSCREEN or
                        android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                        android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                        android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                )

        binding.progressBar1.visibility = CircularProgressIndicator.VISIBLE

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 7000)
    }
}