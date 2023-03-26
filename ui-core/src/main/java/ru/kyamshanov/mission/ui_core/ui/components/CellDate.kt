package ru.kyamshanov.mission.ui_core.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Calendar
import java.util.Date
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme
import ru.kyamshanov.mission.ui_core.ui.utils.ComplexMissionDateFormatter
import ru.kyamshanov.mission.ui_core.ui.utils.MissionDateFormatter

@Composable
fun CellDate(
    modifier: Modifier = Modifier,
    value: Date?,
    onValueChange: (Date) -> Unit,
    label: String,
    missionDateFormatter: MissionDateFormatter,
) {

    val initialCalendar = Calendar.getInstance().apply {
        if (value != null) time = value
    }
    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            initialCalendar.apply {
                set(Calendar.YEAR, year)
                set(Calendar.MONTH, month)
                set(Calendar.DAY_OF_MONTH, dayOfMonth)
            }
            onValueChange.invoke(initialCalendar.time)
        },
        initialCalendar.get(Calendar.YEAR),
        initialCalendar.get(Calendar.MONTH),
        initialCalendar.get(Calendar.DAY_OF_MONTH)
    )

    TextField(
        modifier = modifier
            .border(
                color = MissionTheme.colors.border,
                width = 2.dp,
                shape = MissionTheme.shapes.medium
            )
            .fillMaxWidth(),
        value = value?.let { missionDateFormatter.invoke(it) }.orEmpty(),
        onValueChange = {},
        enabled = false,
        textStyle = MissionTheme.typography.large,
        label = { Text(text = label, style = MissionTheme.typography.inputHint) },
        shape = MissionTheme.shapes.medium,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MissionTheme.colors.primary,
            cursorColor = MissionTheme.colors.darkSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        maxLines = 1,
        trailingIcon = {
            Image(
                modifier = Modifier.clickable { datePickerDialog.show() },
                painter = painterResource(id = R.drawable.calendar),
                contentDescription = "Удалить",
            )
        }
    )
}

@Preview
@Composable
fun CellDatePreview() {
    MissionTheme {
        Box(modifier = Modifier.padding(15.dp)) {
            CellDate(
                value = Date(),
                onValueChange = {},
                label = "Логин",
                missionDateFormatter = { ComplexMissionDateFormatter().toDdMmYy(it) }
            )
        }
    }
}
