package com.example.basic_assignment.presentation.searchScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.basic_assignment.R
import com.example.basic_assignment.utils.Dimens

@Composable
fun SearchBar(

    modifier: Modifier = Modifier,
    readOnly: Boolean,
    onValueChange: (String) -> Unit,
    value: String,
    onSearch: () -> Unit, onClear: () -> Unit
) {

    Box(
        modifier = modifier
    ) {
        val containerColor = colorResource(id = R.color.input_background)
        TextField(

            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .border(

                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                ),
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,

            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                            modifier = Modifier.size(15.dp),
                        )
                    }

                }
            },
            placeholder = {
                Text(
                    text = "Search..",
                    style = MaterialTheme.typography.labelLarge,
                    color = colorResource(id = R.color.placeholder)
                )
            },
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                focusedContainerColor = containerColor,
                unfocusedContainerColor = containerColor,
                disabledContainerColor = containerColor,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()

                }
            ),
            textStyle = MaterialTheme.typography.labelMedium,
        )
    }


}