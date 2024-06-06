package com.kursatkumsuz.marslojistiksurucuapp.util

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constants {
    const val BASE_URL = "https://6349229ca59874146b181121.mockapi.io/"
    const val DATASTORE_PREFERENCES_NAME = "app_preferences"
    val DATASTORE_PREFERENCES_KEY = booleanPreferencesKey("onboarding_completed")
}