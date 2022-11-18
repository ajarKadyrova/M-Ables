package com.example.m_ables.adapters

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.m_ables.Model.Categories
import com.example.m_ables.Model.Lessons
import com.example.m_ables.R

class GridViewLessonsAdapter (var context: Context, var arrayList: ArrayList<Lessons>): BaseAdapter(){
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.card_view_item_grid_lessons, null)
        var lessonLetter: TextView = view.findViewById(R.id.test_letter)
        var cardView: CardView = view.findViewById(R.id.card_view_lessons_layout)

        var listItem: Lessons = arrayList[p0]
        lessonLetter.text = listItem.name
        cardView.setBackgroundColor(Color.parseColor(listItem.color))

        return view
    }
}