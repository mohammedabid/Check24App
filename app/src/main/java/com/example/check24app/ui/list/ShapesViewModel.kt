package com.example.check24app.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.check24app.common.Resource
import com.example.check24app.domain.model.ShapesListModel
import com.example.check24app.domain.usecase.ShapesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShapesViewModel @Inject constructor(
    private val shapesListUseCase: ShapesListUseCase
) : ViewModel() {

    private var _uiState = MutableLiveData<ShapesUIState>()
    var uiStateLiveData: LiveData<ShapesUIState> = _uiState

    private var _shapesList = MutableLiveData<ShapesListModel>()
    var photosListLiveData: LiveData<ShapesListModel> = _shapesList

    var isError = false

    init {
        getShapes()
    }


    fun getShapes() {
        isError = !isError
        viewModelScope.launch {
            shapesListUseCase().onEach { dataState ->
                if(isError) {
                    _uiState.postValue(ErrorState("Network Error"))
                }
                else
                when (dataState) {

                    is Resource.Success -> {
                            _uiState.postValue(ContentState)
                            _shapesList.postValue(dataState.data!!)
                        }

                    is Resource.Error -> {
                            _uiState.postValue(ErrorState(dataState.message))

                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}