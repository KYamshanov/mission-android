package ru.kyamshanov.mission.ui_core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun Search(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
            color = MissionTheme.colors.secondary,
            width = 3.dp,
            shape = MissionTheme.shapes.mediumHuge
        ),
        value = value,
        onValueChange = onValueChange,
        textStyle = MissionTheme.typography.large,
        shape = MissionTheme.shapes.mediumHuge,
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = stringResource(id = R.string.search),
                colorFilter = ColorFilter.tint(MissionTheme.colors.darkSecondary)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MissionTheme.colors.input,
            cursorColor = MissionTheme.colors.darkSecondary,
            focusedIndicatorColor = Color.Unspecified,
            unfocusedIndicatorColor = Color.Unspecified,
            disabledIndicatorColor = Color.Unspecified,
        ),
        maxLines = 1
    )
}

@Preview
@Composable
fun SearchPreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            Search(
                value = "Log",
                onValueChange = {},
            )
        }
    }
}
