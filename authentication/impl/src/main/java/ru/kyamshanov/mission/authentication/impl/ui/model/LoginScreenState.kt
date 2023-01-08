package ru.kyamshanov.mission.authentication.impl.ui.model

internal sealed interface LoginScreenState {

    object WrongPassword : LoginScreenState

    object SomethingWentWrong : LoginScreenState
}