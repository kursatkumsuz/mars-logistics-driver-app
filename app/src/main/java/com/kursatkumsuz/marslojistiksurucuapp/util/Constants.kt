package com.kursatkumsuz.marslojistiksurucuapp.util

import androidx.datastore.preferences.core.booleanPreferencesKey

object Constants {
    const val DATASTORE_PREFERENCES_NAME = "app_preferences"
    val DATASTORE_PREFERENCES_KEY = booleanPreferencesKey("onboarding_completed")
}