package com.example.imccompose.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreAnotacao(private val context: Context) {

    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("configurações")

        val ANOTACAO_KEY = stringPreferencesKey("anotacao")
    }

    val getAnotacao: Flow<String> = context.dataStore.data
        .map {
        it[ANOTACAO_KEY] ?: ""
    }

    suspend fun salvarAnotacao(anotacao: String){
        context.dataStore.edit {
            it[ANOTACAO_KEY] = anotacao
        }
    }
}