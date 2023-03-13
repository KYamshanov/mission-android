package ru.kyamshanov.mission.finding_user.impl.domain.usecase

import javax.inject.Inject
import ru.kyamshanov.mission.finding_user.api.navigation.SELECTED_USER_EXTRA_KEY
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.navigation_core.api.Navigator

internal interface SelectUserUseCase {

    fun select(userInfo: UserInfo)
}

internal class SelectUserUseCaseImpl @Inject constructor(
    private val navigator: Navigator,
) : SelectUserUseCase {

    override fun select(userInfo: UserInfo) {
        navigator.backWithResult(
            key = SELECTED_USER_EXTRA_KEY,
            data = userInfo
        )
    }
}