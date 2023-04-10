package ru.kyamshanov.mission.task.view.api.di

import ru.kyamshanov.mission.task.view.api.navigation.TaskViewLauncher

interface TaskViewComponent {

    val launcher : TaskViewLauncher
}