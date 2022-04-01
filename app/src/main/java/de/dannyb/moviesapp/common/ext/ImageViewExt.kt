package de.dannyb.moviesapp.common.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImage(path: Any?) {
    Glide.with(this.context)
        .load(path)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}
