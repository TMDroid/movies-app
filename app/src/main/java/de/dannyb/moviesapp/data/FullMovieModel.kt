package de.dannyb.moviesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullMovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("genres") val genres: List<MovieGenreModel>,
    @SerializedName("backdrop_path") private val backdropPath: String?,
    @SerializedName("poster_path") private val posterPath: String?,
    @SerializedName("vote_average") val votes: Double,
    @SerializedName("release_date") val releaseDate: String,
) : Parcelable {

    val poster: Any
        get() {
            return if (posterPath != null) {
                "${BASE_COVER_PATH}$posterPath"
            } else {
                R.drawable.movie_cover_placeholder
            }
        }

    val backdrop: String?
        get() = backdropPath?.let {
            "${BASE_COVER_PATH}$it"
        }

    companion object {
        const val BASE_COVER_PATH = "https://image.tmdb.org/t/p/w300"
    }
}
