package de.dannyb.moviesapp.data

import com.google.gson.annotations.SerializedName

data class AuthorDetailsModel(
    @SerializedName("name") val name: String,
    @SerializedName("username") val username: String,
    @SerializedName("avatar_path") val avatarPath: String,
    @SerializedName("rating") val rating: Double
)
