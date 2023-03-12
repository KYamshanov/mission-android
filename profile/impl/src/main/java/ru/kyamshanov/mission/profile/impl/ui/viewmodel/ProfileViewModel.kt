package ru.kyamshanov.mission.profile.impl.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kyamshanov.mission.profile.impl.ui.model.ProfileScreenState
import ru.kyamshanov.mission.profile_facade.api.domain.usecase.GetProfileUseCase
import ru.kyamshanov.mission.session_front.api.SessionFront
import ru.kyamshanov.mission.session_front.api.SessionInfo
import ru.kyamshanov.mission.session_front.api.session.LoggedSession

internal class ProfileViewModel @Inject constructor(
    private val sessionInfo: SessionInfo,
    private val sessionFront: SessionFront,
    private val getProfileUseCase: GetProfileUseCase,
) : ViewModel() {

    private val _screenState = MutableStateFlow(ProfileScreenState())

    val screenState = _screenState.asStateFlow()

    init {
        viewModelScope.launch {
            runCatching {
                val userInfo = (sessionInfo.session as? LoggedSession)?.userInfo
                    ?: throw IllegalStateException("User is not logged")
                val profile = getProfileUseCase.getProfile().getOrThrow()
                val screenState = ProfileScreenState(
                    info = ProfileScreenState.Info(
                        roles = userInfo.roles,
                        age = profile.age.toString(),
                        name = profile.name.toString()
                    )
                )
                _screenState.emit(screenState)
            }.onFailure(::showSomethingWentWrong)
        }
    }

    fun exit() {
        viewModelScope.launch {
            sessionFront.destroySession()
        }
    }

    fun closeSomethingWentWrong() {
        _screenState.apply {
            value = value.copy(somethingWentWrong = false)
        }
    }

    private fun showSomethingWentWrong(cause: Throwable) {
        Log.e(TAG, "Something went wrong", cause)
        _screenState.apply {
            value = value.copy(somethingWentWrong = true)
        }
    }

    private companion object {

        const val TAG = "ProfileViewModel"
    }
}