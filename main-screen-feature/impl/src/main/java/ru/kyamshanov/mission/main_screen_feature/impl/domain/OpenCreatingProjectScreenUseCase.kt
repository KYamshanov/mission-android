package ru.kyamshanov.mission.main_screen_feature.impl.domain

import android.util.Log
import ru.kyamshanov.mission.creating_project.api.navigation.CreatingProjectLauncher
import javax.inject.Inject

internal interface OpenCreatingProjectScreenUseCase {

    @Throws()
    fun open()
}

internal class OpenCreatingProjectScreenUseCaseImpl @Inject constructor(
    private val launcher: CreatingProjectLauncher
) : OpenCreatingProjectScreenUseCase {

    override fun open() {
        runCatching { launcher.launch() }
            .onFailure { throwable ->
                Log.e(LOG_TAG, "An error occurred when opening the profile", throwable)
                throw throwable
            }
    }

    private companion object {

        const val LOG_TAG = "OpenProfileUseCaseImpl"
    }
}