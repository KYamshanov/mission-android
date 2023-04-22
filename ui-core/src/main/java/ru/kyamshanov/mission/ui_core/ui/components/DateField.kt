package ru.kyamshanov.mission.ui_core.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import java.util.Calendar
import java.util.Date
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun DateField(
    modifier: Modifier = Modifier,
    value: Date?,
    onValueChange: (Date) -> Unit,
    label: String,
    editable: Boolean = false,
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

    MissionTextField(
        modifier = modifier
            .fillMaxWidth(),
        text = value?.let { missionDateFormatter.invoke(it) }.orEmpty(),
        onValueChange = {},
        editable = false,
        textStyle = MissionTheme.typography.large,
        label = { Text(text = label, style = MissionTheme.typography.inputHint) },
        maxLines = 1,
        rightIcon = if (editable) {
            {
                Image(
                    modifier = Modifier.clickable { datePickerDialog.show() },
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "Удалить",
                )
            }
        } else null,
        underlined = false
    )
}
