package ru.kyamshanov.mission.background_registration.impl.ui.viewmodel

import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.background_registration.impl.domain.usecase.CompleteRegistrationUseCase
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.di_dagger.impl.ComponentItem
import ru.kyamshanov.mission.navigation_core.api.Navigator

@ComponentItem
internal class ViewModelSupplier @Inject constructor(
    private val composableFieldFactory: ComposableFieldFactory,
    private val completeRegistrationUseCase: CompleteRegistrationUseCase,
    private val navigator: Navigator,
) {

    fun createBackgroundRegistrationViewModel(
        requiredFields: List<RegistrationField>,
    ) = BackgroundRegistrationViewModel(
        requiredFields = requiredFields,
        composableFieldFactory = composableFieldFactory,
        completeRegistrationUseCase = completeRegistrationUseCase,
        navigator = navigator,
    )
}