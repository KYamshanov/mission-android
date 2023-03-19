package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun TextFieldCompose(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    maxLines: Int = 1,
    editable: Boolean,
    onValueChange: (String) -> Unit,
) {
    val editableState = rememberSaveable { mutableStateOf(false) }

    val rightIconComposable: (@Composable () -> Unit)? = if (editable) {
        {
            if (editableState.value) {
                Image(
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = stringResource(id = R.string.clear),
                    modifier = Modifier.clickable { onValueChange.invoke("") }
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.square_edit_outline),
                    contentDescription = stringResource(id = R.string.edit),
                    modifier = Modifier.clickable {
                        editableState.value = true
                    }
                )
            }
        }
    } else null

    TextField(
        modifier = modifier,
        text = text,
        label = label,
        maxLines = maxLines,
        rightIcon = rightIconComposable,
        onValueChange = onValueChange,
        editable = editableState.value
    )
}

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    editable: Boolean = false,
    maxLines: Int = 1,
    rightIcon: (@Composable () -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {

    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        textStyle = MissionTheme.typography.inputText,
        label = label?.let { { Text(text = it, style = MissionTheme.typography.inputHint) } },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            cursorColor = MissionTheme.colors.darkSecondary,
            focusedIndicatorColor = MissionTheme.colors.darkSecondary,
            unfocusedIndicatorColor = MissionTheme.colors.darkSecondary,
            disabledIndicatorColor = MissionTheme.colors.darkSecondary,
        ),
        maxLines = maxLines,
        readOnly = editable.not(),
        trailingIcon = rightIcon,
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    MissionTheme {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp).background(MissionTheme.colors.background)) {
            val textState = remember { mutableStateOf("") }
            TextFieldCompose(
                text = "Проект про меня",
                label = "Название проекта",
                onValueChange = { textState.value = it },
                editable = false
            )

            TextFieldCompose(
                text = "Проект про меня",
                label = "Название проекта",
                onValueChange = { textState.value = it },
                editable = true
            )
        }
    }
}
