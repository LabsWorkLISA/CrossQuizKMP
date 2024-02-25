package org.crossquiz.quizresult

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import org.crossquiz.R
import quizresult.QuizResult
import quizresult.QuizResultComponent
import quizresult.QuizResultState

@Composable
fun QuizResultScreen(component: QuizResultComponent) {
    val result by component.quizResultState.collectAsState(Dispatchers.Main.immediate)

    when (result) {
        is QuizResultState.Success -> Success(
            result as QuizResultState.Success,
            onFeedbackClick = { component.openFeedback() },
            onFinishClick = { component.finishQuiz() },
        )

        is QuizResultState.Loading -> Loading()
        is QuizResultState.Error -> Error()
    }
}

@Composable
private fun Success(
    state: QuizResultState.Success,
    onFeedbackClick: () -> Unit,
    onFinishClick: () -> Unit,
) {
    Surface {
        Scaffold(
            content = {
                Result(modifier = Modifier.padding(it), state)
            },
            bottomBar = {
                BottomNavigation(
                    onFinishClick = onFinishClick,
                    onFeedbackClick = onFeedbackClick,
                )
            }
        )
    }
}

@Composable
private fun Loading() {

}

@Composable
private fun Error() {

}

@Composable
private fun Result(
    modifier: Modifier,
    state: QuizResultState.Success,
) {
    val color = getColor(state.result.assessment)
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                stringResource(R.string.quiz_result_main),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h3,
            )
            ResultCell {
                MultiStyleTextScore(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 6.dp),
                    currentScore = state.result.currentScore,
                    maxScore = state.result.maxScore,
                    color = color,
                )
            }
            ResultCell {
                MultiStyleTextAnswers(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 6.dp),
                    currentAnswer = state.result.currentScore,
                    maxAnswer = state.result.maxScore,
                    color = color,
                )
            }
        }
    }
}

@Composable
private fun BottomResult(assessment: QuizResult.Assessment) {
    when (assessment) {
        QuizResult.Assessment.EXCELLENT -> {

        }
        QuizResult.Assessment.GOOD -> {

        }
        QuizResult.Assessment.BAD -> {

        }
    }
}

@Composable
private fun ResultCell(
    content: @Composable () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colors.primary.copy(alpha = 0.5f)
        ),
        modifier = Modifier.width(300.dp).padding(vertical = 8.dp)
    ) {
        content.invoke()
    }
}

@Composable
private fun BottomNavigation(
    onFinishClick: () -> Unit,
    onFeedbackClick: () -> Unit,
) {
    Column {
        Button(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 0.dp),
            onClick = onFeedbackClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colors.onPrimary,
            )
        ) {
            Text(text = stringResource(R.string.quiz_result_feedback))
        }

        Button(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 0.dp),
            onClick = onFinishClick,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.5f),
                contentColor = MaterialTheme.colors.onPrimary,
            )
        ) {
            Text(text = stringResource(R.string.quiz_result_finish))
        }
    }
}

@Composable
private fun MultiStyleTextScore(
    modifier: Modifier,
    currentScore: Int,
    maxScore: Int,
    color: Color,
) {
    Text(
        buildAnnotatedString {
            append(stringResource(R.string.quiz_result_your_result))
            withStyle(style = SpanStyle(color = color)) {
                append("$currentScore")
            }
            append(" ")
            append(stringResource(R.string.quiz_result_out_of, maxScore))
            append(" ")
            append(stringResource(R.string.quiz_result_scores))
        },
        textAlign = TextAlign.Center,
        modifier = modifier,
    )
}

@Composable
private fun MultiStyleTextAnswers(
    modifier: Modifier,
    currentAnswer: Int,
    maxAnswer: Int,
    color: Color,
) {
    Text(
        buildAnnotatedString {
            append(stringResource(R.string.quiz_result_correct_answers))
            withStyle(style = SpanStyle(color = color)) {
                append("$currentAnswer")
            }
            append(" ")
            append(stringResource(R.string.quiz_result_out_of, maxAnswer))
        },
        textAlign = TextAlign.Center,
        modifier = modifier,
    )
}

private fun getColor(assessment: QuizResult.Assessment): Color =
    when (assessment) {
        // TODO add colors in UI system, change yellow color
        QuizResult.Assessment.EXCELLENT -> Color.Green
        QuizResult.Assessment.GOOD -> Color.Yellow
        QuizResult.Assessment.BAD -> Color.Red
    }
