package com.example.newsapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.models.NewsSource
import com.example.newsapp.ui.theme.NewsAppTheme

@Composable
fun HomeScreen(sources: List<NewsSource>) {
    Column {
        HomeScreenAppBar()
        HomeScreenNewsSources(sources)
        HomeScreenSlider()
    }

}

@Composable
fun HomeScreenAppBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) }
    )
}

@Composable
fun HomeScreenNewsSources(sources: List<NewsSource>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(sources) { source ->
            NewsSourceCard(source)
        }
    }
}

@Composable
fun NewsSourceCard(source: NewsSource) {
    Row{
        Image(
            painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = source.name)
    }
}

@Composable
fun HomeScreenSlider() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewsAppTheme {
        HomeScreen(listOf(NewsSource.AL_JAZEERA))
    }
}