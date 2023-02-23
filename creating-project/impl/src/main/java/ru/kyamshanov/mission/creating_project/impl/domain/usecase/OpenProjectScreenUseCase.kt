package ru.kyamshanov.mission.creating_project.impl.domain.usecase

import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import javax.inject.Inject

interface OpenProjectScreenUseCase {

    fun open()
}

internal class OpenProjectScreenUseCaseImpl @Inject constructor(
    private val findingUserLauncher: FindingUserLauncher
) : OpenProjectScreenUseCase {

    override fun open() {
        findingUserLauncher.launch()
    }
}