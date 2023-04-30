package ru.kyamshanov.mission.project_view.impl.data.api

import ru.kyamshanov.mission.project_view.impl.data.model.AddParticipantRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.EditProjectRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.EditTaskSetRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.GetTeamRsDto
import ru.kyamshanov.mission.project_view.impl.data.model.ProjectInfoDto
import ru.kyamshanov.mission.project_view.impl.data.model.RemoveParticipantRqDto
import ru.kyamshanov.mission.project_view.impl.data.model.SetRoleRqDto

internal interface ProjectApi {

    suspend fun getProject(projectId: String): ProjectInfoDto

    suspend fun getTeam(projectId: String): GetTeamRsDto

    suspend fun getManagedTeam(projectId: String): GetTeamRsDto
    suspend fun editProject(body: EditProjectRqDto)
    suspend fun editTasks(body: EditTaskSetRqDto)

    suspend fun setRole(body: SetRoleRqDto)

    suspend fun addParticipant(body: AddParticipantRqDto)

    suspend fun removeParticipant(body: RemoveParticipantRqDto)
}