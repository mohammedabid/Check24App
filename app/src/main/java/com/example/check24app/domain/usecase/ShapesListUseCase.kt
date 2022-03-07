package com.example.check24app.domain.usecase

import com.example.check24app.domain.repository.ShapesRepository
import javax.inject.Inject


class ShapesListUseCase @Inject constructor(private val repository: ShapesRepository) {

    suspend operator fun invoke() = repository.getShapes()
}