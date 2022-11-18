package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m_ables.Model.Lessons
import com.example.m_ables.R
import com.example.m_ables.adapters.GridViewLessonsAdapter
import com.example.m_ables.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment(R.layout.fragment_lessons), AdapterView.OnItemClickListener {

    private var fragmentLessonsBinding:FragmentLessonsBinding? = null
    private var arrayList:ArrayList<Lessons>? = null
    private var gridView: GridView? = null
    private var lessonsAdapter: GridViewLessonsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLessonsBinding.bind(view)
        fragmentLessonsBinding = binding

        gridView = fragmentLessonsBinding!!.gridViewListLessons
        arrayList = ArrayList()
        arrayList = setDataList()
        lessonsAdapter = context?.let { GridViewLessonsAdapter(it, arrayList!!) }
        gridView?.adapter = lessonsAdapter
        gridView?.onItemClickListener = this
    }

    private fun setDataList(): ArrayList<Lessons>? {

        var arrayList:ArrayList<Lessons> = ArrayList()

        arrayList.add(Lessons("A", "#6ae26e"))
        arrayList.add(Lessons("B", "#6ae26e"))
        arrayList.add(Lessons("C", "#6ae26e"))
        arrayList.add(Lessons("D", "#6ae26e"))
        arrayList.add(Lessons("E", "#6ae26e"))

        arrayList.add(Lessons("F", "#b6bfe2"))
        arrayList.add(Lessons("G", "#b6bfe2"))
        arrayList.add(Lessons("H", "#b6bfe2"))
        arrayList.add(Lessons("I", "#b6bfe2"))
        arrayList.add(Lessons("J", "#b6bfe2"))

        arrayList.add(Lessons("K", "#d07cc7"))
        arrayList.add(Lessons("L", "#d07cc7"))
        arrayList.add(Lessons("M", "#d07cc7"))
        arrayList.add(Lessons("N", "#d07cc7"))
        arrayList.add(Lessons("P", "#d07cc7"))

        arrayList.add(Lessons("Q", "#6aa2e2"))
        arrayList.add(Lessons("R", "#6aa2e2"))
        arrayList.add(Lessons("S", "#6aa2e2"))
        arrayList.add(Lessons("T", "#6aa2e2"))
        arrayList.add(Lessons("U", "#6aa2e2"))

        arrayList.add(Lessons("V", "#e28e6a"))
        arrayList.add(Lessons("W", "#e28e6a"))
        arrayList.add(Lessons("X", "#e28e6a"))
        arrayList.add(Lessons("Y", "#e28e6a"))
        arrayList.add(Lessons("Z", "#e28e6a"))

        return arrayList

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val item: Lessons = arrayList!![p2]
        findNavController().navigate(LessonsFragmentDirections.actionLessonsFragmentToLessonFragment2(item.name))
    }

}