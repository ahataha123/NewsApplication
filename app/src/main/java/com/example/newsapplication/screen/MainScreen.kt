package com.example.newsapplication.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapplication.TopBar
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.clickable
import coil.compose.rememberAsyncImagePainter

@Composable
fun MainScreen (
    navController: NavController
) {
    TopBar(navController)
    NewsCard(news = NewsItem("WAS","","",""), onClick = { /*TODO*/ },     shape = RoundedCornerShape(8.dp),
        cornerRadius = 8.dp,
        imageHeight = 180.dp
    )
}

@Composable
fun NewsCard(
    news: NewsItem,
    onClick: () -> Unit,
    shape: Shape,
    cornerRadius: Dp,
    imageHeight: Dp
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        shape = shape,
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(news.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
                    .clip(shape = shape),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = news.summary,
                    style = MaterialTheme.typography.body1,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Composable
fun LoadImage(url: String) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url).apply(block = fun ImageRequest.Builder.() {
            transformations(CircleCropTransformation())
        }).build()
    )

    Image(
        painter = painter,
        contentDescription = "Image",
        modifier = Modifier.fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}
data class NewsItem(
    val title: String,
    val summary: String,
    val imageUrl: String,
    val articleUrl: String
)