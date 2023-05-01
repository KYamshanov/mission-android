package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.kyamshanov.mission.ui_core.R

@Composable
fun SomethingWentWrongDialog(
    modifier: Modifier = Modifier,
    visibleState: MutableState<Boolean>,
    onConfirm: () -> Unit,
) {
    if (visibleState.value)
        SomethingWentWrongDialog(
            modifier = modifier,
            onDismissRequest = { visibleState.value = false },
            onConfirm = {
                visibleState.value = false
                onConfirm.invoke()
            }
        )
}

@Composable
fun SomethingWentWrongDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
) = SomethingWentWrongDialog(
    onDismissRequest = onDismissRequest,
    onConfirm = onDismissRequest
)

@Composable
fun SomethingWentWrongDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = { Text(stringResource(id = R.string.something_went_wrong)) },
        modifier = modifier,
        confirmButton = {
            Button(onClick = onConfirm) { Text(text = stringResource(id = R.string.clearly)) }
        }
    )
}