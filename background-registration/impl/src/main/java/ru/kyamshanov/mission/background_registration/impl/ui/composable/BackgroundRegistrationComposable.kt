package ru.kyamshanov.mission.background_registration.impl.ui.composable

import ru.kyamshanov.mission.ui_core.R as UiR
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.api.domain.models.RegistrationField
import ru.kyamshanov.mission.background_registration.impl.di.InternalComponentInterface
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.di_dagger.impl.Di

@Composable
internal fun BackgroundRegistrationComposable(
    moduleComponent: InternalComponentInterface = requireNotNull(Di.getInternalComponent<BackgroundRegistrationComponent, InternalComponentInterface>()),

    backgroundRegistrationBoundaryData: BackgroundRegistrationBoundaryData,
    composableFieldFactory: ComposableFieldFactory = moduleComponent.composableFieldFactory,
) {

    RegistrationScreenComposable(backgroundRegistrationBoundaryData.requiredFields, composableFieldFactory)
}

@Composable
internal fun RegistrationScreenComposable(
    requiredFields: List<RegistrationField>,
    composableFieldFactory: ComposableFieldFactory,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Регистрация профиля")
        val valueStates: Map<RegistrationField, MutableState<out Any?>> =
            requiredFields.associateWith { field -> rememberSaveable { composableFieldFactory.createFieldState(field) } }
        LazyColumn {
            valueStates.forEach { (field, state) ->
                item { composableFieldFactory.createComposable(field, state).invoke() }
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = UiR.string.save))
        }
    }
}