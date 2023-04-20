package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.authentication.impl.ui.screen.AuthenticationScreen
import ru.kyamshanov.mission.background_registration.impl.ui.screen.BackgroundRegistrationScreen
import ru.kyamshanov.mission.creating_project.impl.ui.screen.CreatingProjectScreen
import ru.kyamshanov.mission.finding_user.impl.ui.screen.FindingUserScreen
import ru.kyamshanov.mission.main_screen_feature.impl.ui.screen.MainScreen
import ru.kyamshanov.mission.navigation_core.api.Screen
import ru.kyamshanov.mission.profile.impl.ui.screen.ProfileScreen
import ru.kyamshanov.mission.project.task.creation.impl.ui.screen.ProjectTaskCreationScreen
import ru.kyamshanov.mission.project_view.impl.ui.screen.ProjectScreen
import ru.kyamshanov.mission.task.set_points.impl.ui.screen.SetPointsScreen
import ru.kyamshanov.mission.task.view.impl.ui.screen.SubtaskCreationScreen
import ru.kyamshanov.mission.task.view.impl.ui.screen.SubtaskViewScreen
import ru.kyamshanov.mission.task.view.impl.ui.screen.TaskViewScreen

internal class ComposableScreensProvider : ScreensProvider {

    override fun supply(): Collection<Screen> = listOf(
        AuthenticationScreen(),
        MainScreen(),
        ProfileScreen(),
        CreatingProjectScreen(),
        FindingUserScreen(),
        ProjectScreen(),
        BackgroundRegistrationScreen(),
        ProjectTaskCreationScreen(),
        TaskViewScreen(),
        SetPointsScreen(),
        SubtaskCreationScreen(),
        SubtaskViewScreen(),
    )
}