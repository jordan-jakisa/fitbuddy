package com.kerustudios.fitbuddy.data.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

object PreferenceKeys{
    val IS_FIRST_TIME_USER = booleanPreferencesKey("is_first_time_user")
    val USER_NAME = stringPreferencesKey("user_name")
}