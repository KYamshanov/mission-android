package ru.kyamshanov.mission.project_view.impl.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.project_view.api.di.ProjectComponent
import ru.kyamshanov.mission.project_view.impl.di.ModuleComponent
import ru.kyamshanov.mission.project_view.impl.domain.model.ParticipantInfo
import ru.kyamshanov.mission.project_view.impl.ui.model.ProjectInfoSlim
import ru.kyamshanov.mission.project_view.impl.ui.viewmodel.ParticipantsListViewModel
import ru.kyamshanov.mission.ui_core.R
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.SecondaryButton
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.SwipeableRow
import ru.kyamshanov.mission.ui_core.ui.components.TopBar
import ru.kyamshanov.mission.ui_core.ui.theme.MissionTheme

@Composable
internal fun ParticipantsListComposable(
    projectInfoSlim: ProjectInfoSlim,
    component: ModuleComponent = requireNotNull(Di.getInternalComponent<ProjectComponent, ModuleComponent>()),
    viewModel: ParticipantsListViewModel = viewModel {
        component.participantsListViewModelFactory.create(projectInfoSlim)
    },
) {
    val screenState by viewModel.screenState.collectAsState()

    Surface(
        topContent = {
            TopBar(title = projectInfoSlim.name, navigationListener = viewModel::clickOnBack)
        },
        bottomContent = {
            if (screenState.teamEditingScheme.isTeamEditable) {
                SecondaryButton(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    label = "Добавить участника",
                    onClick = viewModel::clickOnAddParticipant,
                )
            }
        }
    ) {

        Column(modifier = Modifier.padding(16.dp)) {
            val teamInfo = screenState.teamInfo
            teamInfo.mentor.also { participant ->
                MentorCell(
                    participant = participant,
                    clickOnChange = viewModel::clickOnChangeMentor,
                    editable = screenState.teamEditingScheme.isMentorEditable,
                    clickOnRemove = { participant?.let { viewModel.clickOnRemove(it) } }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            teamInfo.leader?.also { participant ->
                ParticipantCell(
                    participant = participant,
                    clickOnLeaderToggle = { viewModel.clickOnLeaderToggle(participant) },
                    clickOnRemove = { viewModel.clickOnRemove(participant) },
                    editable = screenState.teamEditingScheme.isLeaderEditable
                )
            } ?: {

            }
            teamInfo.participants.forEach { participant ->
                Spacer(modifier = Modifier.height(10.dp))
                ParticipantCell(
                    participant = participant,
                    clickOnLeaderToggle = { viewModel.clickOnLeaderToggle(participant) },
                    clickOnRemove = { viewModel.clickOnRemove(participant) },
                    editable = screenState.teamEditingScheme.isLeaderEditable
                )
            }
        }
    }
}

@Composable
private fun ParticipantCell(
    participant: ParticipantInfo,
    editable: Boolean,
    clickOnLeaderToggle: () -> Unit,
    clickOnRemove: () -> Unit,
) = Cell {
    SwipeableRow(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        hiddenContent = {
            if (editable) {
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.close_circle),
                    contentDescription = "Удалить",
                    modifier = Modifier.clickable { clickOnRemove.invoke() },
                    colorFilter = ColorFilter.tint(MissionTheme.colors.wrong)
                )
            }
        }
    ) {

        Text(
            text = if (participant.role == ParticipantInfo.Role.LEADER) "${participant.name.orEmpty()} (Капитан)" else participant.name.orEmpty(),
            style = MissionTheme.typography.title.run { if (participant.role == ParticipantInfo.Role.LEADER) this + MissionTheme.typography.gold else this }
        )
        Spacer(modifier = Modifier.weight(1f))
        if (participant.role == ParticipantInfo.Role.LEADER) {
            Image(
                painter = painterResource(id = R.drawable.crown_circle),
                contentDescription = "Сделать лидером",
                modifier = Modifier
                    .run { if (editable) clickable { clickOnLeaderToggle.invoke() } else this },
                colorFilter = ColorFilter.tint(MissionTheme.colors.gold),
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.crown_circle),
                contentDescription = "Сделать лидером",
                modifier = Modifier.clickable { clickOnLeaderToggle.invoke() },
                colorFilter = ColorFilter.tint(MissionTheme.colors.gray)
            )
        }
    }
}

@Composable
private fun MentorCell(
    participant: ParticipantInfo?,
    editable: Boolean,
    clickOnChange: () -> Unit,
    clickOnRemove: () -> Unit,
) = Cell {
    SwipeableRow(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        hiddenContent = {
            if (editable) {
                Spacer(modifier = Modifier.width(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.close_circle),
                    contentDescription = "Удалить",
                    modifier = Modifier.clickable { clickOnRemove.invoke() },
                    colorFilter = ColorFilter.tint(MissionTheme.colors.wrong)
                )
            }
        }
    ) {
        when {
            participant != null -> Text(
                text = "${participant.name} (Наставник)",
                style = MissionTheme.typography.title + MissionTheme.typography.green
            )
            else -> Text(
                text = "Отсутствует (Наставник)",
                style = MissionTheme.typography.title + MissionTheme.typography.green
            )
        }
        if (editable) {
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.square_edit_outline),
                contentDescription = "Удалить",
                modifier = Modifier.clickable { clickOnChange.invoke() },
                colorFilter = ColorFilter.tint(MissionTheme.colors.darkSecondary),
                contentScale = ContentScale.None
            )
        }
    }
}