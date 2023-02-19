package ru.kyamshanov.mission.android

import ru.kyamshanov.mission.authentication.impl.di.AuthenticationComponentBuilder
import ru.kyamshanov.mission.base_core.impl.di.BaseCoreComponentBuilder
import ru.kyamshanov.mission.creating_project.impl.di.CreatingProjectComponentBuilder
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.finding_user.impl.di.FindingUserComponentBuilder
import ru.kyamshanov.mission.main_screen_feature.impl.di.MainScreenComponentBuilder
import ru.kyamshanov.mission.network_core.impl.di.NetworkComponentBuilder
import ru.kyamshanov.mission.profile.impl.di.ProfileComponentBuilder
import ru.kyamshanov.mission.session_front.impl.di.SessionFrontComponentBuilder

internal class Application : android.app.Application() {

    override fun onCreate() {
        super.onCreate()
        regBaseDependencies()
    }

    private fun regBaseDependencies() {
        Di.registration(BaseCoreComponentBuilder(applicationContext))
        Di.registration(MainScreenComponentBuilder())
        Di.registration(AuthenticationComponentBuilder())
        Di.registration(SessionFrontComponentBuilder())
        Di.registration(NetworkComponentBuilder())
        Di.registration(ProfileComponentBuilder())
        Di.registration(CreatingProjectComponentBuilder())
        Di.registration(FindingUserComponentBuilder())
    }
}