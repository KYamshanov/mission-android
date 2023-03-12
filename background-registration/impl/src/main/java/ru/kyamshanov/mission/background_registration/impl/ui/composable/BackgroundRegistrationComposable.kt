package ru.kyamshanov.mission.background_registration.impl.ui.composable

import ru.kyamshanov.mission.ui_core.R as UiR
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.impl.R
import ru.kyamshanov.mission.background_registration.impl.di.InternalComponentInterface
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.background_registration.impl.ui.viewmodel.BackgroundRegistrationViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.ui_core.ui.components.SomethingWentWrongDialog

@Composable
internal fun BackgroundRegistrationComposable(
    moduleComponent: InternalComponentInterface = requireNotNull(Di.getInternalComponent<BackgroundRegistrationComponent, InternalComponentInterface>()),

    backgroundRegistrationBoundaryData: BackgroundRegistrationBoundaryData,
    composableFieldFactory: ComposableFieldFactory = moduleComponent.composableFieldFactory,
    backgroundRegistrationViewModel: BackgroundRegistrationViewModel =
        viewModel {
            moduleComponent.viewModelSupplier
                .createBackgroundRegistrationViewModel(backgroundRegistrationBoundaryData.requiredFields)
        },
) {

    RegistrationScreenComposable(backgroundRegistrationViewModel, composableFieldFactory)
}

@Composable
internal fun RegistrationScreenComposable(
    backgroundRegistrationViewModel: BackgroundRegistrationViewModel,
    composableFieldFactory: ComposableFieldFactory,
) {
    val screenState by backgroundRegistrationViewModel.screenState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val valueStates = screenState.fields
        Text(text = "Регистрация профиля")
        LazyColumn {
            valueStates.forEach { (field, state) ->
                item { composableFieldFactory.createComposable(field, state).invoke() }
            }
        }
        Button(onClick = { backgroundRegistrationViewModel.completeRegistration() }) {
            Text(text = stringResource(id = UiR.string.save))
        }
    }

    if (screenState.somethingWentWrong) {
        SomethingWentWrongDialog(
            onDismissRequest = { backgroundRegistrationViewModel.hideSomethingWentWrongDialog() },
            onConfirm = { backgroundRegistrationViewModel.hideSomethingWentWrongDialog() }
        )
    }

    if (screenState.somethingWentWrong) {
        SomethingWentWrongDialog(
            onDismissRequest = { backgroundRegistrationViewModel.hideSomethingWentWrongDialog() },
            onConfirm = { backgroundRegistrationViewModel.hideSomethingWentWrongDialog() }
        )
    }

    if (screenState.successfullyRegistered) {
        SuccessfullyRegistered(
            onDismissRequest = { backgroundRegistrationViewModel.close() },
            onConfirm = { backgroundRegistrationViewModel.close() }
        )
    }
}

@Composable
private fun SuccessfullyRegistered(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        text = { Text(stringResource(id = R.string.br_successfully_registered_msg)) },
        modifier = modifier,
        confirmButton = { Button(onClick = onConfirm) { Text(text = stringResource(id = UiR.string.clearly)) } }
    )
}