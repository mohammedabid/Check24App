package com.example.check24app.ui.detail

sealed class DetailsUIState
object LoadingState : DetailsUIState()
object ContentState : DetailsUIState()
class ErrorState(val message: String) : DetailsUIState() {
}