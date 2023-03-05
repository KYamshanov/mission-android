package ru.kyamshanov.mission.profile_facade.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileFieldStyle
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileInfoMap

internal interface VerifyProfileCompletedUseCase {

    /**
     * @return Поля, которых нет в профиле
     */
    fun verify(profileInfo: ProfileInfoMap): List<ProfileFieldStyle>
}

internal class VerifyProfileCompletedUseCaseImpl @Inject constructor(
    private val profileBaseFields: List<ProfileFieldStyle>,
) : VerifyProfileCompletedUseCase {

    override fun verify(profileInfo: ProfileInfoMap) =
        profileBaseFields.filter { fieldStyle ->
            profileInfo.info[fieldStyle.key]?.takeIf { fieldStyle.type.isInstance(it) } == null
        }
}