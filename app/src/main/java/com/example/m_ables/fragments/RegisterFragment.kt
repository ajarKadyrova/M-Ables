package com.example.m_ables.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.m_ables.R
import com.example.m_ables.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private var fragmentRegisterBinding : FragmentRegisterBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val args:RegisterFragmentArgs by navArgs()
    private var userType:String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout for this fragment
        val binding = FragmentRegisterBinding.bind(view)
        fragmentRegisterBinding = binding

        firebaseAuth = FirebaseAuth.getInstance()

        fragmentRegisterBinding!!.registerBtn.setOnClickListener{
            validateData()
        }
        userType = args.userType

        fragmentRegisterBinding!!.createAccBtn.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToAuthFragment(""))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentRegisterBinding = null
    }

    private var surname = ""
    private var name = ""
    private var birthdate = ""
    private var email = ""
    private var password = ""

    private fun validateData() {
        surname = fragmentRegisterBinding?.surnameTiet?.text.toString().trim()
        name = fragmentRegisterBinding?.nameTiet?.text.toString().trim()
        birthdate = fragmentRegisterBinding?.birthDateTiet?.text.toString().trim()
        email = fragmentRegisterBinding?.emailTiet?.text.toString().trim()
        password = fragmentRegisterBinding?.passwordRegisterTiet?.text.toString().trim()

        when {
            surname.isEmpty() -> Toast.makeText(context, resources.getText(R.string.enter_surname), Toast.LENGTH_SHORT).show()
            name.isEmpty() -> Toast.makeText(context, resources.getText(R.string.enter_name), Toast.LENGTH_SHORT).show()
            birthdate.isEmpty() -> Toast.makeText(context, resources.getText(R.string.enter_birthdate), Toast.LENGTH_SHORT).show()
            email.isEmpty() -> Toast.makeText(context, resources.getText(R.string.enter_email), Toast.LENGTH_SHORT).show()
            password.isEmpty() -> Toast.makeText(context, resources.getText(R.string.enter_password), Toast.LENGTH_SHORT).show()
            else -> createUserAccount()
        }
    }

    private fun createUserAccount() {
        fragmentRegisterBinding?.registerBtn?.visibility = View.GONE
        fragmentRegisterBinding?.progressBarRegistration?.visibility = View.VISIBLE

        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {
                updateUserInfo()
            }
            .addOnFailureListener {
                fragmentRegisterBinding?.registerBtn?.visibility = View.VISIBLE
                fragmentRegisterBinding?.progressBarRegistration?.visibility = View.GONE
                Toast.makeText(context, resources.getText(R.string.registration_error), Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateUserInfo() {
        val timestamp = System.currentTimeMillis()
        val uid = firebaseAuth.uid
        val hashMap: HashMap<String, Any?> = HashMap()
        hashMap["uid"] = uid
        hashMap["email"] = email
        hashMap["surname"] = surname
        hashMap["name"] = name
        hashMap["birthdate"] = birthdate
        hashMap["profileImage"] = ""
        hashMap["userType"] = userType
        hashMap["timestamp"] = timestamp

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(uid!!)
            .setValue(hashMap)
            .addOnSuccessListener {
                Toast.makeText(context, resources.getText(R.string.registration_success), Toast.LENGTH_SHORT).show()
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToAuthFragment(""))
            }.addOnFailureListener {e->
                Toast.makeText(context, resources.getText(R.string.registration_data_error), Toast.LENGTH_SHORT).show()
            }
    }

}