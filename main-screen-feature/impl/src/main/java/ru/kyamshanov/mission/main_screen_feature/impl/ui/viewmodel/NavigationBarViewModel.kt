package ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import ru.kyamshanov.mission.main_screen_feature.impl.domain.OpenCreatingProjectScreenUseCase
import ru.kyamshanov.mission.main_screen_feature.impl.domain.OpenProfileUseCase

/**
 * ВьюМодель панели навигации
 */
internal class NavigationBarViewModel @Inject constructor(
    private val openProfileUseCase: OpenProfileUseCase,
    private val openCreatingProjectScreenUseCase: OpenCreatingProjectScreenUseCase
) : ViewModel() {

    fun openProfile() {
        openProfileUseCase.openProfile()
    }

    fun openCreatingProjectScreen() {
        openCreatingProjectScreenUseCase.open()
    }
}