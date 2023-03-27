package ru.kyamshanov.mission.project.task.creation.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.project.task.creation.impl.data.model.CreateTaskRqDto
import ru.kyamshanov.mission.project.task.creation.impl.data.model.CreateTaskRsDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun createTask(body: CreateTaskRqDto): CreateTaskRsDto = withContext(Dispatchers.IO) {
        requestFactory.post("/project/manager/task/create") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.retrieveBody()
    }
}