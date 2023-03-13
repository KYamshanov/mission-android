package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.authentication.di.AuthenticationComponent
import ru.kyamshanov.mission.authentication.impl.di.ModuleComponent
import ru.kyamshanov.mission.authentication.impl.ui.model.LoginScreenState.SomethingWentWrong
import ru.kyamshanov.mission.authentication.impl.ui.model.LoginScreenState.WrongPassword
import ru.kyamshanov.mission.authentication.impl.ui.viewmodel.LoginViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.main_screen_feature.api.di.MainScreenComponent
import ru.kyamshanov.mission.session_front.api.di.SessionFrontComponent
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun LoginComposable(
    viewModel: LoginViewModel
) {
    val wrongCodeDialogVisibleState = rememberSaveable { mutableStateOf(false) }
    val somethingWentWrongDialogVisibleState = rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(viewModel.screenState) {
        viewModel.screenState.collect {
            when (it) {
                WrongPassword -> wrongCodeDialogVisibleState.value = true
                SomethingWentWrong -> somethingWentWrongDialogVisibleState.value = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AuthenticationComponent { login, password ->
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
                Button(
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
                Button(
                    onClick = {
                        visibleState.value = false
                    }
                ) {
                    Text(text = "Понятно")
                }
            }
        )
}
