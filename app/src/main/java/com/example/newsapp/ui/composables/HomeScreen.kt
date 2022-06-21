package com.example.newsapp.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.models.NewsSource
import com.example.newsapp.ui.viewmodels.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    Column {
        HomeScreenAppBar()
        HomeScreenNewsSources(viewModel, navController)
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
fun HomeScreenNewsSources(viewModel: HomeViewModel, navController: NavController) {
    val sources by viewModel.sources.observeAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        sources?.let {
            items(it) { source ->
                NewsSourceCard(source) {
                    navController.navigate(
                        Screen.NewsSourceScreen.route + "?rssUrl=${source.rssUrl}&imageUrl=${source.imageUrl}"
                    )
                }
            }
        }
    }
}

@Composable
fun NewsSourceCard(source: NewsSource, onClick:() -> Unit) {
    Row(modifier = Modifier.selectable(
        selected = true,
        onClick = onClick
    )
    ){
        AsyncImage(
            model = source.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
        )
        Text(text = source.name)
    }
}

@Composable
fun HomeScreenSlider() {

}