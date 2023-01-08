package ru.kyamshanov.mission.base_core.impl.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kyamshanov.mission.base_core.api.di.BaseCoreComponent
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem

@Component(modules = [BindsModule::class])
@ComponentItem
internal interface ModuleComponent : BaseCoreComponent {

    val context: Context

    @Component.Factory
    interface Factory {

        fun create( @BindsInstance context: Context): ModuleComponent
    }
}