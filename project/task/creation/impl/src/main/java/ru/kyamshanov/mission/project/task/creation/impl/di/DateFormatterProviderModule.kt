package ru.kyamshanov.mission.project.task.creation.impl.di

import dagger.Module
import dagger.Provides
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.project.task.creation.impl.domain.DateFormatterProvider
import ru.kyamshanov.mission.ui_core.ui.utils.ComplexMissionDateFormatter
import ru.kyamshanov.mission.ui_core.ui.utils.MissionDateFormatter

@Module
internal class DateFormatterProviderModule {

    @Provides
    @ComponentItem
    fun provideComplexFormatter() = ComplexMissionDateFormatter()

    @Provides
    @ComponentItem
    fun provideDateFormatterProvider(
        complexFormatterProvider: ComplexMissionDateFormatter,
    ): DateFormatterProvider = object : DateFormatterProvider {

        override fun cellDateFormatter() = MissionDateFormatter(complexFormatterProvider::toDdMmYy)
    }
}