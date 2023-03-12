package ru.kyamshanov.mission.background_registration.impl.di

import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.background_registration.impl.ui.viewmodel.ViewModelSupplier

/**
 * Внутренний интерфейс компоненты
 */
internal interface InternalComponentInterface : BackgroundRegistrationComponent {

    val composableFieldFactory: ComposableFieldFactory

    val viewModelSupplier : ViewModelSupplier
}