package com.example.geoquiz

import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {

    var currentIndex = 0

    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, false, false),
        Question(R.string.question_mideast, true, false)
    )

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    var currentQuestionCheat: Boolean
        get() = questionBank[currentIndex].cheated
        set(value) {
            questionBank[currentIndex].cheated = value
        }

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}