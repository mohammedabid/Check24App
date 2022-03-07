package com.example.check24app.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.check24app.domain.model.ShapesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {

    private var _uiState = MutableLiveData<DetailsUIState>()
    var uiStateLiveData: LiveData<DetailsUIState> = _uiState

    private var _photoModel = MutableLiveData<ShapesModel>()
    var photoModelLiveData: LiveData<ShapesModel> = _photoModel

    fun initPhotoModel(photo: ShapesModel) {
        _photoModel.value = photo
    }
}