package com.example.check24app.common

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.check24app.R
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter("urlToImage")
fun urlToImage(view: ImageView, s: String?) {
    val options = RequestOptions.placeholderOf(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_background)
    Glide.with(view).setDefaultRequestOptions(options)
        .load(s ?: "").into(view)
}

@BindingAdapter("millisToDate")
fun millisToDate(view: TextView, millis: Long) {

    val c = Calendar.getInstance()
    c.timeInMillis = millis*1000
    val newDate = c.time

    val sdf = SimpleDateFormat("dd.MM.yyyy")
    view.text = sdf.format(newDate)
}
