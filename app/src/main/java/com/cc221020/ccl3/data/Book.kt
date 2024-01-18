package com.cc221020.ccl3.data

import kotlinx.serialization.Serializable

@Serializable
data class Book(val title: String, val author: String, val publicationYear: Int)