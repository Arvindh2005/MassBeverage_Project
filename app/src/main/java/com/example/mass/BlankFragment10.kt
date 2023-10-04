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
import androidx.fragment.app.findFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment10 : Fragment() {
    private lateinit var database : DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank6, container, false)
        val name = view.findViewById<EditText>(R.id.editText4)
        val phone = view.findViewById<EditText>(R.id.editText5)
        val address = view.findViewById<EditText>(R.id.editText6)
        val auth : FirebaseAuth = FirebaseAuth.getInstance()
        val save = view.findViewById<Button>(R.id.button9)
        val adname = view.findViewById<EditText>(R.id.editTextText)

        save.setOnClickListener(){
            val names = name.text
            val phones = phone.text
            database = Firebase.database.reference
            val addresss = address.text
            val adnames = adname.text

            if (TextUtils.isEmpty(names)){
                Toast.makeText(activity, "please enter a name", Toast.LENGTH_SHORT).show()
            }

            else if (TextUtils.isEmpty(phones)){
                Toast.makeText(activity, "please enter a Phone number", Toast.LENGTH_SHORT).show()
            }

            else if (TextUtils.isEmpty(addresss)){
                Toast.makeText(activity, "please enter an address", Toast.LENGTH_SHORT).show()
            }

            else if (TextUtils.isEmpty(adnames)){
                Toast.makeText(activity, "please enter an address", Toast.LENGTH_SHORT).show()
            }

            else {

                database.child(auth.uid.toString() + "address").child(adnames.toString())
                    .setValue(AddressData(names.toString(), phones.toString(), addresss.toString()))
                view.findNavController().navigate(R.id.action_blankFragment10_to_blankFragment9)
            }
        }

        return view
    }

}