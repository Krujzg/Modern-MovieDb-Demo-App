package com.example.movies.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.presentation.composables.LoadingProgressBar
import com.example.core.presentation.composables.SnackbarScaffold
import com.example.core.presentation.theme.AppTheme
import com.example.core.presentation.theme.Dimensions
import com.example.core.presentation.theme.LocalAppDimens
import com.example.movies.R
import com.example.movies.presentation.TestTags
import com.example.movies.presentation.states.MovieDetailsUiState

@Composable
internal fun MovieDetailsPage(
    modifier: Modifier = Modifier,
    uiState: MovieDetailsUiState,
) {
    val movie = uiState.movie
    val isLoading by uiState.isLoading.collectAsStateWithLifecycle()

    SnackbarScaffold(
        modifier = modifier,
        uiState = uiState
    ) { padding ->

        if (isLoading) {
            LoadingProgressBar()
        }

        movie?.let {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(padding)
                    .testTag(TestTags.MOVIE_DETAILS_SCREEN),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PosterImage(path = it.getPosterUrl())
                Title(title = it.title)
                ReleaseDate(releaseDate = it.releaseDate)
                Rating(voteAverage = it.getFormattedVoteAverage())
                Overview(overview = it.overview)
            }
        }
    }
}

@Composable
private fun PosterImage(
    modifier: Modifier = Modifier,
    path: String
) {
    val context = LocalContext.current
    val placeholder = painterResource(id = R.drawable.placeholder)
    AsyncImage(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f)
            .testTag(TestTags.MOVIE_DETAILS_POSTER_IMAGE),
        model = ImageRequest.Builder(context)
            .data(path)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        placeholder = placeholder,
        error = placeholder
    )
}

@Composable
private fun Title(
    modifier: Modifier = Modifier,
    title: String,
    dimens: Dimensions = LocalAppDimens.current
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimens.paddingSmall)
            .testTag(TestTags.MOVIE_DETAILS_TITLE),
        text = title,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ReleaseDate(
    modifier: Modifier = Modifier,
    releaseDate: String,
    dimens: Dimensions = LocalAppDimens.current
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimens.paddingSmall)
            .testTag(TestTags.MOVIE_DETAILS_RELEASE_DATE),
        text = stringResource(R.string.release_date) + releaseDate,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun Rating(
    modifier: Modifier = Modifier,
    voteAverage: String,
    dimens: Dimensions = LocalAppDimens.current
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .background(Color.Transparent)
                .testTag(TestTags.MOVIE_DETAILS_RATING),
            text = voteAverage,
            textAlign = TextAlign.Center
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(R.string.star_icon_content_desc),
            tint = AppTheme.colorScheme().primary,
            modifier = Modifier
                .size(dimens.iconSizeNormal)
                .padding(start = dimens.paddingExtraSmall)
        )
    }
}

@Composable
private fun Overview(
    modifier: Modifier = Modifier,
    overview: String,
    dimens: Dimensions = LocalAppDimens.current
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(dimens.paddingSmall)
            .background(Color.Transparent)
            .testTag(TestTags.MOVIE_DETAILS_OVERVIEW),
        text = overview,
        textAlign = TextAlign.Justify
    )
}