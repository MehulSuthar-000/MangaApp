package com.example.storyapp

import android.content.Intent
import  android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class ItemAdapter(val storyTitles : Array<String> , val storyContents : Array<String> , val storyIamges : Array<String> , val firstChapter: Array<String>) : RecyclerView.Adapter<ItemAdapter.viewHolder>() {

    //A ViewHolder describes an item view and metadata about its place within the RecyclerView.
    //Adapter implementations should subclass ViewHolder and add fields for caching potentially expensive findViewById results.
    class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val cardTitle : TextView = itemView.findViewById(R.id.cardTitle)
        val cardContent : TextView = itemView.findViewById(R.id.cardContent)
        val cardImage : ImageView = itemView.findViewById(R.id.cardImage)
        val view = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate((R.layout.custom_item_view),parent,false)
        return viewHolder(view)
    }


    //This method internally calls onBindViewHolder to update the ViewHolder contents with the item at the given position and
    // also sets up some private fields to be used by RecyclerView.
    // Adapters that merge other adapters should use bindViewHolder when calling nested adapters
    // so that RecyclerView can track which adapter bound the ViewHolder to return the correct position from getBindingAdapterPosition method.
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.cardTitle.text = storyTitles[position]
        holder.cardContent.text = storyContents[position]
        Picasso.get().load(storyIamges[position]).into(holder.cardImage)

        holder.view.setOnClickListener{

            //Intializing intent and passing title,image,firstChapter link, Description to chapter.kt
            val intent = Intent(it.context,Chapter::class.java)
            intent.putExtra("storyTitle",storyTitles[position])
            intent.putExtra("storyContent" , storyContents[position])
            intent.putExtra("storyImage" , storyIamges[position])
            intent.putExtra("FirstChapter" , firstChapter[position])
            holder.view.context.startActivity(intent)
        }
    }
        // To get the size of total items in the array
    override fun getItemCount(): Int {
        return storyTitles.size
    }
}

