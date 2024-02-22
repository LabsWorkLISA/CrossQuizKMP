package quizlist

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow

class QuizListComponentImpl(
    componentContext: ComponentContext,
): QuizListComponent, ComponentContext by componentContext {

    override val state: StateFlow<QuizListState>
        get() = TODO("Not yet implemented")

    override fun onQuizClick(quiz: QuizListItem) {
        TODO("Not yet implemented")
    }

    override fun onRetryButtonClick() {
        TODO("Not yet implemented")
    }
}