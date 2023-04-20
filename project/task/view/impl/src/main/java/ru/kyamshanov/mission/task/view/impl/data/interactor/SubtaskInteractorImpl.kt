package ru.kyamshanov.mission.task.view.impl.data.interactor

import javax.inject.Inject
import ru.kyamshanov.mission.project.common.domain.model.SubtaskId
import ru.kyamshanov.mission.task.view.impl.data.api.ProjectApi
import ru.kyamshanov.mission.task.view.impl.data.mapper.toDomain
import ru.kyamshanov.mission.task.view.impl.domain.interactor.SubtaskInteractor
import ru.kyamshanov.mission.task.view.impl.domain.model.SubtaskInfo
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.di.TimeFormat
import ru.kyamshanov.mission.time.di.TimeFormatQualifier

internal class SubtaskInteractorImpl @Inject constructor(
    private val projectApi: ProjectApi,
    @TimeFormatQualifier(TimeFormat.DD_MN_YY) private val dateFormatter: MissionDateFormatter,
) : SubtaskInteractor {

    override suspend fun fetchSubtask(subtaskId: SubtaskId): Result<SubtaskInfo> = runCatching {
        projectApi.getSubtaskBySubtaskId(subtaskId.value).toDomain(dateFormatter)
    }
}