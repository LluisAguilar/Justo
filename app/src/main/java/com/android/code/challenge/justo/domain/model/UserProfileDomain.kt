package com.android.code.challenge.justo.domain.model

data class UserProfileDomain(
    val status: Boolean,
    val info: Info?,
    val results: List<Result>?,
    val error: String?
)