package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m_ables.Model.Categories
import com.example.m_ables.Model.User
import com.example.m_ables.R
import com.example.m_ables.adapters.GridViewAdapter
import com.example.m_ables.databinding.FragmentAccountBinding
import com.example.m_ables.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class AccountFragment : Fragment(R.layout.fragment_account), AdapterView.OnItemClickListener {

    private var fragmentAccountBinding : FragmentAccountBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var databaseRef: DatabaseReference
    private lateinit var uid:String
    private var arrayList:ArrayList<Categories>? = null
    private var gridView:GridView? = null
    private var categoriesAdapter: GridViewAdapter? = null
    private lateinit var progressBar:ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAccountBinding.bind(view)
        fragmentAccountBinding = binding

        fragmentAccountBinding!!.progressBarAccount.visibility = View.VISIBLE
        fragmentAccountBinding!!.layoutAccount.visibility = View.INVISIBLE

        firebaseAuth = FirebaseAuth.getInstance()
        uid = firebaseAuth.currentUser?.uid.toString()
        databaseRef = FirebaseDatabase.getInstance().getReference("Users")
        if(uid.isNotEmpty()) getUserData()


//        gridView = fragmentAccountBinding!!.gridViewList
//        arrayList = ArrayList()
//        arrayList = setDataList()
//        categoriesAdapter = context?.let { GridViewAdapter(it, arrayList!!) }
//        gridView?.adapter = categoriesAdapter
//        gridView?.onItemClickListener = this

        fragmentAccountBinding!!.signOutBtn.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(AccountFragmentDirections.actionAccountFragmentToUserTypeFragment())
        }
    }

    private fun getUserData() {
        databaseRef.child(uid).addValueEventListener(object : ValueEventListener,
            AdapterView.OnItemClickListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val user = snapshot.getValue(User::class.java)
                if (user != null) {
                    if(user.name != "Эрмек")
                    fragmentAccountBinding?.accNameTv?.text = user.surname + " " + user.name
                    fragmentAccountBinding?.accEmailTv?.text = user.email
                    gridView = fragmentAccountBinding!!.gridViewList
                    arrayList = ArrayList()
                    arrayList = setDataList()
                    categoriesAdapter = context?.let { GridViewAdapter(it, arrayList!!) }
                    gridView?.adapter = categoriesAdapter
                    gridView?.onItemClickListener = this
                    fragmentAccountBinding!!.progressBarAccount.visibility = View.INVISIBLE
                    fragmentAccountBinding!!.layoutAccount.visibility = View.VISIBLE
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var item:Categories = arrayList!![p2]
                Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setDataList() : ArrayList<Categories>{

        var arrayList:ArrayList<Categories> = ArrayList()

        arrayList.add(Categories("Мои ученики", "23 ученика", "#718ef4"))
        arrayList.add(Categories("Статистика", "", "#9c5af2"))
        arrayList.add(Categories("Должность", "Преподаватель", "#86cc7f"))
        arrayList.add(Categories("Стаж", "10 лет", "#f25a61"))
        arrayList.add(Categories("Центр", "Рука в руке", "#f2ab5a"))

        return arrayList
    }

    override fun onItemClick(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var item:Categories = arrayList!![position]
        Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
    }

}