package ru.kyamshanov.mission.time.api

import java.util.Date

fun interface MissionDateFormatter {

    operator fun invoke(date: Date): String
}