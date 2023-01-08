package ru.kyamshanov.mission.base_core.impl.ui

import android.content.Context
import ru.kyamshanov.mission.base_core.api.MissionPreferences
import javax.inject.Inject

private const val SHARED_PREFERENCES_KEY = "MissionSharedPreferencesKey"

class MissionPreferencesImpl @Inject constructor(
    context: Context
) : MissionPreferences {

    private val preferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)

    override fun saveValue(key: String, value: String) {
        preferences.edit()
            .apply {
                putString(key, value)
            }.apply()
    }

    override fun getValue(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun remove(key: String) {
        preferences.edit()
            .apply {
                remove(key)
            }.apply()
    }
}