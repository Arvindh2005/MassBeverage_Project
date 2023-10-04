package com.example.mass

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class BlankFragment12 : Fragment() {
    private lateinit var dRef : DatabaseReference
    lateinit var database : DatabaseReference
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank8, container, false)
        val back = view.findViewById<Button>(R.id.button14)
        if (! Python.isStarted()) {
            Python.start(AndroidPlatform(requireActivity()))
        }
        val py = Python.getInstance()
        val module = py.getModule("script")
        val into = module["great"]
        var b = into?.call(auth.currentUser?.email).toString()

        back.setOnClickListener(){
            dRef = FirebaseDatabase.getInstance().getReference(auth.uid.toString())
            dRef.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        for (userSnapshot in snapshot.children){
                            val user = userSnapshot.getValue(ListItem.Commodity::class.java)
                            if (user?.item!! > 0) {
                                database = Firebase.database.reference
                                database.child(auth.uid.toString()).child(user.name).child("item").setValue(0)
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
            view.findNavController().navigate(R.id.action_blankFragment12_to_blankFragment4)
        }
        return view
    }

}