package ru.kyamshanov.mission.time.di

import dagger.Module
import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.time.api.DateFormatterProvider
import ru.kyamshanov.mission.time.api.MissionDateFormatter
import ru.kyamshanov.mission.time.impl.ComplexMissionDateFormatter

@Module
class TimeFormatterModule {

    @Provides
    @ComponentItem
    fun provideComplexMissionDateFormatter(): ComplexMissionDateFormatter =
        ComplexMissionDateFormatter()

    @Provides
    @ComponentItem
    @TimeFormatQualifier(TimeFormat.DD_MN_YY)
    fun provideDdMmYy(
        complexFormatter: ComplexMissionDateFormatter,
    ): MissionDateFormatter = MissionDateFormatter { complexFormatter.toDdMmYy(it) }

    @Provides
    @ComponentItem
    fun provideDateFormatterProvider(
        @TimeFormatQualifier(TimeFormat.DD_MN_YY) cellDateFormatter: MissionDateFormatter,
    ): DateFormatterProvider = object : DateFormatterProvider {

        override fun cellDateFormatter(): MissionDateFormatter = cellDateFormatter
    }
}