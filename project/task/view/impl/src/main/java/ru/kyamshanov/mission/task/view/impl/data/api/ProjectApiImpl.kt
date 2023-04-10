package ru.kyamshanov.mission.task.view.impl.data.api

import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.task.view.impl.data.model.GetTaskRsDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun getTask(taskId: String): GetTaskRsDto = withContext(Dispatchers.IO) {
        requestFactory.get("/project/private/task/get") {
            parameter("task", taskId)
            contentType(ContentType.Application.Json)
        }.retrieveBody()
    }
}