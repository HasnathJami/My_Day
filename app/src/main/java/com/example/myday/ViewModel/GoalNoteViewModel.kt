package com.example.myday.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myday.database.GoalNoteDatabase
import com.example.myday.model.GoalNoteEntityModel
import com.example.myday.repository.GoalNoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GoalNoteViewModel(application: Application) : AndroidViewModel(application) {

    val allGoalNote: LiveData<List<GoalNoteEntityModel>>
    private val repository :GoalNoteRepository

    init {
        val dao=GoalNoteDatabase.getDatabase(application).getNotesDao()
        repository=GoalNoteRepository(dao)
        allGoalNote=repository.allGoalNote
    }


    fun AddGoalNote(goalNote:GoalNoteEntityModel)=viewModelScope.launch(Dispatchers.IO) {

        repository.Insert(goalNote)

    }

    fun UpdateGoalNote(goalNote:GoalNoteEntityModel)=viewModelScope.launch(Dispatchers.IO) {

         repository.Update(goalNote)
    }

    fun DeleteGoalNote(goalNote:GoalNoteEntityModel)=viewModelScope.launch(Dispatchers.IO) {

        repository.Delete(goalNote)

    }




}