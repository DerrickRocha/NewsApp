package com.example.newsapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.ui.viewmodels.NewsSourceScreenViewModel
import com.prof.rssparser.Article

@Composable
fun NewsSourceScreen(
    rssUrl: String,
    imageUrl: String,
    navController: NavController,
    newsSourceViewModel: NewsSourceScreenViewModel = viewModel(),
    onNewsSourceClick: (String) -> Unit
) {
    Scaffold(
        topBar = { NewsSourceScreenAppBar(navController) },
        content = {
            LaunchedEffect(key1 = true) {
                newsSourceViewModel.loadRss(rssUrl)
            }
            Column {
                NewsSourceScreenImage(imageUrl)
                NewsSourceScreenContent(newsSourceViewModel, onNewsSourceClick)
            }
        }
    )
}

@Composable
fun NewsSourceScreenAppBar(navController: NavController) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
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
fun NewsSourceScreenContent(viewModel: NewsSourceScreenViewModel, onNewsSourceClick: (String) -> Unit) {
    val channel by viewModel.channel.observeAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        channel?.articles?.let {
            items(it) { article ->
                NewsArticleCard(article) {
                    article.link?.let { articleUrl ->
                        onNewsSourceClick(articleUrl)
                    }
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
                .size(56.dp)
        )
        Text(text = article.title?: "")
    }
}
