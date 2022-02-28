package com.example.myday.repository

import androidx.lifecycle.LiveData
import com.example.myday.database.GoalNoteDao
import com.example.myday.model.GoalNoteEntityModel

class GoalNoteRepository(val goalNoteDao: GoalNoteDao) {
   val allGoalNote: LiveData<List<GoalNoteEntityModel>> =goalNoteDao.getAllData()


   suspend fun Insert(goalNote:GoalNoteEntityModel)
   {
       goalNoteDao.Insert(goalNote)
   }

   suspend fun Delete(goalNote: GoalNoteEntityModel)
   {
       goalNoteDao.Delete(goalNote)
   }

    suspend fun Update(goalNote:GoalNoteEntityModel)
    {
        goalNoteDao.Update(goalNote)
    }

}