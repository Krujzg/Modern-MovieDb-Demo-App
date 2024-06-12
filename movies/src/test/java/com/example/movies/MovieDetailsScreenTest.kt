package com.example.movies

import androidx.compose.ui.test.onNodeWithTag
import com.example.movies.domain.movieentity.MovieDetails
import com.example.movies.presentation.TestTags
import com.example.movies.presentation.screens.MovieDetailsPage
import com.example.movies.presentation.states.MovieDetailsUiState
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDetailsScreenTest : BaseComposeTest() {

    private val uiState: MovieDetailsUiState = MovieDetailsUiState(
        movie = MovieDetails(
            id = 2874,
            isAdult = false,
            backDropPath = "/j3Z3XktmWB1VhsS8iXNcrR86PXi.jpg",
            budget = 9727,
            originalLanguage = "hu",
            originalTitle = "Shrek 2",
            overview = "Lorum ipsem",
            popularity = 4.5,
            posterPath = "/j3Z3XktmWB1VhsS8iXNcrR86PXi.jpg",
            releaseDate = "2024.01.10.",
            revenue = 5469,
            runtime = 7988,
            title = "Shrek 2",
            isThereAVideo = false,
            voteAverage = 6.7,
            voteCount = 2850
        )
    )

    @Test
    fun `MovieDetailsPage details should show when uiState is not null`() {
        withCompositionLocals {
            MovieDetailsPage(uiState = uiState)
        }
        rule.apply {
            runOnIdle {
                onNodeWithTag(TestTags.MOVIE_DETAILS_SCREEN).assertExists()
                onNodeWithTag(TestTags.MOVIE_DETAILS_POSTER_IMAGE).assertExists()
                onNodeWithTag(TestTags.MOVIE_DETAILS_TITLE).assertExists()
                onNodeWithTag(TestTags.MOVIE_DETAILS_RELEASE_DATE).assertExists()
                onNodeWithTag(TestTags.MOVIE_DETAILS_RATING).assertExists()
                onNodeWithTag(TestTags.MOVIE_DETAILS_OVERVIEW).assertExists()
            }
        }
    }

    @Test
    fun `MovieDetailsPage details should be shown when uiState is null`() {
        withCompositionLocals {
            MovieDetailsPage(uiState = MovieDetailsUiState())
        }
        rule.apply {
            runOnIdle {
                onNodeWithTag(TestTags.MOVIE_DETAILS_SCREEN).assertDoesNotExist()
                onNodeWithTag(TestTags.MOVIE_DETAILS_POSTER_IMAGE).assertDoesNotExist()
                onNodeWithTag(TestTags.MOVIE_DETAILS_TITLE).assertDoesNotExist()
                onNodeWithTag(TestTags.MOVIE_DETAILS_RELEASE_DATE).assertDoesNotExist()
                onNodeWithTag(TestTags.MOVIE_DETAILS_RATING).assertDoesNotExist()
                onNodeWithTag(TestTags.MOVIE_DETAILS_OVERVIEW).assertDoesNotExist()
            }
        }
    }

    @Test
    fun `LoadingProgressBar should show when loadingState is true`() = runTest {
        val nullUiState = MovieDetailsUiState()
        withCompositionLocals {
            MovieDetailsPage(uiState = nullUiState)
        }

        nullUiState.showLoadingDialog()

        rule.apply {
            runOnIdle {
                onNodeWithTag("LoadingIndicator").assertExists()
            }
        }
    }

    @Test
    fun `LoadingProgressBar should not show when loadingState is false`() = runTest {
        val nullUiState = MovieDetailsUiState()
        withCompositionLocals {
            MovieDetailsPage(uiState = nullUiState)
        }

        nullUiState.hideLoadingDialog()

        rule.apply {
            runOnIdle {
                onNodeWithTag("LoadingIndicator").assertDoesNotExist()
            }
        }
    }
}