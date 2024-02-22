package quizlist

import com.arkivanov.decompose.ComponentContext

class QuizListComponentImpl(
    componentContext: ComponentContext,
): QuizListComponent, ComponentContext by componentContext {
}