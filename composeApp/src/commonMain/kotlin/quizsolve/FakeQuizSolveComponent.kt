package quizsolve

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.StateFlow

class FakeQuizSolveComponent: QuizSolveComponent {

    override val currentQuiz: StateFlow<Quiz>
        get() = TODO("Not yet implemented")
    override val currentQuestion: StateFlow<Question>
        get() = TODO("Not yet implemented")

    override fun onNextButtonClick() {
        TODO("Not yet implemented")
    }
}

//@Preview(showSystemUi = true)
//@Composable
//fun SignInUiPreview() {
//    AppTheme {
//        SignInUi(FakeSignInComponent())
//    }
//}