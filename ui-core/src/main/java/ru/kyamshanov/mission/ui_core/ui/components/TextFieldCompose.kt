package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextFieldCompose(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    maxLines: Int = 1,
    editable: Boolean,
    underlined: Boolean = true,
    suffix: String? = null,
    onValueChange: (String) -> Unit,
) {
    var editableState by rememberSaveable { mutableStateOf(false) }

    val rightIconComposable: (@Composable () -> Unit)? = if (editable) {
        {
            if (editableState) {
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
                        editableState = true
                    }
                )
            }
        }
    } else null

    EditTextField(
        modifier = modifier,
        text = text,
        label = label,
        maxLines = maxLines,
        rightIcon = rightIconComposable,
        onValueChange = onValueChange,
        editable = editableState,
        underlined = underlined,
        suffix = suffix
    )
}

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    rightIcon: (@Composable () -> Unit)? = null,
    underlined: Boolean = false,
) = TextField(
    modifier = modifier,
    label = label,
    content = { Text(text = text, style = MissionTheme.typography.inputText) },
    underlined = underlined,
    rightIcon = rightIcon,
)

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String? = null,
    underlined: Boolean = false,
    rightIcon: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) = BoxWithConstraints(modifier = modifier) {
    val localDensity = LocalDensity.current
    val iconSizeState = remember { mutableStateOf(IntSize.Zero) }


    Column(
        modifier = Modifier.align(Alignment.TopStart)
            .width(this.maxWidth - with(localDensity) { iconSizeState.value.width.toDp() })
    ) {
        label?.let { Text(it, style = MissionTheme.typography.inputHint) }
        content.invoke()
    }

    rightIcon?.let {
        Box(
            modifier = Modifier.align(Alignment.CenterEnd).onSizeChanged { iconSizeState.value = it }) { it.invoke() }
    }
    if (underlined) CellLine(modifier = Modifier.align(Alignment.BottomStart))
}

@Preview
@Composable
fun TextFieldPreview() {
    MissionTheme {
        Column(modifier = Modifier.fillMaxSize().padding(15.dp).background(MissionTheme.colors.background)) {
            TextField(
                label = "Ответственный",
                text = "Василий васильевич пупкин",
                rightIcon = {
                    Image(
                        modifier = Modifier.clickable { },
                        painter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.ic_search),
                        contentDescription = "Поиск",
                    )
                },
            )
            TextFieldCompose(
                text = "Пошёл",
                label = "Pizz",
                editable = true,
                suffix = "в пешее ",
                onValueChange = {}
            )
        }
    }
}
