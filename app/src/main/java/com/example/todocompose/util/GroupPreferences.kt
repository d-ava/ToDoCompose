package com.example.todocompose.util



import android.content.Context

object GroupPreferences {
    private const val PREFS_NAME = "group_prefs"
    private const val KEY_GROUP_CODE = "group_code"

    fun saveGroupCode(context: Context, code: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_GROUP_CODE, code).apply()
    }

    fun getGroupCode(context: Context): String? {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_GROUP_CODE, null)
    }

    fun getOrCreateGroupCode(context: Context): String {
        val existing = getGroupCode(context)
        if (existing != null) return existing
        val newCode = (100000..999999).random().toString()
        saveGroupCode(context, newCode)
        return newCode
    }
}