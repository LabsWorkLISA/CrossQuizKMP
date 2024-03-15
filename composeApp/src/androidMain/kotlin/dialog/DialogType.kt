package com.example.quiz.dialog

enum class DialogType {
    PickBase, PickName, PickThemes
}

data class DialogEvent(
    val type: DialogType,
)

enum class Setup {
    Default
}