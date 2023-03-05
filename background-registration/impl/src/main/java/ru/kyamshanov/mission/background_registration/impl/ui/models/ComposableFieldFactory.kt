package ru.kyamshanov.mission.background_registration.impl.ui.models

import ru.kyamshanov.mission.ui_core.R as UiR
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField

internal interface ComposableFieldFactory {

    fun create(registrationField: RegistrationField, fieldValueState: MutableState<Any?>): (@Composable () -> Unit)
}

internal class ComposableFieldFactoryImpl @Inject constructor(

) : ComposableFieldFactory {

    @Suppress("UNCHECKED_CAST")
    override fun create(
        registrationField: RegistrationField,
        fieldValueState: MutableState<Any?>,
    ): (@Composable () -> Unit) {
        when (registrationField) {
            RegistrationField.AGE -> return {
                TextRegistrationField(stringResource(id = UiR.string.age), fieldValueState as MutableState<String?>)
            }
            RegistrationField.NAME -> return {
                TextRegistrationField(
                    stringResource(id = UiR.string.first_name),
                    fieldValueState as MutableState<String?>
                )
            }
            else -> throw IllegalArgumentException("RegistrationField $registrationField is not supported")
        }
    }

    @Composable
    private fun TextRegistrationField(label: String, fieldValueState: MutableState<String?>) {
        TextField(
            value = fieldValueState.value.orEmpty(),
            onValueChange = { value -> fieldValueState.value = value },
            enabled = false,
            label = { Text(text = label) },
        )
    }
}