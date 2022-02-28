package com.example.myday.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myday.model.GoalNoteEntityModel

@Dao
interface GoalNoteDao {

     @Insert(onConflict =OnConflictStrategy.IGNORE)
     suspend fun  Insert(goalNote: GoalNoteEntityModel)

     @Delete
     suspend fun Delete(goalNote: GoalNoteEntityModel)

     @Update
     suspend fun Update(goalNote:GoalNoteEntityModel)

     @Query("Select * From goalNote_table order by timestamp DESC")
     fun getAllData(): LiveData<List<GoalNoteEntityModel>>


}