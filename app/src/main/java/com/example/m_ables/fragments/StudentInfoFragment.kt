package com.example.m_ables.fragments

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m_ables.Model.Student
import com.example.m_ables.R
import com.example.m_ables.databinding.FragmentAuthBinding
import com.example.m_ables.databinding.FragmentStudentInfoBinding
import com.example.m_ables.databinding.FragmentStudentsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import java.util.ArrayList


class StudentInfoFragment : Fragment(R.layout.fragment_student_info) {

    private val args:StudentInfoFragmentArgs by navArgs()
    lateinit var userInfo: Student
    private var fragmentStudentInfoBinding : FragmentStudentInfoBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStudentInfoBinding.bind(view)
        fragmentStudentInfoBinding = binding

        userInfo = args.studentData
        fragmentStudentInfoBinding!!.nameTv.text = userInfo.name + " " + userInfo.surname
        fragmentStudentInfoBinding!!.birthdateTv.text = userInfo.birthdate
        if (userInfo.image.isEmpty()){
            fragmentStudentInfoBinding!!.profileImageIv.setImageDrawable(resources.getDrawable(R.drawable.ic_outline_account_circle_24))
            fragmentStudentInfoBinding!!.profileImageIv.layoutParams.height = 100
            fragmentStudentInfoBinding!!.profileImageIv.layoutParams.width = 100
        }
        //getStudentList()

        fragmentStudentInfoBinding!!.testStudentBtn.setOnClickListener {
            findNavController().navigate(StudentInfoFragmentDirections.actionStudentInfoFragmentToLessonsFragment())
        }
        //drawPie()
    }

//    private fun drawPie() {
//        fragmentStudentInfoBinding!!.pieChart.setUsePercentValues(true)
//        fragmentStudentInfoBinding!!.pieChart.getDescription().setEnabled(false)
//        fragmentStudentInfoBinding!!.pieChart.setExtraOffsets(5f, 10f, 5)
//        // on below line we are setting drag for our pie chart
//        fragmentStudentInfoBinding!!.pieChart.setDragDecelerationFrictionCoef(0.95f)
//        // on below line we are setting hole
//        // and hole color for pie chart
//        fragmentStudentInfoBinding!!.pieChart.setDrawHoleEnabled(true)
//        fragmentStudentInfoBinding!!.pieChart.setHoleColor(Color.WHITE)
//        // on below line we are setting circle color and alpha
//        fragmentStudentInfoBinding!!.pieChart.setTransparentCircleColor(Color.WHITE)
//        fragmentStudentInfoBinding!!.pieChart.setTransparentCircleAlph
//        // on  below line we are setting hole radius
//        fragmentStudentInfoBinding!!.pieChart.setHoleRadius(58f)
//        fragmentStudentInfoBinding!!.pieChart.setTransparentCircleRadiu
//        // on below line we are setting center text
//        fragmentStudentInfoBinding!!.pieChart.setDrawCenterText
//        // on below line we are setting
//        // rotation for our pie chart
//        fragmentStudentInfoBinding!!.pieChart.setRotationAngle(0f)
//        // enable rotation of the pieChart by touch
//        fragmentStudentInfoBinding!!.pieChart.setRotationEnabled(true)
//        fragmentStudentInfoBinding!!.pieChart.setHighlightPerTapEnabled
//        // on below line we are setting animation for our pie chart
//        fragmentStudentInfoBinding!!.pieChart.animateY(1400, Easing.EaseInOutQuad)
//        // on below line we are disabling our legend for pie chart
//        fragmentStudentInfoBinding!!.pieChart.legend.isEnabled = false
//        fragmentStudentInfoBinding!!.pieChart.setEntryLabelColor(Color.WHITE)
//        fragmentStudentInfoBinding!!.pieChart.setEntryLabelTextSiz
//        // on below line we are creating array list and
//        // adding data to it to display in pie chart
//        val entries: ArrayList<PieEntry> = ArrayList()
//        entries.add(PieEntry(70f))
//        entries.add(PieEntry(20f))
//        entries.add(PieEntry(10f))
//        // on below line we are setting pie data set
//        val dataSet = PieDataSet(entries, "Mobil")
//        // on below line we are setting icons.
//        dataSet.setDrawIcons(false)
//        // on below line we are setting slice for pie
//
//        dataSet.sliceSpace = 3f
//        dataSet.iconsOffset = MPPointF(0f, 40f)
//        dataSet.selectionShift = 5f
//        // add a lot of colors to list
//        val colors: ArrayList<Int> = ArrayList()
//        colors.add(resources.getColor(R.color.purple_200))
//        colors.add(resources.getColor(R.color.yellow))
//        colors.add(resources.getColor(R.color.red))
//        // on below line we are setting colors.
//        dataSet.colors = colors
//        // on below line we are setting pie data set
//        val data = PieData(dataSet)
//        data.setValueFormatter(PercentFormatter())
//        data.setValueTextSize(15f)
//        data.setValueTypeface(Typeface.DEFAULT_BOLD)
//        data.setValueTextColor(Color.WHITE)
//        fragmentStudentInfoBinding!!.pieChart.setData(data)
//        // undo all highlights
//        fragmentStudentInfoBinding!!.pieChart.highlightValues(null)
//        // loading chart
//        fragmentStudentInfoBinding!!.pieChart.invalidate()
//    }
}