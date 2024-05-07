package com.example.basic_assignment.presentation.detailsScreen

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.basic_assignment.data.model.Content
import com.example.basic_assignment.presentation.detailsScreen.components.DetailsCard
import com.example.basic_assignment.presentation.detailsScreen.components.VideoPlayer
import com.example.basic_assignment.presentation.homeScreen.components.VideoCard

@Composable
fun DetailsScreen(
    content: Content,
    onShareClicked: () -> Unit,
    contentList: List<Content>,
    navigateToDetails: (Content) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()

    ) {
        var liked by remember { mutableStateOf(false) }
        VideoPlayer(videoUrl = content.videoUrl)
        Text(
            text = content.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp)
        )
        Row {
            IconButton(onClick = {
                liked = !liked
            }) {
                Icon(
                    imageVector = if (liked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null
                )

            }
            IconButton(onClick = onShareClicked) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)

            }
        }
        DetailsCard(
            description = content.description,
            channelUrl = content.channelUrl,
            channelName = content.channelName
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp)
        ) {

            items(contentList.size) {
                val item = contentList[it]
                if (item != content)
                    VideoCard(
                        thumbUrl = item.thumbnailUrl,
                        title = item.title,
                        channelUrl = item.channelUrl, channelName = item.channelName
                    ) {
                        navigateToDetails(item)
                    }
            }
        }

    }
}