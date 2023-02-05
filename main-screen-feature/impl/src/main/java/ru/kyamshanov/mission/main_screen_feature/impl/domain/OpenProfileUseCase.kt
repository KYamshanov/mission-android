package ru.kyamshanov.mission.main_screen_feature.impl.domain

import android.util.Log
import ru.kyamshanov.mission.profile.api.navigation.ProfileLauncher
import javax.inject.Inject
import kotlin.jvm.Throws

internal interface OpenProfileUseCase {

    @Throws()
    fun openProfile()
}

internal class OpenProfileUseCaseImpl @Inject constructor(
    private val profileLauncher: ProfileLauncher
) : OpenProfileUseCase {

    override fun openProfile() {
        runCatching { profileLauncher.launch() }
            .onFailure { throwable ->
                Log.e(LOG_TAG, "An error occurred when opening the profile", throwable)
                throw throwable
            }
    }

    private companion object {

        const val LOG_TAG = "OpenProfileUseCaseImpl"
    }
}