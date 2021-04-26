package com.varshakulkarni.dailynews.presentation.news

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.varshakulkarni.dailynews.R

@BindingAdapter("fetchImage")
fun bindImageView(imageView: ImageView, url: String?) {
    Picasso.get().load(url).centerCrop().fit().into(imageView)
}

@BindingAdapter("updateReadingList")
fun bindReadingListButton(imageView: ImageView, isAddedToReadingList: Boolean?) {
    val imageResource =
        if (isAddedToReadingList!!) R.drawable.ic_bookmark_added else R.drawable.ic_bookmark_add
    imageView.setImageResource(imageResource)
}
