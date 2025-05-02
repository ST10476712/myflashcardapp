package com.example.myflashcardapp
// ... rest of your QuizActivity code

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myflashcardapp.R
import com.example.myflashcardapp.ReviewActivity

class QuizActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "The Great Wall of China is in Japan.",
        "World War II ended in 1945.",
        "Julius Caesar was a Roman Emperor.",
        "The Berlin Wall fell in 1989."
    )

    private val answers = arrayOf(true, false, true, false, true)
    private var currentIndex = 0
    private var score = 0
    private val userAnswers = mutableListOf<Boolean>()

    private lateinit var questionText: TextView
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var feedbackText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        questionText = findViewById(R.id.questionText)
        trueButton = findViewById(R.id.trueButton)
        falseButton = findViewById(R.id.falseButton)
        nextButton = findViewById(R.id.nextButton)
        feedbackText = findViewById(R.id.feedbackText)

        showQuestion()

        trueButton.setOnClickListener { checkAnswer(true) }
        falseButton.setOnClickListener { checkAnswer(false) }
        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
            } else {
                val intent = Intent(this, ReviewActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWERS", answers)
                intent.putExtra("USER_ANSWERS", userAnswers.toBooleanArray())
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        questionText.text = questions[currentIndex]
        feedbackText.text = ""
        trueButton.isEnabled = true
        falseButton.isEnabled = true
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = answers[currentIndex]
        if (userAnswer == correctAnswer) {
            feedbackText.text = "Correct!"
            score++
        } else {
            feedbackText.text = "Incorrect!"
        }
        userAnswers.add(userAnswer)
        trueButton.isEnabled = false
        falseButton.isEnabled = false 
    }
}