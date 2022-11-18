package com.example.m_ables.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.m_ables.Model.Student
import com.example.m_ables.R
import com.example.m_ables.databinding.FragmentAuthBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private var fragmentAuthBinding : FragmentAuthBinding? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val args:AuthFragmentArgs by navArgs()
    var userType:String = ""
    var listener: ((category: String) -> Unit)? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        userType = args.userType
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentAuthBinding.bind(view)
        fragmentAuthBinding = binding

        firebaseAuth = FirebaseAuth.getInstance()

        fragmentAuthBinding?.createAccBtn?.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment(userType))
        }
        fragmentAuthBinding!!.forgotPasswordBtn.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToForgotPasswordFragment())
        }
        fragmentAuthBinding?.signInBtn?.setOnClickListener {
            fragmentAuthBinding!!.signInBtn.visibility = View.GONE
            fragmentAuthBinding!!.progressBarAuth.visibility = View.VISIBLE
            validateData()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        fragmentAuthBinding = null
    }

    private var email = ""
    private var password = ""
    private fun validateData() {
        email = fragmentAuthBinding?.loginAuth?.text.toString().trim()
        password = fragmentAuthBinding?.passwordAuth?.text.toString().trim()

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            fragmentAuthBinding!!.signInBtn.visibility = View.VISIBLE
            fragmentAuthBinding!!.progressBarAuth.visibility = View.GONE
            Toast.makeText(context, resources.getText(R.string.invalid_email), Toast.LENGTH_SHORT).show()
        }
        else if(password.isEmpty() || password.length < 8){
            fragmentAuthBinding!!.signInBtn.visibility = View.VISIBLE
            fragmentAuthBinding!!.progressBarAuth.visibility = View.GONE
            Toast.makeText(context, resources.getText(R.string.invalid_password), Toast.LENGTH_LONG).show()
        }
        else loginUser()
    }

    private fun loginUser() {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                checkUser()
                if(userType == "teacher")
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToStudentsFragment())
                else if(userType == "student"){
                    sendCategoryToListener(userType)
                    val student = Student("Атаев", "Эрмек", "20.15.2014", "")
                    findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToStudentInfoFragment(student))
                }
            }
            .addOnFailureListener {
                fragmentAuthBinding!!.signInBtn.visibility = View.VISIBLE
                fragmentAuthBinding!!.progressBarAuth.visibility = View.GONE
                Toast.makeText(context, resources.getText(R.string.registration_error), Toast.LENGTH_LONG).show()
            }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        val ref = FirebaseDatabase.getInstance().getReference("Users")
        ref.child(firebaseUser!!.uid)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userType = snapshot.child("userType").value
//                    if(userType == "teacher"){
//                        Toast.makeText(context, "teacher", Toast.LENGTH_LONG).show()
//                    }
//                    else if (userType == "child"){
//                        Toast.makeText(context, "child", Toast.LENGTH_LONG).show()
//                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })

    }

    fun sendCategoryToListener(category: String) {
        listener?.invoke(category)
    }
}