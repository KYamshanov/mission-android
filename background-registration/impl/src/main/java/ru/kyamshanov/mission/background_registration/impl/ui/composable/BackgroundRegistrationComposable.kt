package ru.kyamshanov.mission.background_registration.impl.ui.composable

import ru.kyamshanov.mission.ui_core.R as UiR
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.kyamshanov.mission.background_registration.api.di.BackgroundRegistrationComponent
import ru.kyamshanov.mission.background_registration.impl.R
import ru.kyamshanov.mission.background_registration.impl.di.InternalComponentInterface
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData
import ru.kyamshanov.mission.background_registration.impl.ui.models.ComposableFieldFactory
import ru.kyamshanov.mission.background_registration.impl.ui.viewmodel.BackgroundRegistrationViewModel
import ru.kyamshanov.mission.di_dagger.impl.Di
import ru.kyamshanov.mission.ui_core.ui.components.Cell
import ru.kyamshanov.mission.ui_core.ui.components.MainButton
import ru.kyamshanov.mission.ui_core.ui.components.SomethingWentWrongDialog
import ru.kyamshanov.mission.ui_core.ui.components.Surface
import ru.kyamshanov.mission.ui_core.ui.components.TextField
import ru.kyamshanov.mission.ui_core.ui.components.TopBar

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

    Surface(
        modifier = Modifier.padding(16.dp),
        topContent = {
            TopBar(title = "Регистрация профиля", navigationListener = {})
        },
        bottomContent = {
            MainButton(
                modifier = Modifier.fillMaxWidth(),
                label = "Сохранить",
                onClick = backgroundRegistrationViewModel::completeRegistration
            )
        }
    ) {
        Column {

            Cell {
                TextField(
                    text = "Пожалуйста, заполните информацию о профиле, чтобы продолжить работу"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            screenState.fields.forEach { (field, state) ->
                Spacer(modifier = Modifier.height(5.dp))
                composableFieldFactory.createComposable(field, state).invoke()
            }
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