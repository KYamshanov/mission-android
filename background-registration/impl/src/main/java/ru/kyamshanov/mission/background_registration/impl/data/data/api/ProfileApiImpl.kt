package ru.kyamshanov.mission.background_registration.impl.data.data.api

import io.ktor.client.request.parameter
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.profile_facade.impl.data.model.FetchUserDtoRs

internal class ProfileApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProfileApi {

    override suspend fun backRegister(authUserId: String): Result<Unit> = runCatching {
        val response = requestFactory.get("/private/profile/fetch") {
            parameter("id", authUserId)
            contentType(ContentType.Application.Json)
        }
        response.retrieveBody()
    }
}