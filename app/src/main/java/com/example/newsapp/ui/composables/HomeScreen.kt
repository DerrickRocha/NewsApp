package com.example.newsapp.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newsapp.R
import com.example.newsapp.models.NewsSource
import com.example.newsapp.ui.viewmodels.HomeViewModel
import kotlin.math.roundToInt

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    Column {
        HomeScreenAppBar()
        HomeScreenNewsSources(viewModel, navController)
        HomeScreenSlider(viewModel)
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
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
fun HomeScreenSlider(viewModel: HomeViewModel) {
    var sliderPosition by remember { mutableStateOf(0f) }

    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it },
        valueRange = 0f..4f,
        onValueChangeFinished = {
            viewModel.loadNewsSources(sliderPosition.roundToInt())
        },
        steps = 3,
        colors = SliderDefaults.colors(
            thumbColor = MaterialTheme.colors.secondary,
            activeTrackColor = MaterialTheme.colors.secondary
        )
    )
}