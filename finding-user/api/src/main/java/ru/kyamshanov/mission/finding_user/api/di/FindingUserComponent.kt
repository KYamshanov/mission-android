package ru.kyamshanov.mission.finding_user.api.di

import ru.kyamshanov.mission.finding_user.api.navigation.FindingUserLauncher

interface FindingUserComponent {

    val launcher: FindingUserLauncher
}