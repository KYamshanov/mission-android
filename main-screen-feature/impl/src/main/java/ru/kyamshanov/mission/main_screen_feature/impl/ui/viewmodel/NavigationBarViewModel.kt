package ru.kyamshanov.mission.main_screen_feature.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import ru.kyamshanov.mission.main_screen_feature.impl.domain.OpenProfileUseCase
import javax.inject.Inject

/**
 * ВьюМодель панели навигации
 */
internal class NavigationBarViewModel @Inject constructor(
    private val openProfileUseCase: OpenProfileUseCase
) : ViewModel() {

    fun openProfile() {
        openProfileUseCase.openProfile()
    }
}