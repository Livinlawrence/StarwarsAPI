package com.livin.starwars.presentation.people_search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livin.starwars.common.Constants.SEARCH_DELAY
import com.livin.starwars.common.Resource
import com.livin.starwars.common.UiEvent
import com.livin.starwars.domain.usecase.PeopleSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleSearchViewModel @Inject constructor(
    private val peopleSearchUseCase: PeopleSearchUseCase
) : ViewModel() {

    private val _peopleSearchState = mutableStateOf(PeopleSearchState())
    val peopleSearchState: State<PeopleSearchState> = _peopleSearchState

    private val _searchFieldState = mutableStateOf("")
    val searchFieldState: State<String> = _searchFieldState

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var searchJob: Job? = null

    fun searchWord(word: String) {
        _searchFieldState.value = word
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            peopleSearchUseCase.invoke(word).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _peopleSearchState.value = peopleSearchState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _peopleSearchState.value = peopleSearchState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = false
                        )
                        _eventFlow.emit(UiEvent.ShowSnackBar(result.message ?: "Something went wrong"))
                    }
                    is Resource.Loading -> {
                        _peopleSearchState.value = peopleSearchState.value.copy(
                            data = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}
