package ru.kyamshanov.mission.project.task.creation.api.di

import ru.kyamshanov.mission.project.task.creation.api.navigation.ProjectTaskCreationLauncher

interface ProjectTaskCreationComponent {

    val launcher : ProjectTaskCreationLauncher
}