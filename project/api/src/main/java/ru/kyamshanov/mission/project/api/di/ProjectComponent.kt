package ru.kyamshanov.mission.project.api.di

import ru.kyamshanov.mission.project.api.navigation.ProjectLauncher

interface ProjectComponent {

    val launcher: ProjectLauncher
}