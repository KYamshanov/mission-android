package ru.kyamshanov.mission.project.task.creation.impl.domain

import ru.kyamshanov.mission.ui_core.ui.utils.MissionDateFormatter

internal interface DateFormatterProvider {

    fun cellDateFormatter(): MissionDateFormatter
}