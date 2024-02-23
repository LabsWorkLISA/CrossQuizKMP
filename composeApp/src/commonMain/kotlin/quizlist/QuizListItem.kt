package quizlist

data class QuizListItem(
    val id: Long,
    val name: String,
    val imageUrl: String,
    // TODO separate class
    val tags: List<String>,
    val creatorName: String,
    val startDate: String,
)