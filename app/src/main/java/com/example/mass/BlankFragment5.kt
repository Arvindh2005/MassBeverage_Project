package com.example.mass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BlankFragment5 : Fragment() {

    private lateinit var dRef : DatabaseReference
    private lateinit var recyclerview : RecyclerView
    private lateinit var itemLists : ArrayList<PicData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_tester, container, false)
        var message = BlankFragment5Args.fromBundle(requireArguments()).massgreat


        recyclerview = view.findViewById(R.id.recyclerView3)
        recyclerview.layoutManager = GridLayoutManager(activity, 2)
        itemLists = arrayListOf<PicData>()

        val all = view.findViewById<ImageButton>(R.id.ma1)
        val mass = view.findViewById<ImageButton>(R.id.ma2)
        val vedic = view.findViewById<ImageButton>(R.id.ma3)
        val soda = view.findViewById<ImageButton>(R.id.ma4)
        val fizzy = view.findViewById<ImageButton>(R.id.ma5)

        getUserData(message)

        all.setOnClickListener(){
            itemLists.clear()
            getUserDat()
        }

        mass.setOnClickListener(){
            itemLists.clear()
            getUserData("Mass")
        }

        vedic.setOnClickListener(){
            itemLists.clear()
            getUserData("veda")
        }

        soda.setOnClickListener(){
            itemLists.clear()
            getUserData("Soda")
        }

        fizzy.setOnClickListener(){
            itemLists.clear()
            getUserData("Fizz")
        }
        return view
    }

    fun getUserData(i:String){

        dRef = FirebaseDatabase.getInstance().getReference("user")
        dRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){

                        val user = userSnapshot.getValue(PicData::class.java)

                        if (user?.name.toString().contains(i, ignoreCase = true)){
                            itemLists.add(user!!)
                        }
                    }
                    recyclerview.adapter = MyAdapter(itemLists)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getUserDat(){

        dRef = FirebaseDatabase.getInstance().getReference("user")
        dRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(PicData::class.java)
                        itemLists.add(user!!)
                    }
                    recyclerview.adapter = MyAdapter(itemLists)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}