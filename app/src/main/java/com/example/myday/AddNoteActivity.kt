package com.example.myday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myday.ViewModel.GoalNoteViewModel
import com.example.myday.model.GoalNoteEntityModel
import kotlinx.android.synthetic.main.activity_add_note.*
import java.text.SimpleDateFormat
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    lateinit var viewModel:GoalNoteViewModel
    var fetchNoteId=-1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(GoalNoteViewModel::class.java)


        val goalNoteType=intent.getStringExtra("goalNoteType")

        if(goalNoteType.equals("Update"))
        {

            val fetchNoteTitle=intent.getStringExtra("goalNoteTitle")
            val fetchNoteDescription=intent.getStringExtra("goalNoteDescription")
            fetchNoteId=intent.getIntExtra("noteId",-1)

            titleId.setText(fetchNoteTitle)
            descriptionId.setText(fetchNoteDescription)

            saveButtonId.text="Update"
            setTitle("Update Task")
        }
        else{

            setTitle("Save Task")
            saveButtonId.text="Save"
        }

        saveButtonId.setOnClickListener(View.OnClickListener {

            val setNoteTitle=titleId.text.toString()
            val setNoteDescription=descriptionId.text.toString()

            if(goalNoteType.equals("Update"))
            {
                if(setNoteTitle.isNotEmpty() && setNoteDescription.isNotEmpty())
                {

                    val sdf= SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime:String=sdf.format(Date())
                    val updateNote=GoalNoteEntityModel(setNoteTitle,setNoteDescription,currentDateAndTime)
                    updateNote.id=fetchNoteId
                    viewModel.UpdateGoalNote(updateNote)
                    Toast.makeText(this,"Task Updated",Toast.LENGTH_LONG).show()

                }

            }
            else{


                if(setNoteTitle.isNotEmpty() && setNoteDescription.isNotEmpty())
                {
                   val sdf=SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime:String =sdf.format(Date())
                    viewModel.AddGoalNote(GoalNoteEntityModel(setNoteTitle,setNoteDescription,currentDateAndTime))
                    Toast.makeText(this,"$setNoteTitle Added",Toast.LENGTH_LONG).show()

                }


            }



            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()

        })

    }
}