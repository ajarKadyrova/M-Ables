package com.example.m_ables.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.m_ables.Model.Student
import com.example.m_ables.R

class StudentsAdapter( private var studentsList: List<Student>,
                       private val listener: OnItemClickListener) :
            RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_view,
            parent, false)
        return StudentsViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: StudentsAdapter.StudentsViewHolder, position: Int) {
        val currentStudent = studentsList[position]
        holder.name.text = currentStudent.surname + " " + currentStudent.name
        holder.birthDate.text = currentStudent.birthdate
    }

    override fun getItemCount() = studentsList.size

    fun setData(newList: List<Student>) {
        studentsList = newList
        notifyDataSetChanged()
    }

    inner class StudentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val name: TextView = itemView.findViewById(R.id.name_tv)
        val birthDate: TextView = itemView.findViewById(R.id.birthdate_tv)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}