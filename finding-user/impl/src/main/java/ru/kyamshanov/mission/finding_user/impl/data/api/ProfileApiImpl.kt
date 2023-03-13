package ru.kyamshanov.mission.finding_user.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRqDto
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRsDto
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody

internal class ProfileApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProfileApi {

    override suspend fun findUsers(body: FindUsersRqDto): FindUsersRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.post("/profile/manager/search/find") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }
}