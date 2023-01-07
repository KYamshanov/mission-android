package ru.kyamshanov.mission.session_front.impl.data.api

import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.kyamshanov.mission.network_core.api.RequestFactory
import ru.kyamshanov.mission.network_core.api.utils.exposeException
import ru.kyamshanov.mission.session_front.impl.data.model.BlockRefreshRqDto
import ru.kyamshanov.mission.session_front.impl.data.model.CheckAccessRqDto
import ru.kyamshanov.mission.session_front.impl.data.model.CheckAccessRsDto
import ru.kyamshanov.mission.session_front.impl.data.model.RefreshRqDto
import ru.kyamshanov.mission.session_front.impl.data.model.TokensRsDto
import ru.kyamshanov.mission.session_front.impl.data.model.UserDto
import javax.inject.Inject

internal class AuthenticationApiImpl @Inject constructor(
    private val requestFactory: RequestFactory
) : AuthenticationApi {

    override suspend fun login(rq: UserDto): Result<TokensRsDto> = runCatching {
        val response = requestFactory.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(rq)
        }
        response.exposeException()
    }

    override suspend fun check(rq: CheckAccessRqDto): Result<CheckAccessRsDto> = runCatching {
        val response = requestFactory.post("auth/check") {
            contentType(ContentType.Application.Json)
            setBody(rq)
        }
        response.exposeException()
    }

    override suspend fun refresh(rq: RefreshRqDto): Result<TokensRsDto> = runCatching {
        val response = requestFactory.post("auth/refresh") {
            contentType(ContentType.Application.Json)
            setBody(rq)
        }
        response.exposeException()
    }

    override suspend fun blockRefresh(rq: BlockRefreshRqDto): Result<Unit> = runCatching {
        val response = requestFactory.post("auth/block_refresh") {
            contentType(ContentType.Application.Json)
            setBody(rq)
        }
        response.exposeException()
    }
}