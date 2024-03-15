package com.example.quiz.quizList

data class QuizListItem(
    val id: Long = 0L,
    val order: Long = 0L,
    val title: String = "",
    val imageUrl: String = "",
    val themes: List<String> = listOf(),
    val creatorName: String = "",
    val startDate: String = ""
)