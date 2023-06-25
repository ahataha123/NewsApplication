package com.example.newsapplication.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapplication.viewmodel.SavedNewsViewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

import com.example.newsapplication.model.RoomModel
import com.example.newsapplication.ui.theme.customWhite
import java.util.*


@Composable
fun SavedNewsView(navController: NavController, viewModel: SavedNewsViewModel = hiltViewModel()){

   val savedNewsList = viewModel.newsList.observeAsState(listOf()).value

    if (savedNewsList.isNotEmpty()){
        SavedNewsListView(articles = savedNewsList, navController = navController,viewModel)
    }else{
         Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
             ) {
             Text(text = "No Saved Articles",

             fontSize = 13.sp,
             textAlign = TextAlign.Center)
         }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SavedNewsListView(articles:List<RoomModel>, navController: NavController, viewModel: SavedNewsViewModel = hiltViewModel()){
    Collections.reverse(articles)  //it make listview show first most recently added in compose
    LazyColumn(contentPadding = PaddingValues(5.dp),
                reverseLayout = true){
            itemsIndexed(items = articles,key = {_,article->
                    article.hashCode()
            }){index, item ->
            val state  = rememberDismissState(
                confirmStateChange = {
                    if (it==DismissValue.DismissedToStart){
                        viewModel.deleteNews(item)
                    }
                    true
                }
            )
           SwipeToDismiss(state = state, background = {
               val color = when(state.dismissDirection){
                   DismissDirection.StartToEnd -> Color.Transparent
                       DismissDirection.EndToStart -> Color.Black
                       null-> Color.Red

               }
               Box(modifier = Modifier
                   .fillMaxSize()
                   .background(color = color)
                   .padding(10.dp)){
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color.Gray,
                            modifier = Modifier.align(Alignment.CenterEnd)
                            )
                    }
           },
           dismissContent = {
               SavedNewsRow(navController,item)
           },
           directions = setOf(DismissDirection.EndToStart))
           Divider()
     }
    }
}


@Composable
fun SavedNewsRow(navController: NavController, article : RoomModel){
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



