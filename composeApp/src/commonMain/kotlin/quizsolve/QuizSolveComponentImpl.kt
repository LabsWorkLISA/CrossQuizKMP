package quizsolve

import com.arkivanov.decompose.ComponentContext

class QuizSolveComponentImpl(
    componentContext: ComponentContext
): QuizSolveComponent, ComponentContext by componentContext {
}