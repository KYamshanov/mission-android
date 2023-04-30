package ru.kyamshanov.mission.finding_user.impl.data.converter

import javax.inject.Inject
import ru.kyamshanov.mission.core.common.Converter
import ru.kyamshanov.mission.finding_user.impl.data.model.ParticipantDto
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo

internal class ParticipantConverter @Inject constructor() : Converter<ParticipantDto, UserInfo> {

    override fun invoke(source: ParticipantDto) = UserInfo(
        id = source.userId,
        name = source.userLogin
    )
}