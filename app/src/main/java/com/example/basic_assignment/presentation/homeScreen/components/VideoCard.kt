package com.example.basic_assignment.presentation.homeScreen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.basic_assignment.utils.Dimens

@Composable
fun VideoCard(
    thumbUrl: String,
    title: String,
    channelUrl: String, channelName: String,
    onTap: () -> Unit
) {
    Box(modifier = Modifier
        .padding(vertical = Dimens.ExtraSmallPadding2)
        .fillMaxWidth()
        .clickable { onTap() }) {
        Column {
            AsyncImage(
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(Dimens.ExtraSmallPadding2)
                    .fillMaxWidth()
                    .height(250.dp), model = thumbUrl, contentDescription = null
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(
                    horizontal = Dimens.ExtraSmallPadding2,
                    vertical = Dimens.ExtraSmallPadding
                )
            ) {

                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center,
                    model = channelUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(Dimens.ExtraSmallPadding2))
                Column {
                    Text(
                        text = title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(Dimens.ExtraSmallPadding))
                    Text(text = channelName)
                }


            }
        }


    }
}