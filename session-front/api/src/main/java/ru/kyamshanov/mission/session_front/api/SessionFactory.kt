package ru.kyamshanov.mission.session_front.api

import ru.kyamshanov.mission.session_front.api.session.Session

interface SessionFactory {

    suspend fun newSession(login: String, password: CharSequence): Result<Session>

    suspend fun refreshSession(): Result<Session>
}