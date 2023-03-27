package ru.kyamshanov.mission.time.impl

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComplexMissionDateFormatter {

    fun toDdMmYy(date: Date): String =
        DdMnYyFormat.format(date)

    private companion object {

        val DdMnYyFormat = SimpleDateFormat("d MM yyyy", Locale("ru", "RU"))
    }
}