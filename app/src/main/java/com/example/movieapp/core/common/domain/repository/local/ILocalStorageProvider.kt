package com.example.movieapp.core.common.domain.repository.local

import kotlin.reflect.KClass

/**
 * Interface defining a key-value storage provider that supports CRUD (Create, Read, Update, Delete) operations.
 *
 * This abstraction allows for flexible storage implementations while adhering to the Dependency Inversion Principle,
 * enabling seamless substitution of different storage mechanisms.
 */
interface ILocalStorageProvider {

    /**
     * Saves a value associated with a specific key in the storage.
     *
     * @param key The unique identifier for the value, represented by an implementation of [IStorageKeyEnum].
     * @param value The value to be stored.
     * @param type The type of the value being saved, used to determine the appropriate storage key type.
     * @param T The generic type of the value being stored.
     */
    suspend fun <T : Any> save(key: IStorageKeyEnum, value: T, type: KClass<T>)

    /**
     * Retrieves a value associated with a specific key from the storage.
     *
     * @param key The unique identifier for the value, represented by an implementation of [IStorageKeyEnum].
     * @param defaultValue The default value to return if the key is not found in the storage.
     * @param type The type of the value being retrieved, used to determine the appropriate storage key type.
     * @param T The generic type of the value being retrieved.
     * @return The value associated with the key, or the default value if the key is not found.
     */
    suspend fun <T : Any> get(key: IStorageKeyEnum, defaultValue: T, type: KClass<T>): T

    /**
     * Updates a value associated with a specific key in the storage.
     *
     * @param key The unique identifier for the value, represented by an implementation of [IStorageKeyEnum].
     * @param value The new value to be updated in the storage.
     * @param type The type of the value being updated, used to determine the appropriate storage key type.
     * @param T The generic type of the value being updated.
     */
    suspend fun <T : Any> update(key: IStorageKeyEnum, value: T, type: KClass<T>)

    /**
     * Deletes a value associated with a specific key from the storage.
     *
     * @param key The unique identifier for the value, represented by an implementation of [IStorageKeyEnum].
     * @param type The type of the value being deleted, used to determine the appropriate storage key type.
     * @param T The generic type of the value being deleted.
     */
    suspend fun <T : Any> delete(key: IStorageKeyEnum, type: KClass<T>)
}