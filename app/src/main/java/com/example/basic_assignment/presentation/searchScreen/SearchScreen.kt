package com.example.basic_assignment.presentation.searchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import com.example.basic_assignment.data.model.Content
import com.example.basic_assignment.presentation.homeScreen.components.VideoCard
import com.example.basic_assignment.utils.Dimens.MediumPadding1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    contentList: List<Content>,
    navigateToDetail: (Content) -> Unit,
    navigateUp: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    var loadState by remember { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf(emptyList<Content>()) }
    fun searchContent() {
        val searchQuery = query.toLowerCase()
        searchResults = contentList.filter { it.title.toLowerCase().contains(searchQuery) }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(MediumPadding1)
    ) {
        Row {
            IconButton(onClick = {
                navigateUp()

            }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            SearchBar(readOnly = false, onValueChange = {
                query = it
            }, value = query, onSearch = {
                loadState = true
                searchContent()
                loadState = false
            }) {
                query = ""
                searchResults = emptyList()
            }
        }
        if (loadState) {
            CircularProgressIndicator()
        } else

            LazyColumn {


                items(searchResults.size) {
                    val content = searchResults[it]
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