package com.example.myflashcardapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.myflashcardapp.R

class ReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val score = intent.getIntExtra("SCORE", 0) // Changed to getIntExtra
        val questions = intent.getStringArrayExtra("QUESTIONS") // Assuming questions are strings
        val answers = intent.getBooleanArrayExtra("ANSWERS")
        val userAnswers = intent.getBooleanArrayExtra("USER_ANSWERS")

        val scoreText = findViewById<TextView>(R.id.resultText)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        scoreText.text = "Your score: $score / ${questions?.size ?: 0}" // Used elvis operator for null safety

        feedbackText.text = when {
            score >= (questions?.size?.div(2) ?: 0) -> "Great job!" // Adjusted feedback logic
            else -> "Keep practicing!"
        }

        reviewButton.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java) // Corrected intent creation
            intent.putExtra("QUESTIONS", questions)
            intent.putExtra("USER_ANSWERS", userAnswers)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity() // Exits the app
        }
    }
}



