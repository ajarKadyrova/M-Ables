package com.example.m_ables.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m_ables.Model.Lesson
import com.example.m_ables.R

class LessonsAdapter(private var lessonsList: List<Lesson>) :
    RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.test_list_view,
            parent, false
        )
        return LessonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LessonsAdapter.LessonViewHolder, position: Int) {
        val currentTask = lessonsList[position]
        holder.taskName.text = currentTask.taskName
    }

    fun setData(newList: List<Lesson>) {
        lessonsList = newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int  = lessonsList.size

    inner class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val taskName: TextView = itemView.findViewById(R.id.task_name_tv)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)


    }

}