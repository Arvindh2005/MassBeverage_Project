package com.example.mass

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
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

class BlankFragment7 : Fragment() {
    private lateinit var dRef : DatabaseReference
    private lateinit var recyclerview : RecyclerView
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var itemLists : ArrayList<ListItem>
    var total_price = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank3, container, false)
        val check = view.findViewById<Button>(R.id.button7)
        val steps: MutableList<String> = ArrayList()
        val a = listOf<String>("Cart", "Shipping Address", "Payment")
        for (i in 0..2) {
            steps.add(a[i])
        }

        val stepview = view.findViewById<StepView>(R.id.step_view)
        stepview.setSteps(steps)
        check.setOnClickListener(){
            val database = Firebase.database.reference
            database.child(auth.uid.toString()).child(auth.uid.toString()+"mass").child("address").setValue(total_price)
            view.findNavController().navigate(R.id.action_blankFragment7_to_blankFragment9)
        }

        recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView2)
        recyclerview.layoutManager = LinearLayoutManager(context)
        itemLists = arrayListOf<ListItem>()

        getUserData()

        return view
    }

    fun getUserData(){

        dRef = FirebaseDatabase.getInstance().getReference(auth.uid.toString())
        dRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    total_price = 0
                    itemLists.clear()

                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(ListItem.Commodity::class.java)
                        if (user?.item!! > 0) {
                            itemLists.add(user!!)
                            total_price += user!!.rs*user!!.item
                        }
                    }
                    Log.d("TAGY", "HI")
                    itemLists.add(ListItem.Price("Rs".plus(total_price.toString())))
                    recyclerview.adapter = MassAdapter(itemLists)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

}