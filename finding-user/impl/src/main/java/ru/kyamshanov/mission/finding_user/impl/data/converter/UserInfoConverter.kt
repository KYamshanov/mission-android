package ru.kyamshanov.mission.finding_user.impl.data.converter

import javax.inject.Inject
import ru.kyamshanov.mission.core.common.Converter
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRsDto
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo

internal class UserInfoConverter @Inject constructor(): Converter<FindUsersRsDto.FoundUserInfo, UserInfo> {

    override fun invoke(source: FindUsersRsDto.FoundUserInfo) = UserInfo(
        id = source.id,
        name = formatName(source)
    )

    private fun formatName(info: FindUsersRsDto.FoundUserInfo) = buildString {
        append(info.firstname ?: "Аноним")
        info.lastname?.let { name ->
            append(' ')
            append(name)
        }

        info.lastname?.let { name ->
            append(' ')
            append(name[0])
            append('.')
        }

        info.group?.let { group ->
            append(' ')
            append(group)
        }
    }
}