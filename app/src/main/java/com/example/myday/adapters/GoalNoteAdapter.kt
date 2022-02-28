package com.example.myday.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myday.R
import com.example.myday.model.GoalNoteEntityModel
import kotlinx.android.synthetic.main.item_template.view.*

class GoalNoteAdapter(private val context: Context,private val noteClickDeleteInterface:NoteClickDeleteInterface,private val noteClickInterface:NoteClickInterface): RecyclerView.Adapter<GoalNoteAdapter.viewHolder>() {

    private val allGoalNotes = ArrayList<GoalNoteEntityModel>()

    inner class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleRef = itemView.titleId
        val timestampRef = itemView.timeStampId
        val deleteButtonRef = itemView.imageButtonId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_template, parent, false)

        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val currPos = allGoalNotes[position]
        holder.titleRef.text = currPos.NoteTitle
        holder.timestampRef.text = "Last Updated : ${currPos.timestamp.toString()}"


        holder.deleteButtonRef.setOnClickListener(View.OnClickListener {

            noteClickDeleteInterface.onDeleteIconClick(currPos)
        })

        holder.itemView.setOnClickListener(View.OnClickListener {

            noteClickInterface.onNoteClick(currPos)

        })

    }

    override fun getItemCount(): Int {
        return allGoalNotes.size
    }

    fun updateList(goalNewList:List<GoalNoteEntityModel>)
    {
        allGoalNotes.clear()
        allGoalNotes.addAll(goalNewList)
        notifyDataSetChanged()
    }

}

    interface NoteClickDeleteInterface{

        fun onDeleteIconClick(goalNote:GoalNoteEntityModel)
    }

    interface NoteClickInterface{

        fun onNoteClick(goalNote:GoalNoteEntityModel)
    }


