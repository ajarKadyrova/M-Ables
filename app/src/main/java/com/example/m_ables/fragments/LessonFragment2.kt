package com.example.m_ables.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import android.widget.GridView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.m_ables.Model.Lessons
import com.example.m_ables.R
import com.example.m_ables.adapters.GridViewLessonsAdapter
import com.example.m_ables.databinding.FragmentLesson2Binding
import com.example.m_ables.databinding.FragmentLessonsBinding

class LessonFragment2 : Fragment(R.layout.fragment_lesson2), AdapterView.OnItemClickListener {

    private var fragmentLessonsBinding: FragmentLesson2Binding? = null
    private var arrayList:ArrayList<Lessons>? = null
    private var gridView: GridView? = null
    private var lessonsAdapter: GridViewLessonsAdapter? = null
    private val args:LessonFragment2Args by navArgs()
    private var test_letter_type:String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLesson2Binding.bind(view)
        fragmentLessonsBinding = binding

        fragmentLessonsBinding!!.testLetter.text = test_letter_type

        gridView = fragmentLessonsBinding!!.gridViewListLessons
        arrayList = ArrayList()
        arrayList = setDataList()
        lessonsAdapter = context?.let { GridViewLessonsAdapter(it, arrayList!!) }
        gridView?.adapter = lessonsAdapter
        gridView?.onItemClickListener = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        test_letter_type = args.testLetterType
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun setDataList(): ArrayList<Lessons> {

        var arrayList:ArrayList<Lessons> = ArrayList()

        arrayList.add(Lessons("1", "#BDBDBD"))
        arrayList.add(Lessons("2", "#BDBDBD"))
        arrayList.add(Lessons("3", "#BDBDBD"))
        arrayList.add(Lessons("4", "#BDBDBD"))
        arrayList.add(Lessons("5", "#BDBDBD"))

        arrayList.add(Lessons("6", "#BDBDBD"))
        arrayList.add(Lessons("7", "#BDBDBD"))
        arrayList.add(Lessons("8", "#BDBDBD"))
        arrayList.add(Lessons("9", "#BDBDBD"))
        arrayList.add(Lessons("10", "#BDBDBD"))

        arrayList.add(Lessons("11", "#BDBDBD"))
        arrayList.add(Lessons("12", "#BDBDBD"))
        arrayList.add(Lessons("13", "#BDBDBD"))
        arrayList.add(Lessons("14", "#BDBDBD"))
        arrayList.add(Lessons("15", "#BDBDBD"))

        arrayList.add(Lessons("16", "#BDBDBD"))
        arrayList.add(Lessons("17", "#BDBDBD"))
        arrayList.add(Lessons("18", "#BDBDBD"))
        arrayList.add(Lessons("19", "#BDBDBD"))

        return arrayList

    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        var item: Lessons = arrayList!![p2]
        findNavController().navigate(LessonFragment2Directions.actionLessonFragment2ToLessonFragment3())
    }
}