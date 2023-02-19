package ru.kyamshanov.mission.creating_project.impl.domain

import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import javax.inject.Inject

interface OpenFindingUserScreenUseCase {

    fun open()
}

internal class OpenFindingUserScreenUseCaseImpl @Inject constructor(
    private val findingUserLauncher: FindingUserLauncher
) : OpenFindingUserScreenUseCase {

    override fun open() {
        findingUserLauncher.launch()
    }
}