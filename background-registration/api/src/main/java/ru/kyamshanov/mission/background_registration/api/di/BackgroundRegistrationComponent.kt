package ru.kyamshanov.mission.background_registration.api.di

import ru.kyamshanov.mission.background_registration.api.ui.navigation.BackgroundRegistrationLauncher

interface BackgroundRegistrationComponent {

    val launcher : BackgroundRegistrationLauncher
}