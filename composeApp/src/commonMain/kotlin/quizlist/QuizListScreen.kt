package quizlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.Dispatchers

@Composable
fun QuizListScreen(component: QuizListComponent) {
    val state by component.state.collectAsState(Dispatchers.Main.immediate)

    MainContent(state)
}

@Composable
private fun MainContent(state: QuizListState) {

    // TODO
    when (state) {
        is QuizListState.Data -> {

        }
        is QuizListState.Error -> {

        }
        is QuizListState.Loading -> {

        }
    }

}
