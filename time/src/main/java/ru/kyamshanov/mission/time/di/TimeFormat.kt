package ru.kyamshanov.mission.time.di

import javax.inject.Qualifier

@Qualifier
annotation class TimeFormatQualifier(
    val value: TimeFormat,
)

enum class TimeFormat {

    DD_MN_YY,
}