package com.example.basic_assignment.presentation.detailsScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun DetailsCard(description: String, channelUrl: String, channelName: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expanded = !expanded }
            .fillMaxWidth()
            .height(if (expanded) 350.dp else 150.dp)
            .animateContentSize(),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                AsyncImage(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape),
                    alignment = Alignment.Center,
                    model = channelUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = channelName,
                    modifier = Modifier.padding(10.dp),
                    fontWeight = FontWeight.Bold,
                )

            }

            Text(
                text = description, overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(16.dp)
            )

        }

    }
}