package com.example.check24app.domain.model

import android.os.Parcelable
import com.example.check24app.data.model.Header
import com.example.check24app.data.model.ProductsItem
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize



@Parcelize
data class ShapesListModel(
    val title: String? = null,
    val subtitle: String? = null,
    val filters: List<String?>? = null,
    val shapesList: List<ShapesModel>? = null
) : Parcelable

@Parcelize
data class ShapesModel(
    val id: Int?,
    val name: String,
    val desc: String?,
    val price: String,
    val image: String,
    val rating: Float,
    val date: Long?
) : Parcelable
