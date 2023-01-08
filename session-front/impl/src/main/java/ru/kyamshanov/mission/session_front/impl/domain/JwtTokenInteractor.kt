package ru.kyamshanov.mission.session_front.impl.domain

import ru.kyamshanov.mission.session_front.impl.domain.model.JwtModel
import ru.kyamshanov.mission.session_front.impl.domain.model.Token

/**
 * Интеграктор для Jwt
 */
internal interface JwtTokenInteractor {

    /**
     * Получить [JwtModel] из токена
     * @param token Токен
     * @return []
     */
    fun parse(token: Token): JwtModel
}

