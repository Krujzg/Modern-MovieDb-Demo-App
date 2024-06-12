package com.example.movies.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.presentation.composables.LoadingProgressBar
import com.example.core.presentation.composables.SnackbarScaffold
import com.example.core.presentation.theme.AppTheme
import com.example.core.presentation.theme.LocalAppDimens
import com.example.movies.R
import com.example.movies.domain.Constants
import com.example.movies.domain.movieentity.Movie
import com.example.movies.presentation.event.UiEvent
import com.example.movies.presentation.states.MoviesUiState

@Composable
internal fun MoviesScreen(
    modifier: Modifier = Modifier,
    uiState: MoviesUiState,
    onEvent: (UiEvent) -> Unit
) {
    val movies = uiState.movies
    val isLoading by uiState.isLoading.collectAsStateWithLifecycle()

    SnackbarScaffold(
        modifier = modifier,
        uiState = uiState
    ) { padding ->
        if (isLoading) {
            LoadingProgressBar()
        }
        if (movies.isNotEmpty()) {
            MoviesList(
                modifier = Modifier.padding(padding),
                movies = movies,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun MoviesList(
    modifier: Modifier = Modifier,
    movies: List<Movie>,
    onEvent: (UiEvent) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = modifier.testTag("MoviesScreen"),
        state = listState
    ) {
        items(movies.size) { index ->
            MovieCard(
                movie = movies[index],
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun MovieCard(
    modifier: Modifier = Modifier,
    movie: Movie,
    onEvent: (UiEvent) -> Unit
) {
    val dimens = LocalAppDimens.current
    val colors = AppTheme.colorScheme()
    Card(
        modifier = modifier
            .padding(
                horizontal = dimens.paddingExtraLarge,
                vertical = dimens.paddingNormal
            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimens.paddingExtraSmall
        ),
        border = BorderStroke(
            width = dimens.borderWidthSmall,
            color = colors.primary
        ),
        colors = CardDefaults.cardColors(
            contentColor = colors.primary
        ),
        onClick = {
            onEvent(UiEvent.OnMovieClicked(movie.id))
        }
    ) {
        MovieCardContent(movie = movie)
    }

}

@Composable
private fun MovieCardContent(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    val context = LocalContext.current
    val dimens = LocalAppDimens.current
    val halfMaxHeight = Constants.HALF_HEIGHT
    with(movie) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val placeholder = painterResource(id = R.drawable.placeholder)
            val posterUrl = getPosterUrl()

            if (posterUrl.isEmpty()) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(halfMaxHeight),
                    painter = placeholder,
                    contentDescription = null
                )
            } else {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(halfMaxHeight),
                    model = ImageRequest.Builder(context)
                        .data(posterUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    placeholder = placeholder,
                    error = placeholder
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .fillMaxWidth()
                    .padding(vertical = dimens.paddingNormal),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .background(Color.Transparent),
                    text = title,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}