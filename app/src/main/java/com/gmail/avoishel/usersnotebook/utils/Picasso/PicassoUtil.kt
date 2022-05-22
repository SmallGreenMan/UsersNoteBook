package com.gmail.avoishel.usersnotebook.utils.Picasso

import android.widget.ImageView
import com.gmail.avoishel.usersnotebook.R
import com.squareup.picasso.Picasso
import javax.inject.Inject

class PicassoUtil @Inject constructor(
    private val picasso: Picasso
) {

    fun loadImage(imageUrl: String, imageView: ImageView){
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_placeholder_foreground)
            .error(R.drawable.ic_placeholder_error_foreground)
            .into(imageView)
    }
}