package ru.kyamshanov.mission.profile_facade.api.di

import ru.kyamshanov.mission.dagger.CoreComponent
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase

interface ProfileFacadeComponent : CoreComponent {

    val getProfileUseCase: GetProfileUseCase
}