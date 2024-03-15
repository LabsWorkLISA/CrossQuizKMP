package com.example.quiz.dialog

import com.arkivanov.decompose.value.Value
import com.example.quiz.quizList.QuizListItem

interface QuizMainDialog {
    val models: Value<Model>

    fun onBackClicked()

    fun onNextClicked()

    fun onDialogDismissed()

    fun onSetupPicked()

    fun onTitleTextChanged()

    fun onThemePicked()

    fun onThemeCreateClicked()

    fun onQuizCreateClicked()

    data class Model(
        val quiz: QuizListItem
    )

    sealed class Output {
        data class Selected(val id: Long) : Output()
    }
}