package ru.kyamshanov.mission.profile.api.di

import ru.kyamshanov.mission.profile.api.navigation.ProfileLauncher

interface ProfileComponent {

    val launcher : ProfileLauncher
}