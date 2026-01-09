package com.example.movieapp.core.common.presentaion.mvi

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface defining the core structure of the Model-View-Intent (MVI) architecture.
 *
 * @param Action The type representing user actions or intents.
 * @param Event The type representing one-time events (e.g., navigation or error messages).
 * @param State The type representing the UI state of the view.
 */
interface MVI<Action, Event, State> {

    /**
     * A [StateFlow] representing the current state of the view.
     * Observers can collect this flow to react to state changes.
     */
    val state: StateFlow<State>

    /**
     * A [Flow] representing one-time events such as navigation or showing a toast.
     * These events are not part of the state and should not be replayed.
     */
    val event: Flow<Event>

    /**
     * Processes a user action or intent.
     * This function is responsible for interpreting the action and triggering the appropriate state or event updates.
     *
     * @param action The user action or intent to be processed.
     */
    fun processIntent(action: Action)

    /**
     * Updates the current state of the view.
     * This function is typically called internally to reflect changes in the UI state.
     *
     * @param state The new state to be set.
     */
    fun updateState(state: State)

    /**
     * Emits a one-time event.
     * This function is used to send events like navigation or error messages to the view.
     * **Note**: This function must be called on the [kotlinx.coroutines.Dispatchers.Main] dispatcher,
     * as it interacts with the UI.
     *
     * @param event The event to be emitted.
     */
    fun emitEvent(event: Event)

    /**
     * Clears the current state.
     * This function is used to reset the state to its initial or default value.
     */
    fun clearState()
}
