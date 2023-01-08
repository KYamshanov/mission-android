package ru.kyamshanov.mission.base_core.impl.di

import dagger.Binds
import ru.kyamshanov.mission.base_core.api.Device
import ru.kyamshanov.mission.base_core.api.MissionPreferences
import ru.kyamshanov.mission.base_core.impl.ui.DeviceImpl
import ru.kyamshanov.mission.base_core.impl.ui.MissionPreferencesImpl
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@dagger.Module
internal interface BindsModule {

    @Binds
    @ComponentItem
    fun bindSharedPreferences(impl: MissionPreferencesImpl): MissionPreferences

    @Binds
    @ComponentItem
    fun bindDevice(impl: DeviceImpl): Device
}