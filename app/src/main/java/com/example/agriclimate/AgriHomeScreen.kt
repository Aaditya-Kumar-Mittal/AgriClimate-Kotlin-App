package com.example.agriclimate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.agriclimate.databinding.ActivityAgriHomeScreenBinding

class AgriHomeScreen : AppCompatActivity() {
    private lateinit var binding: ActivityAgriHomeScreenBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private val fileName = "login_details"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAgriHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set toolbar
        setSupportActionBar(binding.toolbar1)

        // Enable toggle button (hamburger menu)
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout1, binding.toolbar1, R.string.open, R.string.close)
        binding.drawerLayout1.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Get stored username from SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "User")

        // Set toolbar title dynamically
        supportActionBar?.title = "Welcome, $username!"

        // Set username in NavigationView header
        val headerView = binding.navigationView2.getHeaderView(0)
        val navUsernameTextView = headerView.findViewById<TextView>(R.id.nav_username)
        navUsernameTextView.text = username

        // Load AboutUsFragment by default
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer1, AgriAboutUs())
                .commit()
        }


        // Navigation item selection listener
        binding.navigationView2.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> replaceFragment(AgriAboutUs())
                R.id.nav_crop_guidelines -> replaceFragment(CropGuidelines())
                R.id.nav_irrigation -> replaceFragment(IrrigationPractices())
                R.id.nav_crop_selection -> replaceFragment(CropSelection())
                R.id.nav_about -> replaceFragment(AgriAboutUs())
                R.id.nav_rate_us -> replaceFragment(AgriRateUse())
                R.id.nav_logout -> {
                    sharedPreferences.edit {
                        clear()
                        apply()
                    }
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            binding.drawerLayout1.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer1, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item)
    }
}
