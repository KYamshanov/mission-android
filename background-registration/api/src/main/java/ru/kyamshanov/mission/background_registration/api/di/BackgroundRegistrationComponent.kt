package ru.kyamshanov.mission.background_registration.api.di

import ru.kyamshanov.mission.background_registration.api.navigation.BackgroundRegistrationLauncher

interface BackgroundRegistrationComponent {

    val launcher : BackgroundRegistrationLauncher
}