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
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.lucwaw.ludigrid.domain.Author
import com.lucwaw.ludigrid.domain.Post
import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.error_image
import ludigrid.composeapp.generated.resources.games
import ludigrid.composeapp.generated.resources.sort_date
import ludigrid.composeapp.generated.resources.sort_name
import ludigrid.composeapp.generated.resources.sort_options
import ludigrid.composeapp.generated.resources.sort_rating
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(windowSizeClass: WindowSizeClass, onNavigateToDetail: () -> Unit, onNavigateToAddPost: () -> Unit) {
    val post = Post(
        id = "1",
        author = Author(
            id = "1",
            name = "John Doe",
            avatar = "https://www.example.com"
        ),
        title = "Codenames",
        description = "Description",
        image = "https://www.example.com"
    )
    val posts = List(25) { post.copy(id = it.toString()) }
    Scaffold(
        topBar = {
            var isSearchActive by rememberSaveable { mutableStateOf(false) }
            var searchQuery by remember { mutableStateOf("") }

            Column {
                TopAppBar(
                    title = { Text(text = stringResource(Res.string.games)) },
                    actions = {
                        var expanded by remember { mutableStateOf(false) }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .background(MaterialTheme.colorScheme.surface)
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {



                            Box {
                                IconButton(onClick = { expanded = true }) {
                                    Icon(
                                        Icons.Default.MoreVert,
                                        contentDescription = stringResource(
                                            Res.string.sort_options
                                        )
                                    )
                                }
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    offset = DpOffset(x = 0.dp, y = 0.dp)
                                ) {
                                    DropdownMenuItem(
                                        onClick = {
                                            //TODO Sort
                                            expanded = false
                                        },
                                        text = { Text(stringResource(Res.string.sort_name)) }
                                    )
                                    DropdownMenuItem(
                                        onClick = {
                                            //Todo Sort
                                            expanded = false
                                        },
                                        text = { Text(stringResource(Res.string.sort_rating)) }
                                    )
                                    DropdownMenuItem(
                                        onClick = {
                                            //Todo Sort
                                            expanded = false
                                        },
                                        text = { Text(stringResource(Res.string.sort_date)) }
                                    )
                                }
                            }

                        }
                    }
                )




                SearchBar(
                    modifier = Modifier
                        .semantics { traversalIndex = 0f }.fillMaxWidth().padding(16.dp),
                    inputField = {
                        SearchBarDefaults.InputField(
                            query = searchQuery,
                            onQueryChange = {
                                searchQuery = it
                             },
                            onSearch = {
                                /*TODO FILTER*/
                                isSearchActive = false
                            },
                            expanded = isSearchActive,
                            onExpandedChange = { isSearchActive = it },
                            placeholder = { Text("Search") },
                            trailingIcon = {
                                if (isSearchActive){
                                    IconButton(
                                        onClick = { isSearchActive = false }
                                    ) {
                                        Icon(Icons.Default.Close, "Close the Search Field")
                                    }
                                }

                            }
                        )
                    },
                    expanded = isSearchActive,
                    onExpandedChange = { isSearchActive = it },
                ) {
                    // Display search results in a scrollable column

                    LazyGrid(posts, windowSizeClass, onNavigateToDetail = {})

                }

            }
        },
        floatingActionButton =
            {
                FloatingActionButton(
                    onClick = { onNavigateToAddPost() }
                ){
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )
                }
            }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyGrid(posts,windowSizeClass, onNavigateToDetail)

        }

    }
}



@Composable
fun LazyGrid(posts : List<Post>, windowSizeClass: WindowSizeClass, onNavigateToDetail: () -> Unit) {
    LazyVerticalGrid(
        columns = rememberColumns(windowSizeClass),
        contentPadding = PaddingValues(18.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(posts, key = { item -> item.id }
        ){
            Post(it, onClick = onNavigateToDetail)
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
                                            tint = Color(0xFF86B2E8),
                                            modifier = Modifier
                                                .size(20.dp)
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
                            painter = painterResource(Res.drawable.error_image),
                            contentDescription = "Error Image",
                            contentScale = ContentScale.Crop
                        )
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