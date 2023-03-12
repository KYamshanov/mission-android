package ru.kyamshanov.mission.background_registration.impl.ui.model

import androidx.compose.runtime.MutableState
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField

data class BackgroundRegistrationScreenState(
    val fields: Map<RegistrationField, MutableState<out Any?>>,
    val successfullyRegistered: Boolean,
    val somethingWentWrong: Boolean,
) {

    constructor() : this(
        fields = emptyMap(),
        successfullyRegistered = false,
        somethingWentWrong = false
    )
}