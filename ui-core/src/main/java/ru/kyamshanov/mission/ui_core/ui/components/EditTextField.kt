package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String?,
    editable: Boolean = false,
    maxLines: Int = 1,
    rightIcon: (@Composable () -> Unit)? = null,
    underlined: Boolean = true,
    textStyle: TextStyle = MissionTheme.typography.inputText,
    onValueChange: (String) -> Unit,
) {
    MissionTextField(
        modifier = modifier,
        text = text,
        label = label?.let { { Text(text = it, style = MissionTheme.typography.inputHint) } },
        editable = editable,
        maxLines = maxLines,
        rightIcon = rightIcon,
        underlined = underlined,
        textStyle = textStyle,
        onValueChange = onValueChange
    )
}

@Composable
fun MissionTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: (@Composable () -> Unit)? = null,
    editable: Boolean = false,
    maxLines: Int = 1,
    rightIcon: (@Composable () -> Unit)? = null,
    underlined: Boolean = true,
    textStyle: TextStyle = MissionTheme.typography.inputText,
    onValueChange: (String) -> Unit,
) = BasicTextField(
    modifier = modifier
        .fillMaxWidth(),
    textStyle = textStyle,
    value = text,
    onValueChange = onValueChange,
    maxLines = maxLines,
    readOnly = editable.not(),
    cursorBrush = SolidColor(MissionTheme.colors.darkSecondary),
    decorationBox = { innerTextField ->
        BoxWithConstraints {
            val localDensity = LocalDensity.current
            val iconSizeState = remember { mutableStateOf(IntSize.Zero) }

            Column(
                modifier = Modifier.align(Alignment.TopStart)
                    .width(this.maxWidth - with(localDensity) { iconSizeState.value.width.toDp() })
            ) {
                label?.invoke()
                innerTextField()
            }
            rightIcon?.let { icon ->
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .wrapContentSize()
                        .onSizeChanged { iconSizeState.value = it },
                    contentAlignment = Alignment.Center,
                    content = { icon() },
                )
            }
            if (underlined) CellLine(modifier = Modifier.align(Alignment.BottomStart))
        }
    },
)

@Preview
@Composable
fun MissionTextFieldPreview() {
    MissionTheme {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp).background(MissionTheme.colors.background)) {
            val textState = remember { mutableStateOf("") }

            TextFieldCompose(
                label = "Проект про мен3я233",
                text = "a b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c da b c d",
                onValueChange = { textState.value = it },
                editable = true
            )
        }
    }
}
