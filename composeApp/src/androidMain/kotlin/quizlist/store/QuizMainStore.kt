package com.example.quiz.quizList.store

import com.example.quiz.quizList.QuizListItem

internal interface QuizMainStore {

    sealed class Intent {
        object AddItem : Intent()
    }

    data class State(
        val items: List<QuizListItem> = emptyList()
    )

}