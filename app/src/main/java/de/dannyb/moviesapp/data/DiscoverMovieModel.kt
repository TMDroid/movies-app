package de.dannyb.moviesapp.data

import android.os.Parcelable
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion
import com.google.gson.annotations.SerializedName
import de.dannyb.moviesapp.R
import de.dannyb.moviesapp.data.FullMovieModel.Companion.BASE_COVER_PATH
import kotlinx.parcelize.Parcelize

@Parcelize
data class DiscoverMovieModel(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") private val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double,
) : Parcelable, SearchSuggestion {
    val poster: Any
        get() {
            return if (posterPath != null) {
                "$BASE_COVER_PATH$posterPath"
            } else {
                R.drawable.movie_cover_placeholder
            }
        }

    override fun getBody(): String {
        return title
    }
}
