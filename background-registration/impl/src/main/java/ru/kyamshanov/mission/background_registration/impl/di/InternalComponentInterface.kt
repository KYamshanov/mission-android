package ru.kyamshanov.mission.background_registration.impl.di

import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory

/**
 * Внутренний интерфейс компоненты
 */
internal interface InternalComponentInterface : BackgroundRegistrationComponent {

    val composableFieldFactory: ComposableFieldFactory
}