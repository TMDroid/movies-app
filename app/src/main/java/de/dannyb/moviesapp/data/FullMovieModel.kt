package de.dannyb.moviesapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class FullMovieModel(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("tagline") var tagline: String,
    @SerializedName("overview") var overview: String,
    @SerializedName("genres") var genres: List<MovieGenreModel>,
    @SerializedName("backdrop_path") private var backdropPath: String?,
    @SerializedName("poster_path") private var posterPath: String?,
    @SerializedName("vote_average") var votes: Double,
    @SerializedName("release_date") var releaseDate: String,
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
