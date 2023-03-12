package ru.kyamshanov.mission.background_registration.impl.data.data.api

import ru.kyamshanov.mission.background_registration.impl.data.data.model.BackRegisterRqDto

internal interface ProfileApi {

    suspend fun backRegister(body: BackRegisterRqDto)
}