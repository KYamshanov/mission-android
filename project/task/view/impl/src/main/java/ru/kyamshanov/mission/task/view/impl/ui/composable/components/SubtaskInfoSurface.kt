package ru.kyamshanov.mission.task.view.impl.ui.composable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.task.view.impl.ui.viewmodel.SubtaskViewModel
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.EditTextField
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun SubtaskInfoSurface(
    info: SubtaskInfo,
    viewModel: SubtaskViewModel,
) = Surface(
    modifier = Modifier
        .fillMaxSize(),
    topContent = {
        TopBar(title = info.title, navigationListener = viewModel::onBack)
    },
    bottomContent = {
        /* if (info.setPointsButtonVisible) {
             SecondaryButton(label = stringResource(id = R.string.tv_set_points_title)) {
                 viewModel.openSetPointScreen()
             }
         }*/
    },
    content = {
        SubtaskInfoCell(modifier = Modifier.padding(16.dp), info)
    }
)

@Composable
private fun SubtaskInfoCell(
    modifier: Modifier = Modifier,
    info: SubtaskInfo,
) = Column(modifier = modifier) {

    TextFieldCompose(
        label = "Задача",
        text = info.title,
        onValueChange = {},
        editable = true
    )

    TextFieldCompose(
        label = "Описание задачи",
        text = info.description,
        onValueChange = {},
        editable = true
    )

    TextField(
        label = "Ответственный",
        text = info.responsible.name,
        rightIcon = {
            Image(
                modifier = Modifier.clickable { },
                painter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.ic_search),
                contentDescription = "Поиск",
            )
        },
        underlined = true
    )
    Spacer(modifier = Modifier.height(10.dp))
    Cell(autoPaddings = false) {
        TextField(
            rightIcon = {
                Image(
                    modifier = Modifier.clickable { },
                    painter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.square_edit_outline),
                    contentDescription = "Удалить",
                )
            },
            content = {
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(text = "Состояние:", style = MissionTheme.typography.title)
                    SubtaskStateText(info.stage)
                }
            }
        )
    }

    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = "Описание результатов",
        style = MissionTheme.typography.title
    )

    Text(
        modifier = Modifier.align(Alignment.Start),
        text = "Результатов ещё нет",
        style = MissionTheme.typography.titleSecondary
    )

    Cell {
        EditTextField(
            label = "Описание результатов",
            text = info.executionResult ?: "Добавьте описание хода выполнения проекта",
            onValueChange = {},
            rightIcon = {
                Image(
                    modifier = Modifier.clickable { },
                    painter = painterResource(id = ru.kyamshanov.mission.ui_core.R.drawable.close),
                    contentDescription = "Очистить",
                )
            },
            underlined = false,
        )
    }

    Spacer(modifier = Modifier.weight(1f))

    MainButton(modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp), label = "Сохранить") {}

}