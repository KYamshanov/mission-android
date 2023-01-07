package ru.kyamshanov.mission.session_front.impl.domain.usecase

import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import java.time.Instant
import java.util.Date
import javax.inject.Inject

/**
 * UseCase для получения текущй отметки времени
 */
@ComponentItem
internal class GetCurrentDateUseCase @Inject constructor() {

    /**
     * Получить текущую дату
     * @return Дату в формате [Instant]
     */
    operator fun invoke(): Date = Date()
}

