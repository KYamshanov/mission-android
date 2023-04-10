package ru.kyamshanov.mission.ui_core.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.Image
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
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.impl.ComplexMissionDateFormatter
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
fun CellDate(
    modifier: Modifier = Modifier,
    value: Date?,
    onValueChange: (Date) -> Unit,
    label: String,
    missionDateFormatter: MissionDateFormatter,
) = Cell(modifier = modifier) {
    DateField(value = value, onValueChange = onValueChange, label = label, missionDateFormatter = missionDateFormatter)
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
