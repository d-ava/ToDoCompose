package com.example.todocompose.test.data.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RealtimeDBUser(
    val id: String? = null,
    val name: String? = null,
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name" to name
        )
    }
}
