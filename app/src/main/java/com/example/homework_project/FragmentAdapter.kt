package com.example.homework_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FragmentAdapter(val list: List<New>, val callback: Callback) : RecyclerView.Adapter<FragmentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FragmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.news_item, parent, false )
        val viewHolder = FragmentViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: FragmentViewHolder, position: Int) {
        val new = list.get(position)
        val newID = new.id
        holder.newsTitle.text = new.title
        holder.newsText.text = new.text
        holder.deleteButton.setOnClickListener{
            callback.onDeleteClick(newID)
        }
        holder.editButton.setOnClickListener{
            callback.onEditClick(new)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface Callback{
        fun onDeleteClick(newID : Int)
        fun onEditClick(new : New)
    }
}

class FragmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
    val newsText = itemView.findViewById<TextView>(R.id.newsText)
    val deleteButton = itemView.findViewById<Button>(R.id.deleteNewButton)
    val editButton = itemView.findViewById<Button>(R.id.editNewButton)
}