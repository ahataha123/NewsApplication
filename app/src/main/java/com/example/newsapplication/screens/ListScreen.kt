package com.example.newsapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.newsapplication.viewmodel.HomeViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.newsapplication.model.Article
import com.example.newsapplication.ui.theme.customWhite


@Composable
fun NewsList(navController: NavController, viewModel: HomeViewModel = hiltViewModel()){
    val articleList by remember {viewModel.newsList}
    val errorMessage by remember {viewModel.errorMessage}
    val isLoading by remember {viewModel.isLoading}


    NewsListView(articles = articleList, navController = navController)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        if (isLoading){
            CircularProgressIndicator(color = Color.Red)
        }
        if (errorMessage.isNotEmpty()){
            RetryView(error = errorMessage) {
                viewModel.loadNews()
            }
        }
    }
}


@Composable
fun NewsListView(articles:List<Article>, navController: NavController){
    LazyColumn(contentPadding = PaddingValues(5.dp)){
        items(articles){ article ->
            if (article.author != null && article.title != null && article.content != null && article.urlToImage != null && article.url != null){
                NewsRow(navController = navController, article = article)
            }

        }
    }
}

@Composable
fun NewsRow(navController: NavController, article : Article){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(45f)
            .background(color = customWhite)
            .padding(7.dp)
            .clickable {
                navController.navigate("details_graph/${article.title}")
            }
    ) {
        Image(painter = rememberImagePainter(data = article.urlToImage),
            contentDescription = "Image",
            modifier = Modifier
                .size(150.dp,150.dp)
                .clip(RectangleShape)
                .padding(2.dp)
        )
        Column {
            Text(text = article.title,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(9f),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp)

            Text(text = article.content,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                maxLines =4,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic)

            Text(text = article.author,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(30f),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 10.sp,
                textAlign = TextAlign.End)
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}

@Composable
fun RetryView(error:String, onRetry: () -> Unit){

    Column {
        Text(error, color = Color.Red, fontSize = 20.sp)

        Button(onClick = {
            onRetry
        }, modifier = Modifier.align(Alignment.CenterHorizontally)){
            Text(text = "Retry")
        }
    }

}



