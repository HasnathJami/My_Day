package com.example.myday

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.myday.ViewModel.GoalNoteViewModel
import com.example.myday.adapters.GoalNoteAdapter
import com.example.myday.adapters.NoteClickDeleteInterface
import com.example.myday.adapters.NoteClickInterface
import com.example.myday.model.GoalNoteEntityModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),NoteClickDeleteInterface,NoteClickInterface {
    lateinit var viewModel:GoalNoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        recyclerViewId.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        val goalNoteAdapter=GoalNoteAdapter(this,this,this)
        recyclerViewId.adapter=goalNoteAdapter


        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GoalNoteViewModel::class.java)

        viewModel.allGoalNote.observe(this, Observer {list-> list?.let {

            goalNoteAdapter.updateList(it)

        }


        })
    }

    fun addNotesFloatingButton(view: View) {

        //Toast.makeText(this,"Clicked",Toast.LENGTH_LONG).show();
       // Snackbar.make(view,"Clicked",Snackbar.LENGTH_LONG).show();

        val intent = Intent(this,AddNoteActivity::class.java)

        startActivity(intent)
        this.finish()

    }

    override fun onDeleteIconClick(goalNote: GoalNoteEntityModel) {

        viewModel.DeleteGoalNote(goalNote)
        Toast.makeText(this,"${goalNote.NoteTitle} Deleted",Toast.LENGTH_LONG).show()

    }

    override fun onNoteClick(goalNote: GoalNoteEntityModel) {

        val intent=Intent(this,AddNoteActivity::class.java)
        intent.putExtra("goalNoteType","Update")
        intent.putExtra("goalNoteTitle",goalNote.NoteTitle)
        intent.putExtra("goalNoteDescription",goalNote.NoteDescription)
        intent.putExtra("noteId",goalNote.id)



        startActivity(intent)
        this.finish()

    }


}