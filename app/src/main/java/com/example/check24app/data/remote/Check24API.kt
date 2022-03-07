package com.example.check24app.data.remote

import com.example.check24app.data.model.ShapesListDTOModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Check24API {

        @GET("/products-test.json")
        suspend fun getShapesList() : ShapesListDTOModel

    }