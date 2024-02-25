package org.crossquiz.quizresult

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import quizresult.QuizResult
import quizresult.QuizResultComponent
import quizresult.QuizResultState

class FakeQuizResultComponent : QuizResultComponent {

    override val quizResultState: StateFlow<QuizResultState>
        get() = MutableStateFlow(
            QuizResultState.Success(
                QuizResult(
                    currentScore = 9,
                    maxScore = 10,
                    rightQuestion = 9,
                    maxQuestion = 10,
                    assessment = QuizResult.Assessment.EXCELLENT,
                )
            )
        )

    override fun finishQuiz() {
        TODO("Not yet implemented")
    }

    override fun openFeedback() {
        TODO("Not yet implemented")
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuizResultScreenPreview() {
    QuizResultScreen(FakeQuizResultComponent())
}