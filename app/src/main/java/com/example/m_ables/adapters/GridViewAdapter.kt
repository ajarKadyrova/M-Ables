package com.example.m_ables.adapters

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.m_ables.Model.Categories
import com.example.m_ables.R

class GridViewAdapter(var context:Context, var arrayList: ArrayList<Categories>):BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        var view: View = View.inflate(context, R.layout.card_view_item_grid, null)
        var category:TextView = view.findViewById(R.id.category_tv)
        var description:TextView = view.findViewById(R.id.description_tv)
        var cardView:CardView = view.findViewById(R.id.card_view_layout)

        var listItem:Categories = arrayList[position]
        category.text = listItem.name
        if(listItem.description.isNotEmpty())  description.text = listItem.description
        else description.visibility = View.INVISIBLE
        cardView.setBackgroundColor(Color.parseColor(listItem.color))

        return view
    }
}