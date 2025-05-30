package com.lucwaw.ludigrid.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lucwaw.ludigrid.FAB
import com.lucwaw.ludigrid.MENU
import com.lucwaw.ludigrid.ToolBar
import com.lucwaw.ludigrid.domain.Author
import com.lucwaw.ludigrid.domain.Comment
import com.lucwaw.ludigrid.domain.Post
import com.lucwaw.ludigrid.toolBarModifier
import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.by
import ludigrid.composeapp.generated.resources.comments
import ludigrid.composeapp.generated.resources.go_back
import ludigrid.composeapp.generated.resources.no_comments
import ludigrid.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(onNavigateToBack: () -> Unit) {
    val post = Post(
        id = "1",
        title = "Codenames",
        author = Author(
            id = "1",
            name = "John Doe",
            avatar = "https://www.example.com"
        ),
        description = "Description"
    )
    val comments = List(10) {
        Comment(
            comment = "Comment $it", author = Author(
                id = it.toString(),
                name = "John Doe",
                avatar = "https://www.example.com"
            )
        )
    }
    var toolbarExpanded by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = post.title)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateToBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.go_back)
                        )
                    }
                },
                actions = {
                    MENU(
                        share = { /*TODO*/ },
                        delete = { /*TODO*/ },
                        addAComment = { /*TODO*/ }
                    )
                }
            )
        },
        floatingActionButton = {
            FAB(expanded = toolbarExpanded,share = { /*TODO*/ }, delete = { /*TODO*/ }, addAComment = { /*TODO*/ })
        }
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding).toolBarModifier(toolbarExpanded, onExpand = {toolbarExpanded = true}, onCollapse = {toolbarExpanded = false}),
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                MainDetail(
                    post = post
                )
            }
            item {
                Headline()
            }

            comments(comments)
        }

        Box(modifier = Modifier.fillMaxSize().padding(contentPadding), Alignment.BottomCenter) {
            ToolBar(expanded = toolbarExpanded,share = { /*TODO*/ }, delete = { /*TODO*/ }, addAComment = { /*TODO*/ })
        }

    }
}


private fun LazyListScope.comments(comments: List<Comment>) {
    if (comments.isEmpty()) {
        item {
            Text(
                text = stringResource(Res.string.no_comments),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    } else {
        items(comments) { comment ->
            CommentItem(comment = comment)
        }
    }
}

@Composable
fun MainDetail(
    modifier: Modifier = Modifier,
    post: Post
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Detail(
            post = post
        )
        Description(
            description = post.description
        )
    }

}

@Composable
fun Detail(modifier: Modifier = Modifier, post: Post) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {

        if (post.image.isEmpty()) {
            Image(
                painter = painterResource(Res.drawable.placeholder),
                contentDescription = "image",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(28.dp))
            )
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(28.dp)),
                model = post.image,
                placeholder = painterResource(Res.drawable.placeholder),
                contentDescription = "image",
                contentScale = ContentScale.Crop
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Text(
                text = post.title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = if (post.author.name.contains(" ")) {
                    stringResource(
                        Res.string.by,
                        post.author.name.split(" ")[0],
                        post.author.name.split(" ")[1]
                    )
                } else {
                    stringResource(Res.string.by, post.author.name, "")
                },
                style = MaterialTheme.typography.bodyMedium
            )
        }


    }
}

@Composable
fun Description(modifier: Modifier = Modifier, description: String) {
    Text(modifier = modifier, text = description, style = MaterialTheme.typography.bodyMedium)
}


@Composable
fun Headline(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(stringResource(Res.string.comments), style = MaterialTheme.typography.headlineSmall)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}

@Composable
fun CommentItem(comment: Comment, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = Color(0xFFFBFB78))
            .padding(14.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = if (comment.author.name.contains(" ")) {
                stringResource(
                    Res.string.by,
                    comment.author.name.split(" ")[0],
                    comment.author.name.split(" ")[1]
                )
            } else {
                stringResource(Res.string.by, comment.author.name, "")
            },
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = comment.comment, style = MaterialTheme.typography.bodyMedium)
    }
}