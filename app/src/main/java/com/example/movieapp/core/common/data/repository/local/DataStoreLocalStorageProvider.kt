package com.example.movieapp.core.common.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.movieapp.R
import com.example.movieapp.core.common.data.models.exception.MovieException
import com.example.movieapp.core.common.domain.repository.local.ILocalStorageProvider
import com.example.movieapp.core.common.domain.repository.local.IStorageKeyEnum
import kotlinx.coroutines.flow.firstOrNull
import kotlin.reflect.KClass


/**
 * A concrete implementation of the [ILocalStorageProvider] interface that provides
 * a key-value storage solution using Jetpack DataStore.
 *
 * This implementation supports CRUD (Create, Read, Update, Delete) operations
 * for various data types while adhering to the Dependency Inversion Principle.
 *
 * Keys are managed using the [IStorageKeyEnum] interface, ensuring type safety
 * and consistency across the application.
 *
 * @property dataStore The DataStore instance used for managing preferences.
 */
class DataStoreLocalStorageProvider(private val dataStore: DataStore<Preferences>) :
    ILocalStorageProvider {

    /**
     * Saves a value associated with a given key in the DataStore.
     *
     * @param key An instance of [IStorageKeyEnum] representing the unique identifier for the value.
     * @param value The value to be saved.
     * @param type The type of the value being saved, used to determine the appropriate key type.
     * @throws MovieException.Local.IOProcess if the value type is unsupported.
     */
    override suspend fun <T : Any> save(key: IStorageKeyEnum, value: T, type: KClass<T>) {
        dataStore.edit { userData ->
            userData[getPreferenceKey(key.keyValue, type)] = value
        }
    }

    /**
     * Retrieves a value associated with a given key from the DataStore.
     *
     * @param key An instance of [IStorageKeyEnum] representing the unique identifier for the value.
     * @param defaultValue The default value to return if the key is not found.
     * @param type The type of the value being retrieved, used to determine the appropriate key type.
     * @return The value associated with the key, or the default value if the key is not found.
     * @throws MovieException.Local.IOProcess if the value type is unsupported.
     */
    override suspend fun <T : Any> get(key: IStorageKeyEnum, defaultValue: T, type: KClass<T>): T {
        val preferencesKey = getPreferenceKey(key.keyValue, type)
        val preferences = dataStore.data.firstOrNull()
        return preferences?.get(preferencesKey) ?: defaultValue
    }

    /**
     * Updates a value associated with a given key in the DataStore.
     *
     * @param key An instance of [IStorageKeyEnum] representing the unique identifier for the value.
     * @param value The new value to be updated.
     * @param type The type of the value being updated, used to determine the appropriate key type.
     * @throws MovieException.Local.IOProcess if the value type is unsupported.
     */
    override suspend fun <T : Any> update(key: IStorageKeyEnum, value: T, type: KClass<T>) {
        dataStore.edit { userData ->
            userData[getPreferenceKey(key.keyValue, type)] = value
        }
    }

    /**
     * Deletes a value associated with a given key from the DataStore.
     *
     * @param key An instance of [IStorageKeyEnum] representing the unique identifier for the value.
     * @param type The type of the value being deleted, used to determine the appropriate key type.
     * @throws MovieException.Local.IOProcess if the value type is unsupported.
     */
    override suspend fun <T : Any> delete(key: IStorageKeyEnum, type: KClass<T>) {
        dataStore.edit { userData ->
            userData.remove(getPreferenceKey(key.keyValue, type))
        }
    }

    /**
     * Determines the appropriate [Preferences.Key] based on the provided type.
     *
     * @param key The unique identifier for the value as a string.
     * @param type The type of the value being saved, retrieved, updated, or deleted.
     * @return A [Preferences.Key] specific to the type.
     * @throws MovieException.Local.IOProcess if the value type is unsupported.
     */
    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> getPreferenceKey(key: String, type: KClass<T>): Preferences.Key<T> {
        return when (type) {
            Int::class -> intPreferencesKey(key)
            Float::class -> floatPreferencesKey(key)
            Double::class -> doublePreferencesKey(key)
            Long::class -> longPreferencesKey(key)
            Boolean::class -> booleanPreferencesKey(key)
            String::class -> stringPreferencesKey(key)
            else -> throw MovieException.Local.IOProcess(R.string.value_type_is_not_supported)
        } as Preferences.Key<T>
    }
}