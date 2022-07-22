package com.example.newsapp.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
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
    Scaffold(
        topBar = { HomeScreenAppBar() },
        content = { HomeScreenNewsSources(viewModel = viewModel, navController = navController) },
        bottomBar = { HomeScreenSlider(viewModel = viewModel) }
    )
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
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier
            .selectable(
                selected = true,
                onClick = onClick
            )
            .padding(8.dp)
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
}

@Composable
fun HomeScreenSlider(viewModel: HomeViewModel) {
    val sliderState = viewModel.storedSliderPosition.observeAsState()
    val sliderValue = sliderState.value?: 0
    var sliderPosition by rememberSaveable { mutableStateOf(sliderValue) }
    Column(
        Modifier
            .background(MaterialTheme.colors.background)
            .padding(bottom = 8.dp)
    ) {
        Slider(
            value = sliderPosition.toFloat(),
            onValueChange = { sliderPosition = it.roundToInt() },
            modifier = Modifier.padding(
                start = 8.dp,
                end = 8.dp
            ),
            valueRange = 0f..4f,
            onValueChangeFinished = {
                viewModel.saveSliderPosition(sliderPosition)
                viewModel.loadNewsSources(sliderPosition)
            },
            steps = 3,
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colors.secondary,
                activeTrackColor = MaterialTheme.colors.secondary
            )
        )
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Left")
            Text("Center-Left")
            Text("Center")
            Text("Center-Right")
            Text("Right")
        }
    }

}