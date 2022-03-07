package com.example.check24app.data.repository

import android.util.Log
import com.example.check24app.common.Resource
import com.example.check24app.data.model.getShapeItem
import com.example.check24app.data.remote.Check24API
import com.example.check24app.domain.model.ShapesListModel
import com.example.check24app.domain.model.ShapesModel
import com.example.check24app.domain.repository.ShapesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class ShapesRepositoryImpl(private val shapesAPI: Check24API) : ShapesRepository {
    override suspend fun getShapes(): Flow<Resource<ShapesListModel>> {
        return flow {
            shapesAPI.getShapesList().apply {
                try {
                    Log.d("getGiphySearch", "repository.getGiphySearch")

                    val domainData =
                        products?.map { it ->
                            it?.getShapeItem() ?: ShapesModel(
                                id = -1,
                                name = "",
                                image = "",
                                rating = 0.0f,
                                price = "",
                                desc = "",
                                date = 0
                            )
                        }
                            ?: emptyList()

                    val shapesData = ShapesListModel(
                        title = this.header?.headerTitle ?: "",
                        subtitle = this.header?.headerDescription,
                        filters = this.filters,
                        shapesList = domainData
                    )
                    Log.d("getGiphySearch", "emit(Resource.success")
                    emit(Resource.Success(data = shapesData))
                } catch (e: HttpException) {
                    Log.d("getGiphySearch", e.localizedMessage)
                    emit(
                        Resource.Error<ShapesListModel>(
                            message = e.localizedMessage ?: "An Unknown error occurred"
                        )
                    )
                } catch (e: IOException) {
                    Log.d("getGiphySearch", e.localizedMessage)
                    emit(
                        Resource.Error<ShapesListModel>(
                            message = e.localizedMessage ?: "Check Connectivity"
                        )
                    )
                } catch (e: Exception) {

                }
            }
        }
    }
}