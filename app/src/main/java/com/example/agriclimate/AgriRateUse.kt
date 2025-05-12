package com.example.agriclimate

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class AgriRateUse : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var ratingBar: RatingBar
    private lateinit var ratingTextView: TextView

    private val sharedPrefFile = "rating_pref"
    private val ratingKey = "user_rating"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agri_rate_use, container, false)

        // Initialize views
        ratingBar = view.findViewById(R.id.ratingBarOne)
        ratingTextView = view.findViewById(R.id.ratingTextView)

        // Setup SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        // Load saved rating
        val savedRating = sharedPreferences.getFloat(ratingKey, 0f)
        ratingBar.rating = savedRating
        ratingTextView.text = "Your Rating: ${savedRating.toInt()}"

        // Set rating change listener
        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            ratingTextView.text = "Your Rating: ${rating.toInt()}"
            sharedPreferences.edit().putFloat(ratingKey, rating).apply()
            Toast.makeText(requireContext(), "Thanks for rating us $rating stars!", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
