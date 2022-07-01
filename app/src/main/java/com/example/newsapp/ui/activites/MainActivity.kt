package com.example.newsapp.ui.activites

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.ui.composables.HomeScreen
import com.example.newsapp.ui.composables.NewsSourceScreen
import com.example.newsapp.ui.composables.Screen
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.viewmodels.HomeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                val navController = rememberNavController()
                MainScreen(viewModel, navController)
            }
        }
    }

    @Composable
    fun MainScreen(
        viewModel: HomeViewModel,
        navController: NavHostController
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.HomeScreen.route
            ){
                composable(route = Screen.HomeScreen.route) {
                    HomeScreen(viewModel = viewModel, navController)
                }
                composable(
                    route = Screen.NewsSourceScreen.route + "?rssUrl={rssUrl}&imageUrl={imageUrl}",
                    arguments = listOf(
                        navArgument("rssUrl") {
                            type = NavType.StringType
                        },
                        navArgument("imageUrl") {
                            type = NavType.StringType
                        }
                    )
                ) { currentBackstackEntry ->
                    val rssUrl = currentBackstackEntry.arguments?.getString("rssUrl")?: ""
                    val imageUrl = currentBackstackEntry.arguments?.getString("imageUrl")?: ""
                    NewsSourceScreen(rssUrl, imageUrl, navController, viewModel()) { articleUrl ->
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(articleUrl)
                        startActivity(intent)
                    }

                }
            }

        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadNewsSources(0)
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        NewsAppTheme {
            //HomeScreen(viewModel,)
        }
    }
}