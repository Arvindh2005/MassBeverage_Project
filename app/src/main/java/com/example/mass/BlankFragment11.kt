package com.example.mass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shuhart.stepview.StepView

class BlankFragment11 : Fragment() {

    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank7, container, false)
        val pri = view.findViewById<TextView>(R.id.textView25)
        val steps: MutableList<String> = ArrayList()
        val place = view.findViewById<Button>(R.id.button13)
        val a = listOf<String>("Cart", "Shipping Address", "Payment")
        for (i in 0..2) {
            steps.add(a[i])
        }

        val stepview = view.findViewById<StepView>(R.id.step_views)
        stepview.setSteps(steps)

        stepview.go(2, true)

        place.setOnClickListener(){
            view.findNavController().navigate(R.id.action_blankFragment11_to_blankFragment12)
        }

        val pe = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val u1 = snapshot.getValue()
                pri.setText(u1.toString())

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        databaseReference.child(auth.uid.toString()).child(auth.uid.toString()+"mass").child("address").addValueEventListener(pe)
        return view
    }

}