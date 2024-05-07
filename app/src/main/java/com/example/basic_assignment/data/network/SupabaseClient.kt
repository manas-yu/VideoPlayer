package com.example.basic_assignment.data.network

import com.example.basic_assignment.utils.Constants
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {
    val supabase = createSupabaseClient(
        supabaseUrl = Constants.supabaseUrl,
        supabaseKey = Constants.supabaseKey
    ) {
        install(Postgrest)
        install(Storage)
        //install other modules
    }
}