package com.dbtechprojects.photonotes.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Note (
    val id: Int,
    val note: String,
    val title: String,
    val dateCreated: String = getDateCreated(),
    val imageUris: List<String>? = null
)

fun getDateCreated(): String {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}