package ru.kyamshanov.mission.creating_project.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRqDto
import ru.kyamshanov.mission.creating_project.impl.data.model.CreateProjectRsDto
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.exposeException
import javax.inject.Inject

internal class ProjectApiImpl @Inject constructor(
    private val requestFactory: RequestFactory
) : ProjectApi {

    override suspend fun create(body: CreateProjectRqDto): Result<CreateProjectRsDto> = runCatching {
        val response = requestFactory.post("project/private/admin/create") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        return response.exposeException()
    }
}