package com.example.mass

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BlankFragment3 : Fragment() {
    private lateinit var database : DatabaseReference
    val db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_register, container, false)
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val username = view.findViewById<EditText>(R.id.usernames)
        val progress = view.findViewById<ProgressBar>(R.id.progress_bar)
        val password = view.findViewById<EditText>(R.id.passwords)
        val submit = view.findViewById<Button>(R.id.submitdetailss)

        submit.setOnClickListener(){

            progress.visibility = View.VISIBLE

            val email: String = username.text.toString()
            val password:String = password.text.toString()

            if (TextUtils.isEmpty(email)){
                Toast.makeText(activity, "please enter an EMAIL id", Toast.LENGTH_SHORT).show()
            }

            if (TextUtils.isEmpty(password)){
                Toast.makeText(activity, "please enter a Password", Toast.LENGTH_SHORT).show()
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(activity, "Success", Toast.LENGTH_SHORT).show()
                        progress.visibility = View.GONE
                        database = Firebase.database.reference
                        database.child(auth.uid.toString()).child("Mass 20ltr can").setValue(Finaldata(0, "https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fltrtwen.jpg?alt=media&token=f5090417-70b4-45c9-a830-f6df7f413f8f", "Rs. 90/can", 90, "Mass 20ltr can"))
                        database.child(auth.uid.toString()+"address").child("address").setValue(AddressData("","",""))

                        view.findNavController().navigate(R.id.action_blankFragment3_to_blankFragment4)

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