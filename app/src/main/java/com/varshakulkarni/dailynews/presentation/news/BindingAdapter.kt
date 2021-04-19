package com.varshakulkarni.dailynews.presentation.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("fetchImage")
fun bindImageView(imageView: ImageView, url: String?){
    Picasso.get().load(url).centerCrop().fit().into(imageView)
}
