package com.example.movieapp.features.movieList

import android.graphics.drawable.Drawable
import androidx.appcompat.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@Composable
fun MovieListScreen() {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "🍰 BakingApp",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                },
                actions = {
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            RecipeCard(
                name = "Movie Name",
                description = "Movie description",
                imageUrl =    AsyncImage(
                    model = "https://static0.makeuseofimages.com/wordpress/wp-content/uploads/2022/05/next-movie-apps.jpg?q=50&fit=crop&w=1488&h=837&dpr=1.5",
                    contentDescription = "Description of the image",
                    modifier = Modifier.size(100.dp)
                ),
                onCardClick = { },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}