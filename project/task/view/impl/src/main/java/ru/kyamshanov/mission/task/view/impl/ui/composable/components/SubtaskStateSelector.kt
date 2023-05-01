package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.ui_core.ui.components.AlternativeButton
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun SubtaskStateSelector(
    states: List<SubtaskInfo.State>,
    clickOnState: (SubtaskInfo.State) -> Unit,
    onDismissRequest: () -> Unit,
) {
    Popup(
        alignment = Alignment.Center,
        onDismissRequest = onDismissRequest,
        properties = PopupProperties(clippingEnabled = false)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .background(MissionTheme.colors.secondButton)
                .padding(16.dp),
        ) {
            states.forEach { state ->
                Cell(modifier = Modifier.width(150.dp)) {
                    AlternativeButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        content = {
                            SubtaskStateText(subtaskState = state)
                        }
                    ) { clickOnState.invoke(state) }
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            MainButton(modifier = Modifier.align(Alignment.End), label = "Закрыть") { onDismissRequest.invoke() }
        }
    }
}

