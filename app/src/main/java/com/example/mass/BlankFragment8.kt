package com.example.mass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment8 : Fragment() {

    private lateinit var database : DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank4, container, false)
        val next = view.findViewById<Button>(R.id.button6)
        val id = view.findViewById<EditText>(R.id.editText2)
        val databaseReference = FirebaseDatabase.getInstance().getReference()
        next.setOnClickListener() {
            val pe = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val u1 = snapshot.getValue()
                    if (u1.toString().equals("null")){
                        val a = Cart(id.text.toString())
                        database = Firebase.database.reference
                        database.child("userid").setValue(a)
                        database.child(id.text.toString()).child("Mass 20 ltr water can").setValue(RealCart(0))
                        val massgre = id.text.toString()

                    }else{
                        Toast.makeText(activity, "UserID already taken", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            databaseReference.child(id.text.toString()).addValueEventListener(pe)
        }
        return view
    }

}