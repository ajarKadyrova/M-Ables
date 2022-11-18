package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m_ables.R
import com.google.firebase.auth.FirebaseAuth


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        firebaseAuth = FirebaseAuth.getInstance()
//        if(firebaseAuth.currentUser == null){
//            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToUserTypeFragment())
//        }
    }


}