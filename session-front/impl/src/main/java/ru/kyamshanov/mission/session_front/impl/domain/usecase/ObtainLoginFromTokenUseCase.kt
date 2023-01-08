package ru.kyamshanov.mission.session_front.impl.domain.usecase

import com.auth0.android.jwt.JWT
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.session_front.impl.domain.model.Token
import javax.inject.Inject

/**
 * UseCase для получения текущй отметки времени
 */
@ComponentItem
internal class ObtainLoginFromTokenUseCase @Inject constructor() {

    /**
     * Получить Логин пользователь из токена
     */
    operator fun invoke(token: Token): String = JWT(token).subject.orEmpty()
}

