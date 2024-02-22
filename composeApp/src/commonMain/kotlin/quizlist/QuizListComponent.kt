package quizlist

import kotlinx.coroutines.flow.StateFlow

interface QuizListComponent {

    val state: StateFlow<QuizListState>

    fun onQuizClick(quiz: QuizListItem)

    fun onRetryButtonClick()
}