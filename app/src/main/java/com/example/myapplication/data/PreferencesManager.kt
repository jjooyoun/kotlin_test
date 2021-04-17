package com.example.myapplication.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

data class FilterPreferences(val test: String)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    val preferencesFlow = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val test = preferences[PreferencesKeys.TEST_PREF] ?: "test"
            FilterPreferences(test)
        }

    suspend fun updateTest(test: String) {
        context.dataStore.edit{ preferences ->
            preferences[PreferencesKeys.TEST_PREF] = test
        }
    }

    private object PreferencesKeys {
        val TEST_PREF = stringPreferencesKey("test")
    }
}