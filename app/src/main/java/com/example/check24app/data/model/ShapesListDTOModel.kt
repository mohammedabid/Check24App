package com.example.check24app.data.model

import android.os.Parcelable
import com.example.check24app.domain.model.ShapesModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlin.math.roundToInt

@Parcelize
data class ShapesListDTOModel(

	@field:SerializedName("header")
	val header: Header? = null,

	@field:SerializedName("filters")
	val filters: List<String?>? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem?>? = null
) : Parcelable

@Parcelize
data class ProductsItem(

	@field:SerializedName("longDescription")
	val longDescription: String? = null,

	@field:SerializedName("color")
	val color: String? = null,

	@field:SerializedName("releaseDate")
	val releaseDate: Long? = null,

	@field:SerializedName("price")
	val price: Price? = null,

	@field:SerializedName("imageURL")
	val imageURL: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("available")
	val available: Boolean? = null,

	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("colorCode")
	val colorCode: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null

) : Parcelable

@Parcelize
data class Price(

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("value")
	val value: Double? = null
) : Parcelable

@Parcelize
data class Header(

	@field:SerializedName("headerDescription")
	val headerDescription: String? = null,

	@field:SerializedName("headerTitle")
	val headerTitle: String? = null
) : Parcelable

fun ProductsItem.getShapeItem() : ShapesModel{
	return ShapesModel(
		id = this.id,
		name = this.name ?: "",
		image = this.imageURL ?: "",
		date = this.releaseDate,
		desc = this.description,
		price = this.price?.currency.plus(" ").plus(this.price?.value),
		rating = roundToHalf(this.rating)
	)
}

fun roundToHalf(d: Double?): Float {
	if (d != null) {
		return ((d * 2).roundToInt() / 2.0).toFloat()
	}
	return 0.0f
}