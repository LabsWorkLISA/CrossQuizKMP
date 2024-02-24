package dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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

private data class ThemeOfTest(
    val id: Int,
    val text: String
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

    var picked by remember { mutableStateOf(false) }

    Text(
        text = "Создание теста",
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Color(0xFF1D1B20)
    )
    TypeOfTest(modifier = Modifier.padding(horizontal = 10.dp), picked = picked, onClick = {picked = !picked})
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
            Text("Продолжить", color = if (!picked) Color.Black else Color(0xFF6750A4))
        }
    }
}

@Composable
fun PickNameDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {

    var text by remember { mutableStateOf("") }

    Text(
        text = if(text.isEmpty()) "Название теста" else  text,
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Color(0xFF1D1B20)
    )
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 35.dp),
        supportingText = { Text(text = "Напишите название") },
        placeholder = { Text(text = "Тест") },
        singleLine = true
    )
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
            Text(
                "Продолжить",
                color = if (text.isEmpty()) Color(0xFF1C1B1F) else Color(0xFF6750A4)
            )
        }
    }
}

@Composable
fun PickThemesDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {

    var text by remember { mutableStateOf("") }


    var themesOfTests by remember { mutableStateOf(
        listOf(
            ThemeOfTest(1, "Theme 1"),
            ThemeOfTest(2, "Theme 2"),
            ThemeOfTest(3, "Theme 3")
        )
    )}

    val filteredThemes = themesOfTests.filter { it.text.contains(text, ignoreCase = true) }

    var listOfChecked by remember { mutableStateOf(listOf<Int>()) }


    Text(
        text = "Темы теста",
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth(),
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        color = Color(0xFF1D1B20)
    )
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 35.dp, end = 35.dp, bottom = 15.dp),
        placeholder = { Text(text = "Введите название") },
        label = {Text(text = "Введите название") }
    )

    if (filteredThemes.isEmpty() or ((themesOfTests.filter { it.text.equals(text, ignoreCase = true) }.isEmpty()) and (text.isNotEmpty()))) {
        CreateItemOfList(
            createThemeOfTest = {
                themesOfTests = themesOfTests + ThemeOfTest(themesOfTests.size + 1, text)
                listOfChecked = listOfChecked + themesOfTests.size
                text=""
            },
            text = text,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .shadow(elevation = 5.dp)
                .clip(RoundedCornerShape(10.dp))
        )
    }

    Column(modifier = Modifier
        .padding(horizontal = 10.dp)
        .clip(RoundedCornerShape(10.dp))
        .shadow(elevation = 5.dp)
    ) {
        LazyColumn {
            items(filteredThemes, key = {themeOftest -> themeOftest.id}) { themeOfTest ->
                ItemOfList(
                    themeOfTest = themeOfTest,
                    checked = listOfChecked.contains(themeOfTest.id),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            listOfChecked = listOfChecked + themeOfTest.id
                        } else {
                            listOfChecked = listOfChecked - themeOfTest.id
                        }
                    }
                )
            }
        }
    }
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
            Text("Создать", color = if (listOfChecked.isEmpty()) Color(0xFF1C1B1F) else Color(0xFF6750A4))
        }
    }
}

@Composable
private fun ItemOfList(
    themeOfTest: ThemeOfTest,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ListItem(
        headlineContent = { Text(text = themeOfTest.text) },
        trailingContent = { Checkbox(checked = checked, onCheckedChange = onCheckedChange) }
    )
}

@Composable
private fun CreateItemOfList(
    createThemeOfTest: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable { createThemeOfTest() },
        headlineContent = { Text(text = "Создать " + text) },
        leadingContent = { Icon(imageVector = Icons.Default.Add, contentDescription = null) }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TypeOfTest(
    picked: Boolean = true,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .wrapContentHeight(align = Alignment.CenterVertically)
            .let {
                if (picked) it.border(2.dp, Color(0xFF6750A4), RoundedCornerShape(20.dp))
                else it
            }
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .fillMaxWidth()
        ) {
            Text(text = "Шаблон тест", fontWeight = FontWeight.Medium, color = Color.Black)
            Text(text = "Включает в себя 3 тестовых вопроса с вариантами ответа")
        }
    }
}