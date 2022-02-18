package com.livin.starwars.presentation.people_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.livin.starwars.common.Resource
import com.livin.starwars.common.UiEvent
import com.livin.starwars.domain.usecase.GetPeopleDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    private val getPeopleDetailsUseCase: GetPeopleDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _peopleDetailsState = mutableStateOf(PeopleDetailsState())
    val peopleDetailsState: State<PeopleDetailsState> = _peopleDetailsState
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        savedStateHandle.get<String>("peopleId")?.let { peopleId ->
            if (peopleId.isNotEmpty()) {
                viewModelScope.launch {
                    getPeopleDetailsUseCase.invoke(peopleId).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                _peopleDetailsState.value = peopleDetailsState.value.copy(
                                    peopleDetails = result.data,
                                    isLoading = false
                                )
                            }
                            is Resource.Error -> {
                                _peopleDetailsState.value = peopleDetailsState.value.copy(
                                    peopleDetails = null,
                                    isLoading = false
                                )
                                _eventFlow.emit(UiEvent.ShowSnackBar(result.message ?: "error"))
                            }
                            is Resource.Loading -> {
                                _peopleDetailsState.value = peopleDetailsState.value.copy(
                                    peopleDetails = null,
                                    isLoading = true
                                )
                            }
                        }
                    }.launchIn(this)
                }
            }
        }
    }
}
