package com.example.basic_assignment

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.basic_assignment.data.model.Content
import com.example.basic_assignment.data.model.UserState
import com.example.basic_assignment.data.network.SupabaseClient
import com.example.basic_assignment.utils.NetworkUtils
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.launch
import android.content.Context
import androidx.compose.ui.platform.LocalContext

class SupabaseViewModel : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState
    val contentList = mutableListOf<Content>()

    fun getContent() {
        viewModelScope.launch {
            try {
                println("getting content")
                _userState.value = UserState.Loading
                val data = SupabaseClient.supabase.postgrest["content_table"].select()
                    .decodeList<Content>()
                contentList.addAll(data)
                println(data)
                _userState.value = UserState.Success("data fetched successfully")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error occurred")
            }
        }
    }


}