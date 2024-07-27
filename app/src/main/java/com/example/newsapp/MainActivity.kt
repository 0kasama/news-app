package com.example.newsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.data.NewsRepository
import com.example.newsapp.ui.screen.HomeScreen
import com.example.newsapp.ui.screen.NewsDetailScreen
import com.example.newsapp.ui.theme.NewsappTheme
import com.example.newsapp.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            NewsappTheme {
                NewsApp(
                    onUrlClick = { url ->
                        openUrl(url)
                    }
                )
            }
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(onUrlClick: (String) -> Unit) {
    val navController = rememberNavController()
    val viewModel = NewsViewModel(NewsRepository())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF498AB1),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        "Mandiri News App",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "HomeScreen",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("HomeScreen") {
                HomeScreen(
                    viewModel = viewModel,
                    onHeadlineClick = onUrlClick,
                    onNewsItemClick = { article ->
                        navController.navigate("NewsDetail/${Uri.encode(article.url)}")
                    }
                )
            }
            composable(
                route = "NewsDetail/{articleUrl}",
                arguments = listOf(navArgument("articleUrl") { type = NavType.StringType })
            ) { backStackEntry ->
                val articleUrl = Uri.decode(backStackEntry.arguments?.getString("articleUrl"))
                val article = viewModel.getArticleByUrl(articleUrl)
                article?.let {
                    NewsDetailScreen(
                        article = it,
                        onReadMoreClick = onUrlClick
                    )
                }
            }
        }
    }
}