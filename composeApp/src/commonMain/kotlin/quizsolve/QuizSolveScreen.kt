package quizsolve

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Button
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun QuizSolveScreen(component: QuizSolveComponent) {
    val currentQuestion by component.currentQuestion.collectAsState(Dispatchers.Main.immediate)

    Surface {
        Scaffold(
            content = {
                MainContent(
                    question = currentQuestion,
                    modifier = Modifier.padding(it)
                )
            },
            bottomBar = {
                BottomNavigation(onNextButtonClick = { component.onNextButtonClick() })
            }
        )
    }
}

@Composable
private fun MainContent(
    question: Question,
    modifier: Modifier,
) {
    Column(
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
    ) {
        QuestionText(text = question.questionText)
        Spacer(modifier = Modifier.height(16.dp))
//        when (question) {
//            is Question.Single -> SingleAnswerList(answers = question.answers)
//            is Question.Multiple -> MultipleAnswerList(answers = question.answers)
//        }
    }
}

//@Composable
//private fun SingleAnswerList(answers: Answers) {
//    var currentChosenAnswer by remember { mutableStateOf(Answer.Id("-1")) }
//    when (answers) {
//        is Answers.TextItems -> {
//            LazyColumn {
//                itemsIndexed(items = answers.items) { index, item ->
//                    AnswerTextItem(
//                        item = item,
//                        isChosen = currentChosenAnswer == item.id,
//                        isSingle = true,
//                        onClick = { currentChosenAnswer = it.id },
//                    )
//                }
//            }
//        }
//
//        is Answers.ImageItems -> {
//            LazyColumn {
//                itemsIndexed(items = answers.items) { index, item ->
//                    AnswerImageItem(
//                        item = item,
//                        isChosen = currentChosenAnswer == item.id,
//                        isSingle = true,
//                        onClick = {
//                            currentChosenAnswer = it.id
//                        },
//                    )
//                }
//            }
//        }
//    }
//}

//@Composable
//private fun MultipleAnswerList(answers: Answers) {
//    val currentChosenAnswers = remember { mutableStateListOf<Answer.Id>() }
//    when (answers) {
//        is Answers.TextItems -> {
//            LazyColumn {
//                itemsIndexed(items = answers.items) { index, item ->
//                    AnswerTextItem(
//                        item = item,
//                        isChosen = currentChosenAnswers.contains(item.id),
//                        isSingle = false,
//                        onClick = {
//                            if (currentChosenAnswers.contains(it.id)) {
//                                currentChosenAnswers.remove(it.id)
//                            } else {
//                                currentChosenAnswers.add(it.id)
//                            }
//                        },
//                    )
//                }
//            }
//        }
//
//        is Answers.ImageItems -> {
//            LazyRow {
//                itemsIndexed(items = answers.items) { index, item ->
//                    AnswerImageItem(
//                        item = item,
//                        isChosen = currentChosenAnswers.contains(item.id),
//                        isSingle = false,
//                        onClick = {
//                            if (currentChosenAnswers.contains(it.id)) {
//                                currentChosenAnswers.remove(it.id)
//                            } else {
//                                currentChosenAnswers.add(it.id)
//                            }
//                        },
//                    )
//                }
//            }
//        }
//    }
//}

//@OptIn(ExperimentalResourceApi::class)
//@Composable
//private fun AnswerTextItem(
//    item: Answer.Text,
//    onClick: (Answer.Text) -> Unit,
//    isChosen: Boolean,
//    isSingle: Boolean,
//) {
//    Surface(
//        shape = MaterialTheme.shapes.small,
//        border = BorderStroke(
//            width = 1.dp,
//            color = MaterialTheme.colors.primary.copy(alpha = 0.5f)
//        ),
//        modifier = Modifier.padding(vertical = 8.dp)
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .selectable(
//                    selected = isChosen,
//                    onClick = { onClick(item) },
//                )
////                .background(answerBackgroundColor)
//                .padding(end = 16.dp, top = 20.dp, bottom = 20.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            IconToggleButton(
//                modifier = Modifier.clickable { onClick(item) },
//                checked = isChosen,
//                onCheckedChange = { },
//                content = {
//                    Icon(
//                        modifier = Modifier.clickable { onClick(item) },
//                        painter = painterResource(""
//                            // TODO how to get drawable in KMP project
////                            when {
////                                isChosen && !isSingle -> R.drawable.baseline_check_box_24
////                                isChosen && isSingle -> R.drawable.baseline_check_circle_24
////                                !isChosen && isSingle -> R.drawable.outline_circle_24
////                                else -> R.drawable.baseline_check_box_outline_blank_24
////                            }
//                        ),
//                        contentDescription = null,
//                        tint = MaterialTheme.colors.primary
//                    )
//                }
//            )
//            Text(
//                modifier = Modifier.weight(1f),
//                text = item.text
//            )
//        }
//    }
//}

//@OptIn(ExperimentalResourceApi::class)
//@Composable
//private fun AnswerImageItem(
//    item: Answer.Image,
//    onClick: (Answer.Image) -> Unit,
//    isChosen: Boolean,
//    isSingle: Boolean,
//) {
//
//    Surface(
//        shape = MaterialTheme.shapes.small,
//        border = BorderStroke(
//            width = 1.dp,
//            color = MaterialTheme.colors.primary.copy(alpha = 0.5f)
//        ),
//        modifier = Modifier.padding(vertical = 8.dp)
//    ) {
//        Column(
//            modifier = Modifier.clickable(onClick = { onClick(item) }),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            AsyncImage(
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(vertical = 10.dp),
//                contentScale = ContentScale.Crop,
//                model = item.url,
//                contentDescription = null,
//            )
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//            ) {
//                IconToggleButton(
//                    modifier = Modifier.clickable { onClick(item) },
//                    checked = isChosen,
//                    onCheckedChange = { },
//                    content = {
//                        Icon(
//                            modifier = Modifier.clickable { onClick(item) },
//                            painter = painterResource(""
//                                // TODO how to get drawable in KMP project
////                                when {
////                                    isChosen && !isSingle -> R.drawable.baseline_check_box_24
////                                    isChosen && isSingle -> R.drawable.baseline_check_circle_24
////                                    !isChosen && isSingle -> R.drawable.outline_circle_24
////                                    else -> R.drawable.baseline_check_box_outline_blank_24
////                                }
//                            ),
//                            contentDescription = null,
//                            tint = MaterialTheme.colors.primary,
//                        )
//                    }
//                )
//                if (item.text != null) {
//                    Text(
//                        modifier = Modifier.weight(1f),
//                        text = item.text,
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun AsyncImage(modifier: Modifier, contentScale: ContentScale, model: String, contentDescription: Nothing?) {
    AsyncImage(modifier, contentScale, model, contentDescription)
}

@Composable
private fun QuestionText(text: String) {
    val backgroundColor = if (isSystemInDarkTheme()) {
        MaterialTheme.colors.onSurface.copy(alpha = 0.06f)
    } else {
        MaterialTheme.colors.onSurface.copy(alpha = 0.04f)
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = MaterialTheme.shapes.small
            )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp)
        )
    }
}

@Composable
private fun BottomNavigation(
    modifier: Modifier = Modifier,
    onNextButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = onNextButtonClick,
        ) {
            Text(text = "Следующий вопрос")
        }

    }
}