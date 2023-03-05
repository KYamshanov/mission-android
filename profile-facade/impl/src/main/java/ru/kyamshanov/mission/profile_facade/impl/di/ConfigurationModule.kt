package ru.kyamshanov.mission.profile_facade.impl.di

import dagger.Module
import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_AGE_KEY
import ru.kyamshanov.mission.profile_facade.impl.domain.model.PROFILE_NAME_KEY
import ru.kyamshanov.mission.profile_facade.impl.domain.model.ProfileFieldStyle

@Module
internal class ConfigurationModule {

    @Provides
    @ComponentItem
    fun provideRequiredBaseFields(): List<ProfileFieldStyle> = listOf(
        ProfileFieldStyle(PROFILE_AGE_KEY, Number::class),
        ProfileFieldStyle(PROFILE_NAME_KEY, String::class)
    )
}