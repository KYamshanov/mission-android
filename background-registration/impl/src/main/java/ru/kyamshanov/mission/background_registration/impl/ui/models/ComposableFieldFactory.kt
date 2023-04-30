package ru.kyamshanov.mission.background_registration.impl.ui.models

import ru.kyamshanov.mission.ui_core.R as UiR
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import javax.inject.Inject
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField.*
import ru.kyamshanov.mission.ui_core.ui.components.CellInput
import ru.kyamshanov.mission.ui_core.ui.components.EditTextField
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TextFieldCompose

internal interface ComposableFieldFactory {

    fun createFieldState(registrationField: RegistrationField): MutableState<out Any?>

    fun createComposable(
        registrationField: RegistrationField,
        fieldValueState: MutableState<out Any?>,
    ): (@Composable () -> Unit)
}

internal class ComposableFieldFactoryImpl @Inject constructor(

) : ComposableFieldFactory {

    override fun createFieldState(registrationField: RegistrationField): MutableState<out Any?> =
        when (registrationField) {
            FIRSTNAME -> mutableStateOf<String?>(null)
            LASTNMAE -> mutableStateOf<String?>(null)
            PATRONYMIC -> mutableStateOf<String?>(null)
            GROUP -> mutableStateOf<String?>(null)
        }

    @Suppress("UNCHECKED_CAST")
    override fun createComposable(
        registrationField: RegistrationField,
        fieldValueState: MutableState<out Any?>,
    ): (@Composable () -> Unit) {
        when (registrationField) {
            FIRSTNAME -> return {
                TextRegistrationField(
                    stringResource(id = UiR.string.first_name),
                    fieldValueState as MutableState<String?>
                )
            }
            LASTNMAE -> return {
                TextRegistrationField(
                    "Фамилия",
                    fieldValueState as MutableState<String?>
                )
            }
            PATRONYMIC -> return {
                TextRegistrationField(
                    "Отчество",
                    fieldValueState as MutableState<String?>
                )
            }
            GROUP -> return {
                TextRegistrationField(
                    "Группа",
                    fieldValueState as MutableState<String?>
                )
            }
        }
    }

    @Composable
    private fun TextRegistrationField(label: String, fieldValueState: MutableState<String?>) {
        EditTextField(
            text = fieldValueState.value.orEmpty(),
            onValueChange = { value -> fieldValueState.value = value },
            label = label,
            editable = true
        )
    }
}