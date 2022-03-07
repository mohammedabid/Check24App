package com.example.check24app.domain.repository

import com.example.check24app.common.Resource
import com.example.check24app.domain.model.ShapesListModel
import com.example.check24app.domain.model.ShapesModel
import kotlinx.coroutines.flow.Flow

interface ShapesRepository {

    suspend fun getShapes(): Flow<Resource<ShapesListModel>>

}