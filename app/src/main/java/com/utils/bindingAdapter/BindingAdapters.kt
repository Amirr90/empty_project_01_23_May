package com.utils.bindingAdapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.emptyprojectt1.R
import com.products.mapper.appendDollar

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(it)
            .apply(
                RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
            )
            .into(view)
    }
}

@BindingAdapter("android:bindPriceWithAppendDollar")
fun bindPriceWithAppendDollar(tv: TextView, text: Double) {
    tv.text = text.appendDollar()
}

@BindingAdapter("android:bindText")
fun bindText(tv: TextView, text: String) {
    tv.text = text
}