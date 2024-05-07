package com.example.basic_assignment.presentation.homeScreen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.basic_assignment.R
import com.example.basic_assignment.data.model.Content
import com.example.basic_assignment.presentation.homeScreen.components.VideoCard
import com.example.basic_assignment.utils.Dimens
import com.example.basic_assignment.utils.Dimens.MediumPadding1

@Composable
fun HomeScreen(
    contentList: List<Content>,
    navigateToSearch: () -> Unit,
    navigateToDetail: (Content) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = Dimens.ExtraSmallPadding2,
                        vertical = Dimens.ExtraSmallPadding
                    )
                    .height(35.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_yt),
                    contentDescription = null,
                    Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    navigateToSearch()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        Modifier.size(30.dp),
                        tint = Color.White
                    )
                }

            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(contentList.size) {
                val content = contentList[it]
                VideoCard(
                    thumbUrl = content.thumbnailUrl,
                    title = content.title,
                    channelUrl = content.channelUrl, channelName = content.channelName
                ) {
                    navigateToDetail(content)
                }
            }
        }
    }


}