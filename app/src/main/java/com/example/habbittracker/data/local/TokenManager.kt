package com.example.habbittracker.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Создаем единственный экземпляр DataStore (Синглтон на уровне файла)
private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenManager(private val context: Context) {

    private val JWT_KEY = stringPreferencesKey("jwt_token")

    // TODO: Написать suspend функцию saveToken(token: String)
    suspend fun saveToken(token: String){
        context.dataStore.edit { preferences -> preferences[JWT_KEY] = token }
    }
    // TODO: Написать переменную getToken: Flow<String?>
    val getToken: Flow<String?>  = context.dataStore.data.map { preferences -> preferences[JWT_KEY] }
}