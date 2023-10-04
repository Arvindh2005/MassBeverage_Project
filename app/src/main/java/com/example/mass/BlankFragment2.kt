package com.example.mass

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth

class BlankFragment2 : Fragment() {

    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Toast.makeText(activity, currentUser.email, Toast.LENGTH_SHORT).show()
            view?.findNavController()?.navigate(R.id.action_blankFragment2_to_blankFragment4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_loginpage, container, false)
        val createac = view.findViewById<Button>(R.id.accountcreation)
        val usernames = view.findViewById<EditText>(R.id.username)
        val passwords = view.findViewById<EditText>(R.id.password)
        val submit = view.findViewById<Button>(R.id.submitdetails)


        createac.setOnClickListener(){
            view.findNavController().navigate(R.id.action_blankFragment2_to_blankFragment3)
        }

        submit.setOnClickListener(){
            val email = usernames.text.toString()
            val password = passwords.text.toString()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(activity, "please enter an EMAIL id", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(activity, "please enter a Password", Toast.LENGTH_SHORT).show()
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        view?.findNavController()?.navigate(R.id.action_blankFragment2_to_blankFragment4)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            activity,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()

                    }
                }

        }

        return view
    }
}