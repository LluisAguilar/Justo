package com.android.code.challenge.justo.data.retrofit.response

data class UserProfile(
    val status: Boolean,
    val info: Info?,
    val results: List<Result>?,
    val error: String?
)