package ru.kyamshanov.mission.session_front.impl.data

import com.auth0.android.jwt.JWT
import ru.kyamshanov.mission.session_front.impl.domain.JwtTokenInteractor
import ru.kyamshanov.mission.session_front.impl.domain.model.JwtModel
import ru.kyamshanov.mission.session_front.impl.domain.model.Token
import javax.inject.Inject

/**
 * Реализация [JwtTokenInteractor] использующая [JWT]
 */
internal class JwtTokenInteractorImpl @Inject constructor() : JwtTokenInteractor {

    override fun parse(token: Token): JwtModel = JWT(token).let {
        JwtModel(
            subject = requireNotNull(it.subject) { "Subject required from token" },
            roles = it.getClaim(CLAIM_ROLES).asList(String::class.java)
        )
    }

    private companion object {

        const val CLAIM_ROLES = "roles"
    }
}

