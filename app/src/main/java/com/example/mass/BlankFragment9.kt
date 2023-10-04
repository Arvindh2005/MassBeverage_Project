package com.example.mass

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.shuhart.stepview.StepView

class BlankFragment9 : Fragment() {

    private lateinit var dRef : DatabaseReference
    private lateinit var recyclerview : RecyclerView
    private lateinit var itemList : ArrayList<AddressData>
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank5, container, false)
        var cont = view.findViewById<Button>(R.id.button8)
        val steps: MutableList<String> = ArrayList()
        val a = listOf<String>("Cart", "Shipping Address", "Payment")
        for (i in 0..2) {
            steps.add(a[i])
        }

        val stepview = view.findViewById<StepView>(R.id.step_views)
        stepview.setSteps(steps)
        recyclerview = view.findViewById(R.id.cardView2)

        stepview.go(1, true)
        recyclerview.layoutManager = LinearLayoutManager(context)
        itemList = arrayListOf<AddressData>()

        cont.setOnClickListener(){

            view.findNavController().navigate(R.id.action_blankFragment9_to_blankFragment10)
        }

        getUserDat()
        return view
    }

    fun getUserDat(){
        Log.d("TAGY",auth.uid.toString()+"address" )
        dRef = FirebaseDatabase.getInstance().getReference(auth.uid.toString()+"address")
        dRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val users = userSnapshot.getValue(AddressData::class.java)
                        if (users!!.address != "") {
                            itemList.add(users!!)
                        }
                    }
                    recyclerview.adapter = MyAdapterno(itemList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}