package ru.kyamshanov.mission.task.set_points.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.task.set_points.impl.data.model.SetTaskPointsRqDto

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProjectApi {

    override suspend fun setTaskPoint(body: SetTaskPointsRqDto): Unit = withContext(Dispatchers.IO) {
        requestFactory.post("/project/manager/task/set_points") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.retrieveBody()
    }
}