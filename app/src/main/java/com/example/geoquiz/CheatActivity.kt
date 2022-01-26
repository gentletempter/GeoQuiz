package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

const val EXTRA_ANSWER_SHOWN = "com.example.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
private const val KEY_INDEX = "com.example.geoquiz.cheat_index"

class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var sdkVersionTextView: TextView
    private var answerIsTrue = false

    private val cheatViewModel: CheatViewModel by lazy {
        ViewModelProvider(this).get(CheatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        val cheatAnswer = savedInstanceState?.getBoolean(KEY_INDEX, false) ?: false

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        sdkVersionTextView = findViewById(R.id.sdk_version_text_view)
        cheatViewModel.cheatAnswer = cheatAnswer

        "API Level ${Build.VERSION.SDK_INT}".also { sdkVersionTextView.text = it }

        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }
        if (cheatAnswer) setAnswerShownResult(true)
    }

    override fun onSaveInstanceState(savedInsatnceState: Bundle) {
        super.onSaveInstanceState(savedInsatnceState)
        savedInsatnceState.putBoolean(KEY_INDEX, cheatViewModel.cheatAnswer)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
        cheatViewModel.cheatAnswer = true
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}