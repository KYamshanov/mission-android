package ru.kyamshanov.mission.profile_facade.impl.data.api

import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody
import ru.kyamshanov.mission.profile_facade.impl.data.model.FetchUserInfoRsDto

internal class ProfileApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProfileApi {

    override suspend fun fetch(): FetchUserInfoRsDto = withContext(Dispatchers.IO) {
        val response = requestFactory.get("/profile/private/fetch") {
            contentType(ContentType.Application.Json)
        }
        response.retrieveBody()
    }
}