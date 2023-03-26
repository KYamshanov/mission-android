package ru.kyamshanov.mission.ui_core.ui.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun interface MissionDateFormatter {

    operator fun invoke(date: Date): String
}

class ComplexMissionDateFormatter {

    fun toDdMmYy(date: Date): String =
        DdMnYyFormat.format(date)

    private companion object {

        val DdMnYyFormat = SimpleDateFormat("d MM yyyy", Locale("ru", "RU"))
    }
}