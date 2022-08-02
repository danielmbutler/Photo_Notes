package com.dbtechprojects.photonotes.model

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = Constants.TABLE_NAME, indices = [Index(value = [id], unique = true)])
data class Note (
    @PrimaryKey(autoGenerate = true)    val id: Int,
    @ColumnInfo(name = "note")          val note: String,
    @ColumnInfo(name = "title")         val title: String,
    @ColumnInfo(name = "dateUpdated")   val dateUpdated: String = getDateCreated(),
    @ColumnInfo(name = "imageUris")     val imageUris: List<String>? = null
)

fun getDateCreated(): String {
    return LocalDateTime.now().minusDays((0..200).random().toLong()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}

fun Note.getDay(): String{
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    return LocalDateTime.parse(this.dateUpdated,formatter ).toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}