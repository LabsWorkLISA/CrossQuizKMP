package quizlist

sealed interface QuizListState {
    data class Data(val items: List<QuizListItem>): QuizListState
    data class Error(val errorText: String): QuizListState
    data object Loading: QuizListState
}