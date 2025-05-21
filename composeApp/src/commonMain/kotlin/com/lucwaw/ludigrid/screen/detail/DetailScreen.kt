package com.lucwaw.ludigrid.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.lucwaw.ludigrid.domain.Author
import com.lucwaw.ludigrid.domain.Comment
import com.lucwaw.ludigrid.domain.Post
import ludigrid.composeapp.generated.resources.Res
import ludigrid.composeapp.generated.resources.add
import ludigrid.composeapp.generated.resources.by
import ludigrid.composeapp.generated.resources.comments
import ludigrid.composeapp.generated.resources.go_back
import ludigrid.composeapp.generated.resources.no_comments
import ludigrid.composeapp.generated.resources.placeholder
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
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
    val comments = List(10){ Comment( comment = "Comment $it", author = Author(
        id = it.toString(),
        name = "John Doe",
        avatar = "https://www.example.com"))
    }
    Scaffold(
    topBar = {
        TopAppBar(
            title = {
                Text(text = post.title)
            },
            navigationIcon = {
                IconButton(onClick = {
                    /*TODO*/
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(Res.string.go_back)
                    )
                }
            }
        )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
        FloatingActionButton(
            onClick = {
                //TODO
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(Res.string.add)
            )
        }
    }
    ) { contentPadding ->

        MainDetail(
            modifier = Modifier.padding(contentPadding),
            post = post,
            comments = comments
        )


    }
}

@Composable
fun MainDetail(modifier: Modifier = Modifier, post: Post, comments: List<Comment>) {
    Column(modifier = modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Detail(

            post = post
        )
        Description(
            description = post.description
        )
        CommentsSection(
            comments = comments
        )
    }

}

@Composable
fun Detail(modifier: Modifier = Modifier, post: Post) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {

        if (post.image.isEmpty()){
            Image(
                painter = painterResource(Res.drawable.placeholder),
                contentDescription = "image",
                modifier = Modifier
                    .size(200.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(28.dp))
            )
        } else{
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
                    stringResource(Res.string.by, post.author.name.split(" ")[0], post.author.name.split(" ")[1])
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
fun CommentsSection(modifier: Modifier = Modifier, comments: List<Comment>) {
    Headline()
    Comments(modifier = modifier, comments)
}

@Composable
fun Comments(modifier: Modifier = Modifier, comments: List<Comment>) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(14.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = if (comment.author.name.contains(" ")) {
                stringResource(Res.string.by, comment.author.name.split(" ")[0], comment.author.name.split(" ")[1])
            } else {
                stringResource(Res.string.by, comment.author.name, "")
            },
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = comment.comment, style = MaterialTheme.typography.bodyMedium)
    }
}