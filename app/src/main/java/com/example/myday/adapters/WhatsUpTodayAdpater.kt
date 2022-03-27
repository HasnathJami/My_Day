package com.example.currentworld


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myday.R
import com.example.myday.model.WhatsUpTodayModel


//private val listener:NewItemsClicked
class NewsAdapter(private val listener:NewItemsClicked): RecyclerView.Adapter<NewsViewHolder>() {

      private var items:ArrayList<WhatsUpTodayModel> =ArrayList()
     
     //private val context:Context?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {


        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_template_for_whats_up_today, parent, false)
        val viewHolder= NewsViewHolder(view)
        // return NewsViewHolder(view)


        // view.setOnClickListener {
        //     Toast.makeText(parent.context,"Clicked ${viewHolder.layoutPosition}",Toast.LENGTH_LONG).show() //or list[viewHolder.adapterPosition]
        // }

        view.setOnClickListener {

             listener.onItemClicked(items[viewHolder.adapterPosition])
         }


        return viewHolder
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]

        holder.titleView.text = currentItem.title
        holder.author.text=currentItem.description
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.image)

    }




    fun updateNews(updatedNews:ArrayList<WhatsUpTodayModel>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }


}



class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleView: TextView = itemView.findViewById(R.id.whatsUpTodayTitleId)
    //val itemTemp: ConstraintLayout=itemView.findViewById(R.id.itemTemplate)
   // val image: ImageView =itemView.findViewById(R.id.image)
    val author: TextView=itemView.findViewById(R.id.authorId)
    val image:ImageView=itemView.findViewById(R.id.imageId)



}

interface NewItemsClicked{

   fun onItemClicked(item: WhatsUpTodayModel)

}