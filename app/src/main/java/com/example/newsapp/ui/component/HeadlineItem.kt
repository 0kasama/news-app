package com.example.newsapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.data.model.NewsArticle
import kotlin.random.Random

fun getRandomImageUrl(): String {
    val imageIds = listOf(
        "1000", "1001", "1002", "1003", "1004", "1005",
        "1006", "1007", "1008", "1009", "1010"
    )
    val randomId = imageIds[Random.nextInt(imageIds.size)]
    return "https://picsum.photos/id/$randomId/300/200"
}

@Composable
fun HeadlineItem(article: NewsArticle, onItemClick: () -> Unit) {
    val imageUrl = remember(article.urlToImage) {
        article.urlToImage ?: getRandomImageUrl()
    }

    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .padding(end = 16.dp)
            .padding(top = 8.dp)
            .clickable(onClick = onItemClick)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 2
                )
            }
        }
    }
}