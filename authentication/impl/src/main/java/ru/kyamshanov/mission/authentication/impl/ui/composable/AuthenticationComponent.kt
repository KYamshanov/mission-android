package ru.kyamshanov.mission.authentication.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
internal fun AuthenticationComponent(
    modifier: Modifier = Modifier,
    onLogin: (String, CharSequence) -> Unit
) {
    val loginState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = loginState.value,
            onValueChange = { text -> loginState.value = text },
            label = { Text(text = "Логин") }
        )
        TextField(
            value = passwordState.value,
            onValueChange = { text -> passwordState.value = text },
            label = { Text(text = "Регистрация") }
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { onLogin(loginState.value, passwordState.value) }) {
            Text(text = "Войти")
        }
    }
}