package ru.kyamshanov.mission.background_registration.impl.data.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.kyamshanov.mission.background_registration.impl.data.data.model.BackRegisterRqDto
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.retrieveBody

internal class ProfileApiImpl @Inject constructor(
    private val requestFactory: RequestFactory,
) : ProfileApi {

    override suspend fun backRegister(body: BackRegisterRqDto): Unit = withContext(Dispatchers.IO) {
        val response = requestFactory.post("/profile/private/back_reg") {
            contentType(ContentType.Application.Json)
            setBody(body)
        }
        response.retrieveBody()
    }
}