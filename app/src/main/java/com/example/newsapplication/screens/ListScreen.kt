package com.example.newsapplication.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapplication.viewmodel.HomeViewModel
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.newsapplication.AppNameText
import com.example.newsapplication.DarkModeToggle
import com.example.newsapplication.model.Article
import com.example.newsapplication.ui.theme.customWhite
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay


@Composable
fun NewsList(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val articleList by remember { viewModel.newsList }
    val isLoading by remember { viewModel.isLoading }

    Surface(color = if (isDarkMode) Color.Black else Color.White) {
        Column {
            AppNameText(isDarkMode = isDarkMode, onToggleDarkMode = onToggleDarkMode)

            NewsListView(
                articles = articleList,
                navController = navController,
                isDarkMode = isDarkMode,
                onToggleDarkMode = onToggleDarkMode
            )

            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.Red)
                }
            }
        }
    }
}


@Composable
fun NewsListView(
    articles: List<Article>,
    navController: NavController,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    LazyColumn(contentPadding = PaddingValues(1.dp)) {
        items(articles) { article ->
            if (article.author != null && article.title != null && article.content != null && article.urlToImage != null && article.url != null) {
                NewsRow(
                    navController = navController,
                    article = article,
                    isDarkMode = isDarkMode,
                    onToggleDarkMode = onToggleDarkMode
                )
            }
        }
    }
}
@Composable
fun NewsRow(navController: NavController, article : Article,
            isDarkMode: Boolean,
            onToggleDarkMode: (Boolean) -> Unit){

    val backgroundColor = if (isDarkMode) Color.Black else Color.White
    val textColor = if (isDarkMode) Color.White else Color.Black


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(45f)
            .background(backgroundColor)
            .padding(1.dp)
            .clickable {
                navController.navigate("details_graph/${article.title}")
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = article.urlToImage),
            contentDescription = "Image",
            modifier = Modifier
                .size(150.dp, 150.dp)
                .clip(RectangleShape)
                .padding(2.dp)
        )
        Column {
            Text(text = article.title,
            style = MaterialTheme.typography.h4,
                color = textColor,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(9f),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp)

            Text(text = article.content,
                style = MaterialTheme.typography.h5,
                color = textColor,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp),
                maxLines =4,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontStyle = FontStyle.Italic)

            Text(text = article.author,
                style = MaterialTheme.typography.h6,
                color = textColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(30f),
                fontWeight = FontWeight.ExtraLight,
                fontSize = 10.sp,
                textAlign = TextAlign.End)
        }
    }
    Spacer(modifier = Modifier.padding(1.dp)
        .background(backgroundColor))


}

@Composable
fun SwipeRefresh(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    val articleList by remember { viewModel.newsList }
    var refreshing by remember { mutableStateOf(false) }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(1000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { viewModel.refreshNews() }
    ) {
        NewsListView(
            articles = articleList,
            navController = navController,
            isDarkMode = isDarkMode,
            onToggleDarkMode = onToggleDarkMode
        )
    }
}






