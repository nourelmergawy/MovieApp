package com.example.movieapp.core.common.presentaion.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

open class MovieAppViewModel<Action, Event, State> internal constructor(
    private val initialState: State,
) : MVI<Action, Event, State>, ViewModel() {

    private val _state = MutableStateFlow(initialState)
    override val state: StateFlow<State> = _state

    private val _event = Channel<Event>(Channel.UNLIMITED)
    override val event: Flow<Event> = _event.receiveAsFlow()

    override fun clearState() {
        updateState(initialState)
    }

    override fun emitEvent(event: Event) {
        viewModelScope.launch(Dispatchers.Main.immediate) {
            _event.send(event)
        }
    }

    override fun updateState(state: State) {
        _state.value = state
    }

    override fun processIntent(action: Action) {}

    override fun onCleared() {
        super.onCleared()
        _event.close()
    }

}