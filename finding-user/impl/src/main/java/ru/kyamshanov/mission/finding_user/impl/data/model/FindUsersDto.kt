package ru.kyamshanov.mission.finding_user.impl.data.model

import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo

internal data class FindUsersRqDto(
    val name: String,
    val age: Int?,
)

internal data class FindUsersRsDto(
    val users: List<FoundUserInfo>,
) {

    data class FoundUserInfo(
        val id: String,
        val firstname: String?,
        val lastname: String?,
        val patronymic: String?,
        val group: String?
    )
}