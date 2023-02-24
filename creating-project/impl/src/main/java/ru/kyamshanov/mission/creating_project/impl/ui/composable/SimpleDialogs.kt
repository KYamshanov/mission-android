package ru.kyamshanov.mission.creating_project.impl.ui.composable

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SomethingWentWrongDialog(modifier: Modifier = Modifier, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = onClose,
        text = { Text("Что-то пошло не так") },
        modifier = modifier,
        confirmButton = {
            Button(
                onClick = onClose
            ) {
                Text(text = "Понятно")
            }
        }
    )
}