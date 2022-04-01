package de.dannyb.moviesapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieGenreModel(
    val id: Int,
    val name: String,
) : Parcelable
