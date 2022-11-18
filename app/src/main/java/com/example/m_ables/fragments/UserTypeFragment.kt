package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m_ables.R
import com.example.m_ables.databinding.FragmentAuthBinding
import com.example.m_ables.databinding.FragmentUserTypeBinding
import com.google.firebase.auth.FirebaseAuth

class UserTypeFragment : Fragment(R.layout.fragment_user_type) {

    private var fragmentUserTypeFragment: FragmentUserTypeBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentUserTypeBinding.bind(view)
        fragmentUserTypeFragment = binding

        fragmentUserTypeFragment?.teacherBtn?.setOnClickListener {
            findNavController().navigate(UserTypeFragmentDirections.actionUserTypeFragmentToAuthFragment("teacher"))
        }
        fragmentUserTypeFragment!!.studentBtn.setOnClickListener {
            findNavController().navigate(UserTypeFragmentDirections.actionUserTypeFragmentToAuthFragment("student"))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentUserTypeFragment = null
    }

}