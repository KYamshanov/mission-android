package ru.kyamshanov.mission.authentication.impl.ui.model

internal sealed interface ScreenState {

    object WrongPassword : ScreenState

    object SomethingWentWrong : ScreenState
}