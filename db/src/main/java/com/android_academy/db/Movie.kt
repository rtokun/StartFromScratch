package com.android_academy.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android_academy.network.MoviesListResult
import com.android_academy.network.NetworkingConstants.POSTER_BASE_URL

@Entity
data class Movie(
    @PrimaryKey
    val movieId: Int,
    val name: String,
    val imageUrl: String,
    val overview: String?,
    val releaseDate: String,
    val voteAverage: Double
)

object MovieModelConverter {

    //TODO after moving MoviesListResult to network module change here import as well
    fun convertNetworkMovieToModel(model: MoviesListResult): List<Movie> {
        return model.results.map {
            Movie(
                movieId = it.id,
                name = it.title,
                imageUrl = "${POSTER_BASE_URL}${it.posterPath}",
                overview = it.overview,
                voteAverage = it.voteAverage,
                releaseDate = it.release_date
            )
        }
    }
}