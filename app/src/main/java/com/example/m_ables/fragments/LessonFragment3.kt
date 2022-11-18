package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m_ables.Model.Lesson
import com.example.m_ables.R
import com.example.m_ables.adapters.LessonsAdapter
import com.example.m_ables.databinding.FragmentLesson3Binding
import java.util.ArrayList

class LessonFragment3 : Fragment(R.layout.fragment_lesson3) {

    private var fragmentLessons3Binding: FragmentLesson3Binding? = null
    private var taskList: List<Lesson> = ArrayList()
    private val adapter = LessonsAdapter(taskList)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLesson3Binding.bind(view)
        fragmentLessons3Binding = binding

        taskList = ArrayList()
        taskList = setDataList()
        if (taskList.isNotEmpty()) {
            adapter.setData(taskList)
            fragmentLessons3Binding!!.recyclerView.adapter = adapter
            fragmentLessons3Binding!!.recyclerView.layoutManager = LinearLayoutManager(context)
            fragmentLessons3Binding!!.textView.visibility = View.GONE
        } else {
            fragmentLessons3Binding!!.textView.setText(R.string.no_tasks)
            fragmentLessons3Binding!!.textView.visibility = View.VISIBLE
        }
        //getStudentList()
    }

    private fun setDataList(): ArrayList<Lesson> {

        var arrayList: ArrayList<Lesson> = ArrayList()

        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))
        arrayList.add(Lesson("Просьба через \nкоммуникатор", 0))

        return arrayList
    }

}