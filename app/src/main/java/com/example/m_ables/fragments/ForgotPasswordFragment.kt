package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.m_ables.R
import com.example.m_ables.databinding.FragmentAuthBinding
import com.example.m_ables.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment() {

    private var fragmentForgotPasswordBinding : FragmentForgotPasswordBinding? = null
    private val binding get() = fragmentForgotPasswordBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        fragmentForgotPasswordBinding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentForgotPasswordBinding = null
    }
}