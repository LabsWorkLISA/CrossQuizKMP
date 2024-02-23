package dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

private enum class DialogType {
    PickBase, PickName, PickThemes
}

private data class DialogEvent(
    val type: DialogType,
)

@Composable
private fun NewDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    dialogType: DialogType = DialogType.PickBase
) {
    val dialogEvent by remember {
        mutableStateOf<DialogEvent?>(value = DialogEvent(dialogType))
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            dialogEvent?.let {event ->
                when(event.type) {
                    DialogType.PickBase -> PickBaseDialog( onDismissRequest, onConfirmation)
                    DialogType.PickName -> PickNameDialog(onDismissRequest, onConfirmation)
                    DialogType.PickThemes -> PickThemesDialog(onDismissRequest, onConfirmation)
                }
            }


        }
    }
}

@Composable
fun PickBaseDialog(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
) {
    Text(
        text = "Создание теста",
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
    TypeOfTest(modifier = Modifier.padding(horizontal = 10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = { onDismissRequest() },
        ) {
            Text("Назад")
        }
        TextButton(
            onClick = { onConfirmation() }
        ) {
            Text("Продолжить")
        }
    }
}

@Composable
fun PickNameDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Text(
        text = "Название теста",
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
    TypeOfTest(modifier = Modifier.padding(horizontal = 10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = { onDismissRequest() },
        ) {
            Text("Назад")
        }
        TextButton(
            onClick = { onConfirmation() }
        ) {
            Text("Продолжить")
        }
    }
}

@Composable
fun PickThemesDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    Text(
        text = "Темы теста",
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center
    )
    TypeOfTest(modifier = Modifier.padding(horizontal = 10.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 18.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        TextButton(
            onClick = { onDismissRequest() },
        ) {
            Text("Назад")
        }
        TextButton(
            onClick = { onConfirmation() }
        ) {
            Text("Продолжить")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TypeOfTest(
    picked: Boolean = true,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { /*TODO*/ },
        colors = CardDefaults.cardColors(containerColor = Color.Cyan),
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .let {
                if (picked) it.border(2.dp, Color.Black, RoundedCornerShape(10.dp))
                else it
            }
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
        ) {
            Text(text = "Шаблон тест")
            Text(text = "Включает в себя 3 тестовых вопроса с вариантами ответа")
        }
    }
}