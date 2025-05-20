package com.lucwaw.ludigrid.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage

@Composable
fun HomeScreen(windowSizeClass: WindowSizeClass, onNavigateToDetail: () -> Unit) {
    Scaffold { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyVerticalGrid(
                columns = rememberColumns(windowSizeClass),
                contentPadding = PaddingValues(18.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(25){
                    Post(Post, onClick = onNavigateToDetail)
                }
            }

        }

    }
}

@Composable
private fun rememberColumns(windowSizeClass: WindowSizeClass) = remember(windowSizeClass) {
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> GridCells.Fixed(1)
        WindowWidthSizeClass.Medium -> GridCells.Fixed(2)
        else -> GridCells.Adaptive(240.dp)
    }
}

data object Author {
    val id: Long = 0
    val name: String = "John Doe"
    val avatar: String = ""
}

data object Post {
    val id: Long = 0
    val title: String = "CodeNames"
    val author: Author = Author
    val description: String = "Un jeu de société super sympa à jouer entre amis !"
    val image: String = ""
}

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        onClick =
            { onClick() },
        modifier = modifier
            .width(360.dp)
            .height(342.dp)
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (post.author.avatar.isEmpty()) {
                        AvatarDot(post.author.name)
                    } else {
                        SubcomposeAsyncImage(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)     // clip to the circle shape
                            ,
                            model = post.author.avatar,
                            contentDescription = "Post Image",

                            contentScale = ContentScale.Crop,
                            error = {
                                if (LocalInspectionMode.current) {
                                    Image(
                                        painter = ColorPainter(Color.Red),
                                        contentDescription = "Error",
                                        modifier = Modifier.fillMaxSize()
                                    )
                                } else {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Warning,
                                            contentDescription = null,
                                            tint = Color.Red,
                                            modifier = Modifier
                                                .size(150.dp)
                                        )
                                    }
                                }
                            }
                        )
                    }

                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            post.title,

                            )
                        Text(
                            post.author.name
                        )
                    }
                }

                IconButton(
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }

            }
            SubcomposeAsyncImage(
                modifier = Modifier
                    .weight(1.1f),
                model = post.image,
                contentDescription = "Post Image",
                contentScale = ContentScale.Crop,
                error = {
                    if (LocalInspectionMode.current) {
                        Image(
                            painter = ColorPainter(Color.Red),
                            contentDescription = "Error",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Image(
                            painter = ColorPainter(Color.Red),
                            contentDescription = "Error",
                            modifier = Modifier.fillMaxSize()
                        )
                        /*Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "Error when loading image",
                                textAlign = TextAlign.Center
                            )
                        }*/
                    }
                }
            )

            Text(
                text = post.description,
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            )
        }


    }
}

@Composable
fun AvatarDot(name: String) {
    val initials = name.split(" ").joinToString("") { it.first().uppercase() }
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(
                CircleShape
            )
            .background(MaterialTheme.colorScheme.primaryContainer)

    ) {
        Text(
            text = initials,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )
    }

}