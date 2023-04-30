package ru.kyamshanov.mission.profile_facade.impl.di

import dagger.Module
import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_FIRSTNAME
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_GROUP
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_LASTNAME
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_PATRONYMIC
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileFieldStyle

@Module
internal class ConfigurationModule {

    @Provides
    @ComponentItem
    fun provideRequiredBaseFields(): List<ProfileFieldStyle> = listOf(
        ProfileFieldStyle(PROFILE_FIRSTNAME, String::class),
        ProfileFieldStyle(PROFILE_LASTNAME, String::class),
        ProfileFieldStyle(PROFILE_PATRONYMIC, String::class),
        ProfileFieldStyle(PROFILE_GROUP, String::class),
    )
}