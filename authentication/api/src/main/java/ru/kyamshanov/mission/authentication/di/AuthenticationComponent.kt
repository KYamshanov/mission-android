package ru.kyamshanov.mission.authentication.di

import ru.kyamshanov.mission.authentication.AuthenticationLauncher

interface AuthenticationComponent {

    val launcher: AuthenticationLauncher
}