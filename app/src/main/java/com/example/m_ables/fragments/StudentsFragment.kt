package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m_ables.Model.Student
import com.example.m_ables.Model.User
import com.example.m_ables.R
import com.example.m_ables.adapters.StudentsAdapter
import com.example.m_ables.databinding.FragmentStudentsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import java.util.ArrayList

class StudentsFragment : Fragment(R.layout.fragment_students), StudentsAdapter.OnItemClickListener {

    private var fragmentStudentsBinding: FragmentStudentsBinding? = null
    private lateinit var databaseRef: DatabaseReference
    private lateinit var uid: String
    private var studentsList: List<Student> = ArrayList()
    private val adapter = StudentsAdapter(studentsList, this)
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStudentsBinding.bind(view)
        fragmentStudentsBinding = binding

        firebaseAuth = FirebaseAuth.getInstance()
        if(firebaseAuth.currentUser == null){
            findNavController().navigate(StudentsFragmentDirections.actionStudentsFragmentToUserTypeFragment())
        }

        studentsList = ArrayList()
        studentsList = setDataList()
        if (studentsList.isNotEmpty()) {
            adapter.setData(studentsList)
            fragmentStudentsBinding!!.recyclerView.adapter = adapter
            fragmentStudentsBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
            fragmentStudentsBinding!!.textView.visibility = View.GONE
        } else {
            fragmentStudentsBinding!!.textView.setText(R.string.no_students)
            fragmentStudentsBinding!!.textView.visibility = View.VISIBLE
        }
        //getStudentList()
    }

    private fun getStudentList() {
        databaseRef.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
//                    if(studentsList.isNotEmpty()) {
//                        adapter.setData(studentsList)
//                        fragmentStudentsBinding!!.recyclerView.adapter = adapter
//                        fragmentStudentsBinding!!.recyclerView.layoutManager = LinearLayoutManager(context)
//                        fragmentStudentsBinding!!.textView.visibility = View.GONE
//                    }
//                    else {
//                        fragmentStudentsBinding!!.textView.setText(R.string.no_students)
//                        fragmentStudentsBinding!!.textView.visibility = View.VISIBLE
//                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onItemClick(position: Int) {
        adapter.notifyItemChanged(position)
        findNavController().navigate(StudentsFragmentDirections.actionStudentsFragmentToStudentInfoFragment(studentsList[position]))
    }

    private fun setDataList(): ArrayList<Student> {

        var arrayList: ArrayList<Student> = ArrayList()

        arrayList.add(Student("Иванов", "Иван", "01.05.2003",""))
        arrayList.add(Student("Керимов", "Петр", "04.12.2003",""))
        arrayList.add(Student("Кадырова", "Алина", "02.07.2017",""))
        arrayList.add(Student("Атаев", "Эрмек", "20.15.2014",""))
        arrayList.add(Student("Асемова", "Азем", "31.01.2008",""))

        return arrayList
    }


}