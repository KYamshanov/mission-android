package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.authentication.impl.data.api.AuthenticationApiImpl
import ru.kyamshanov.mission.authentication.impl.ui.model.ScreenState.SomethingWentWrong
import ru.kyamshanov.mission.authentication.impl.ui.model.ScreenState.WrongPassword
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.AuthenticationViewModel

@Composable
internal fun AuthenticationComposable(
    viewModel: AuthenticationViewModel = viewModel { AuthenticationViewModel(AuthenticationApiImpl()) }
) {
    val wrongCodeDialogVisibleState = rememberSaveable { mutableStateOf(false) }
    val somethingWentWrongDialogVisibleState = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(true) {
        viewModel.screenState.collect {
            when (it) {
                WrongPassword -> wrongCodeDialogVisibleState.value = true
                SomethingWentWrong -> somethingWentWrongDialogVisibleState.value = true
            }
        }
    }

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.error)
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationComponent(
            modifier = Modifier.imePadding()
        ) { login, password ->
            viewModel.login(login, password)
        }
    }

    WrongCodeDialog(visibleState = wrongCodeDialogVisibleState)
    SomethingWentWrongDialog(visibleState = somethingWentWrongDialogVisibleState)
}

@Composable
private fun SomethingWentWrongDialog(modifier: Modifier = Modifier, visibleState: MutableState<Boolean>) {
    if (visibleState.value)
        AlertDialog(
            onDismissRequest = { visibleState.value = false },
            text = { Text("Что-то пошло не так") },
            modifier = modifier,
            confirmButton = {
                TextButton(
                    onClick = {
                        visibleState.value = false
                    }
                ) {
                    Text(text = "Понятно")
                }
            }
        )
}

@Composable
private fun WrongCodeDialog(modifier: Modifier = Modifier, visibleState: MutableState<Boolean>) {
    if (visibleState.value)
        AlertDialog(
            onDismissRequest = { visibleState.value = false },
            text = { Text("Пароль неверный") },
            modifier = modifier,
            confirmButton = {
                TextButton(
                    onClick = {
                        visibleState.value = false
                    }
                ) {
                    Text(text = "Понятно")
                }
            }
        )
}