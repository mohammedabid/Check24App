package com.example.check24app.ui.list

sealed class ShapesUIState

object LoadingState : ShapesUIState()
object LoadingNextPageState : ShapesUIState()
object ContentState : ShapesUIState()
object ContentNextPageState : ShapesUIState()
object EmptyState : ShapesUIState()
class ErrorState(val message: String) : ShapesUIState()
class ErrorNextPageState(val message: String) : ShapesUIState()