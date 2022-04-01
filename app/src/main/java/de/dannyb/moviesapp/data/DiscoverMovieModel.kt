package de.dannyb.moviesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.data.FullMovieModel.Companion.BASE_COVER_PATH
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMovieModel(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("poster_path") private var posterPath: String?,
    @SerializedName("release_date") var releaseDate: String?,
    @SerializedName("vote_average") var voteAverage: Double,
) : Parcelable {
    val poster: Any
        get() {
            return if (posterPath != null) {
                "$BASE_COVER_PATH$posterPath"
            } else {
                R.drawable.movie_cover_placeholder
            }
        }
}
