package ru.kyamshanov.mission.project_view.api.di

import ru.kyamshanov.mission.project_view.api.navigation.ProjectLauncher

interface ProjectComponent {

    val launcher: ProjectLauncher
}