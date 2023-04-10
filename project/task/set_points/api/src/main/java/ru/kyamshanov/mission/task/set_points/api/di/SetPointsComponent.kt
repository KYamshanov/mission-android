package ru.kyamshanov.mission.task.set_points.api.di

import ru.kyamshanov.mission.task.set_points.api.navigation.SetPointsLauncher

interface SetPointsComponent {

    val launcher : SetPointsLauncher
}