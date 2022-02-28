package com.example.myday.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myday.model.GoalNoteEntityModel

@Database(entities = arrayOf(GoalNoteEntityModel::class), version = 1, exportSchema = false)
abstract class GoalNoteDatabase : RoomDatabase() {

    abstract fun getNotesDao(): GoalNoteDao

    companion object {

        @Volatile
        private var INSTANCE: GoalNoteDatabase? = null

        fun getDatabase(context: Context): GoalNoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoalNoteDatabase::class.java,
                    "goalNote_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}