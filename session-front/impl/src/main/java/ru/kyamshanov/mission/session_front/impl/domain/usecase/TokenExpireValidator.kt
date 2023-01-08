package ru.kyamshanov.mission.session_front.impl.domain.usecase

import com.auth0.android.jwt.JWT
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.impl.domain.model.Token
import javax.inject.Inject

@ComponentItem
internal class TokenExpireValidator @Inject constructor(
    private val getCurrentDateUseCase: GetCurrentDateUseCase
) {

    /**
     * @return true - Токен активный
     */
    operator fun invoke(token: Token): Boolean =
        JWT(token).expiresAt?.let { it >= getCurrentDateUseCase.invoke() } ?: false
}