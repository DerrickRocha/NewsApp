package com.example.newsapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.viewmodels.NewsSourceScreenViewModel
import com.prof.rssparser.Article

@Composable
fun NewsSourceScreen(
    rssUrl: String,
    imageUrl: String,
    newsSourceViewModel: NewsSourceScreenViewModel = viewModel()
) {
    Scaffold(
        topBar = { NewsSourceScreenAppBar() },
        content = {
            LaunchedEffect(key1 = true) {
                newsSourceViewModel.loadRss(rssUrl)
            }
            Column {
                NewsSourceScreenImage(imageUrl)
                NewsSourceScreenContent(newsSourceViewModel)
            }
        }
    )
}

@Composable
fun NewsSourceScreenAppBar() {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) }
    )
}

@Composable
fun NewsSourceScreenImage(imageUrl: String) {
    if (imageUrl.isNotBlank()) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dp(365f))
        )
    }
}

@Composable
fun NewsSourceScreenContent(viewModel: NewsSourceScreenViewModel) {
    val channel by viewModel.channel.observeAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        channel?.articles?.let {
            items(it) { article ->
                NewsArticleCard(article) {
                    //todo: Open web browser or webView.
                }
            }
        }
    }
}

@Composable
fun NewsArticleCard(article: Article, onClick: () -> Unit) {
    Row(modifier = Modifier.selectable(
        selected = true,
        onClick = onClick
    )
        .fillMaxWidth()
    ){
        AsyncImage(
            model = article.image,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Text(text = article.title?: "")
    }
}
