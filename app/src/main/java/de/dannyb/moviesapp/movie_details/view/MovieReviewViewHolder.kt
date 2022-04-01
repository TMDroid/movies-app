package de.dannyb.moviesapp.movie_details.view

import androidx.recyclerview.widget.RecyclerView
import de.dannyb.moviesapp.common.view.loadImage
import de.dannyb.moviesapp.data.MovieReviewModel
import de.dannyb.moviesapp.databinding.RowReviewBinding
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.temporal.ChronoUnit
import java.util.*

class MovieReviewViewHolder(
    private val binding: RowReviewBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(review: MovieReviewModel) = with(binding) {
        val dateTime = parseDate(review.createdAt)

        reviewText.text = review.content
        reviewTime.text = dateTime?.toLocalTime()
            ?.truncatedTo(ChronoUnit.MINUTES)
            ?.format(
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                    .withLocale(Locale.getDefault())
            )
        reviewDate.text = dateTime?.toLocalDate()
            ?.format(
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                    .withLocale(Locale.getDefault())
            )
        reviewerName.text = review.author

        val avatarPath = buildGravatarPath(review.authorDetails.avatarPath)
        reviewerAvatar.loadImage(avatarPath)
    }

    private fun buildGravatarPath(avatarPath: String?): String? {
        return when {
            avatarPath == null -> return null
            avatarPath.contains(GRAVATAR_BASE_PATH) -> avatarPath.removePrefix("/")
            else -> "${GRAVATAR_BASE_PATH}$avatarPath"
        }
    }

    private fun parseDate(date: String): LocalDateTime? {
        val zdt = ZonedDateTime.parse(date)
        return zdt.toLocalDateTime()
    }

    companion object {
        private const val GRAVATAR_BASE_PATH = "https://www.gravatar.com/avatar"
    }
}
