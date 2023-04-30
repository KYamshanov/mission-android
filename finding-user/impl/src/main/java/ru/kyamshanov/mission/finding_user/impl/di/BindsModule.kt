package ru.kyamshanov.mission.finding_user.impl.di

import dagger.Binds
import ru.kyamshanov.mission.core.common.Converter
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher
import ru.kyamshanov.mission.finding_user.impl.data.api.ProfileApi
import ru.kyamshanov.mission.finding_user.impl.data.api.ProfileApiImpl
import ru.kyamshanov.mission.finding_user.impl.data.api.ProjectApi
import ru.kyamshanov.mission.finding_user.impl.data.api.ProjectApiImpl
import ru.kyamshanov.mission.finding_user.impl.data.converter.ParticipantConverter
import ru.kyamshanov.mission.finding_user.impl.data.converter.UserInfoConverter
import ru.kyamshanov.mission.finding_user.impl.data.model.FindUsersRsDto
import ru.kyamshanov.mission.finding_user.impl.data.model.ParticipantDto
import ru.kyamshanov.mission.finding_user.impl.data.repository.UserRepositoryImpl
import ru.kyamshanov.mission.finding_user.impl.domain.model.UserInfo
import ru.kyamshanov.mission.finding_user.impl.domain.repository.UserRepository
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCaseFactory
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.ObtainUserUseCaseImpl
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCase
import ru.kyamshanov.mission.finding_user.impl.domain.usecase.SelectUserUseCaseImpl
import ru.kyamshanov.mission.finding_user.impl.ui.navigation.FindingUserLauncherImpl

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun FindingUserLauncherImpl.bind(): FindingUserLauncher

    @Binds
    @ComponentItem
    fun UserRepositoryImpl.bindUserRepository(): UserRepository

    @Binds
    @ComponentItem
    fun ObtainUserUseCaseImpl.Factory.bindObtainUserUseCaseFactory(): ObtainUserUseCaseFactory

    @Binds
    @ComponentItem
    fun SelectUserUseCaseImpl.bindSelectUserUseCase(): SelectUserUseCase

    @Binds
    @ComponentItem
    fun ProfileApiImpl.bindProfileApi(): ProfileApi

    @Binds
    @ComponentItem
    fun ProjectApiImpl.bindProjectApi(): ProjectApi

    @Binds
    @ComponentItem
    fun UserInfoConverter.bindUserInfoConverter(): Converter<FindUsersRsDto.FoundUserInfo, UserInfo>

    @Binds
    @ComponentItem
    fun ParticipantConverter.bindParticipantConverter(): Converter<ParticipantDto, UserInfo>
}