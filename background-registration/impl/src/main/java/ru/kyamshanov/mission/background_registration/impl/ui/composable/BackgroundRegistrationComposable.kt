package ru.kyamshanov.mission.background_registration.impl.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.kyamshanov.mission.background_registration.impl.ui.models.BackgroundRegistrationBoundaryData

@Composable
internal fun BackgroundRegistrationComposable(
    backgroundRegistrationBoundaryData: BackgroundRegistrationBoundaryData,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Регистрация профиля")
        LazyColumn {
            backgroundRegistrationBoundaryData.requiredFields.forEach {
                item {
                    TextField(
                        value = "",
                        onValueChange = { text -> "" },
                        label = { Text(text = it.key) }
                    )
                }
            }
        }
    }
}