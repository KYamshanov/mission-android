package ru.kyamshanov.mission.base_core.impl.di

import android.content.Context
import ru.kyamshanov.mission.base_core.api.di.BaseCoreComponent
import ru.kyamshanov.mission.dagger.ComponentBuilder

class BaseCoreComponentBuilder(
    private val applicationContext: Context
) : ComponentBuilder<BaseCoreComponent> {

    override fun build(): BaseCoreComponent =
        DaggerModuleComponent.factory()
            .create(context = applicationContext)
}