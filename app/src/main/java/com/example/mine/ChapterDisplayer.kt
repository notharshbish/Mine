package com.example.mine

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChapterDisplayer(private val chaptersList : ArrayList<Chapters>,private val listener : onClicked) : RecyclerView.Adapter<ViewH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewH {
        val iv = LayoutInflater.from(parent.context).inflate(R.layout.chapter_tag,parent,false)
        val viewHolder = ViewH(iv)
        viewHolder.submit_btn.setOnClickListener {
            listener.onPress(chaptersList[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewH, position: Int) {
        val currentItem = chaptersList[position]
        Log.i("Current Item","$currentItem")
        holder.title_tag.text = currentItem.title
        holder.sub_img.setImageResource(currentItem.sub_img)
        holder.weightage.text = "Weightage : ${currentItem.weightage}%"
    }

    override fun getItemCount(): Int {
        return chaptersList.size
    }
}

class ViewH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title_tag = itemView.findViewById<TextView>(R.id.title_tag)
        val submit_btn = itemView.findViewById<Button>(R.id.submit_btn)
        var sub_img = itemView.findViewById<ImageView>(R.id.sub_img)
        val weightage = itemView.findViewById<TextView>(R.id.question_no)
}

interface onClicked {
    fun onPress(chapters : Chapters)
}