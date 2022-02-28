package com.example.myday.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "goalNote_table")
class GoalNoteEntityModel(
    @ColumnInfo(name = "title") val NoteTitle: String,
    @ColumnInfo(name = "description") val NoteDescription: String,
    @ColumnInfo(name = "timestamp") val timestamp: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}