package ru.kyamshanov.mission.authentication.di

import ru.kyamshanov.mission.authentication.domain.AuthenticationLauncher

interface AuthenticationComponent {

    val launcher: AuthenticationLauncher
}