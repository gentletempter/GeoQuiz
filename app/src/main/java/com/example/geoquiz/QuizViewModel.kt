package com.example.geoquiz

import androidx.lifecycle.ViewModel

private const val CHEATS_LIMIT = 3

class QuizViewModel : ViewModel() {

    var currentIndex = 0
    var cheatsLeft = CHEATS_LIMIT

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, cheated = false),
        Question(R.string.question_africa, answer = false, cheated = false),
        Question(R.string.question_americas, answer = true, cheated = false),
        Question(R.string.question_asia, answer = false, cheated = false),
        Question(R.string.question_mideast, answer = true, cheated = false)
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