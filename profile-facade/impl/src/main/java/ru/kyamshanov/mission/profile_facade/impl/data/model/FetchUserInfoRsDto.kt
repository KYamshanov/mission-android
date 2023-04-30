package ru.kyamshanov.mission.profile_facade.impl.data.model

internal data class FetchUserInfoRsDto(
    val userInfo: UserInfoDto,
    val requiredField: List<UserInfoFieldDto>
)