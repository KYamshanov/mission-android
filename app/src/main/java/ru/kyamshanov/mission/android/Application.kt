package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.authentication.impl.di.AuthenticationComponentBuilder
import ru.kyamshanov.mission.background_registration.impl.di.BackgroundRegistrationComponentBuilder
import ru.kyamshanov.mission.base_core.impl.di.BaseCoreComponentBuilder
import ru.kyamshanov.mission.creating_project.impl.di.CreatingProjectComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.impl.di.FindingUserComponentBuilder
import ru.kyamshanov.mission.main_screen_feature.impl.di.MainScreenComponentBuilder
import ru.kyamshanov.mission.navigation_core.impl.di.NavigationComponentBuilder
import ru.kyamshanov.mission.network_core.impl.di.NetworkComponentBuilder
import ru.kyamshanov.mission.profile.impl.di.ProfileComponentBuilder
import ru.kyamshanov.mission.profile_facade.impl.di.ProfileFacadeComponentBuilder
import ru.kyamshanov.mission.project.task.creation.impl.di.ProjectTaskCreationComponentBuilder
import ru.kyamshanov.mission.project_view.impl.di.ProjectComponentBuilder
import ru.kyamshanov.mission.search_project.impl.di.SearchProjectFacadeComponentBuilder
import ru.kyamshanov.mission.session_front.impl.di.SessionFrontComponentBuilder
import ru.kyamshanov.mission.task.set_points.impl.di.SetPointsComponentBuilder
import ru.kyamshanov.mission.task.view.impl.di.TaskViewComponentBuilder

internal class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        regBaseDependencies()
    }

    private fun regBaseDependencies() {
        Di.registration(BaseCoreComponentBuilder(applicationContext))
        Di.registration(NavigationComponentBuilder())
        Di.registration(MainScreenComponentBuilder())
        Di.registration(AuthenticationComponentBuilder())
        Di.registration(SessionFrontComponentBuilder())
        Di.registration(NetworkComponentBuilder())
        Di.registration(ProfileComponentBuilder())
        Di.registration(CreatingProjectComponentBuilder())
        Di.registration(FindingUserComponentBuilder())
        Di.registration(ProjectComponentBuilder())
        Di.registration(ProfileFacadeComponentBuilder())
        Di.registration(BackgroundRegistrationComponentBuilder())
        Di.registration(SearchProjectFacadeComponentBuilder())
        Di.registration(ProjectTaskCreationComponentBuilder())
        Di.registration(TaskViewComponentBuilder())
        Di.registration(SetPointsComponentBuilder())
    }
}