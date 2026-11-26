package com.gabriel.benini.divideaiapp.core.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveUserId(userId: Long) {
        dataStore.edit { preferences ->
            preferences[SessionPreferencesKeys.USER_ID] = userId
        }
    }

    fun observeUserId(): Flow<Long?> {
        return dataStore.data.map { preferences ->
            preferences[SessionPreferencesKeys.USER_ID]
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.remove(SessionPreferencesKeys.USER_ID)
        }
    }
}