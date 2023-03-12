package ru.kyamshanov.mission.background_registration.impl.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.background_registration.impl.domain.model.RegistrationModel
import ru.kyamshanov.mission.background_registration.impl.domain.usecase.CompleteRegistrationUseCase
import ru.kyamshanov.mission.background_registration.impl.ui.model.BackgroundRegistrationScreenState
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.navigation_core.api.Navigator

internal class BackgroundRegistrationViewModel(
    requiredFields: List<RegistrationField>,
    private val composableFieldFactory: ComposableFieldFactory,
    private val completeRegistrationUseCase: CompleteRegistrationUseCase,
    private val navigator: Navigator,
) : ViewModel() {

    private val _screenState = MutableStateFlow(BackgroundRegistrationScreenState())
    val screenState = _screenState.asStateFlow()

    init {
        _screenState.apply {
            val fields = requiredFields.associateWith { field -> composableFieldFactory.createFieldState(field) }
            value = value.copy(fields = fields)
        }
    }

    fun completeRegistration() {
        viewModelScope.launch {
            val registrationModel = prepareRegistrationModel()
            completeRegistrationUseCase.perform(registrationModel)
                .onSuccess {
                    _screenState.apply {
                        value = value.copy(successfullyRegistered = true)
                    }
                }
                .onFailure {
                    _screenState.apply {
                        value = value.copy(somethingWentWrong = true)
                    }
                }
        }
    }

    fun hideSomethingWentWrongDialog() {
        _screenState.apply {
            value = value.copy(somethingWentWrong = false)
        }
    }

    fun close() {
        _screenState.apply {
            value = value.copy(somethingWentWrong = false, successfullyRegistered = false)
        }
        navigator.exit()
    }

    private fun prepareRegistrationModel() = _screenState.value.fields.let { fields ->
        RegistrationModel(
            name = fields[RegistrationField.NAME]?.value as? String,
            age = fields[RegistrationField.AGE]?.value as? Int
        )
    }
}