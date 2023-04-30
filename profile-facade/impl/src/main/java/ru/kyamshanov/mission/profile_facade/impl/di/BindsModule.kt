package ru.kyamshanov.mission.profile_facade.impl.di

import dagger.Binds
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.profile_facade.api.domain.interactor.VerifyingProfileInteractor
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase
import ru.kyamshanov.mission.profile_facade.impl.data.api.ProfileApi
import ru.kyamshanov.mission.profile_facade.impl.data.api.ProfileApiImpl
import ru.kyamshanov.mission.profile_facade.impl.data.repository.ProfileStorableRepositoryImpl
import ru.kyamshanov.mission.profile_facade.impl.domain.interactor.VerifyingProfileInteractorImpl
import ru.kyamshanov.mission.profile_facade.impl.domain.repository.ProfileStorableRepository
import ru.kyamshanov.mission.profile_facade.impl.domain.usecase.GetProfileUseCaseImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun ProfileStorableRepositoryImpl.bindProfileStorableRepository(): ProfileStorableRepository

    @Binds
    @ComponentItem
    fun ProfileApiImpl.bindProfileApi(): ProfileApi

    @Binds
    @ComponentItem
    fun GetProfileUseCaseImpl.bindGetProfileUseCase(): GetProfileUseCase

    @Binds
    @ComponentItem
    fun VerifyingProfileInteractorImpl.bindVerifyingProfileInteractor(): VerifyingProfileInteractor
}