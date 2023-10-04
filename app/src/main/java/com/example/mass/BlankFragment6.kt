package com.example.mass

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
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
import com.google.firebase.database.ktx.getValue
import com.google.firebase.database.ktx.values
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class BlankFragment6 : Fragment() {

    private lateinit var dRef : DatabaseReference
    lateinit var database : DatabaseReference
    private lateinit var recyclerview : RecyclerView
    private lateinit var itemLists : ArrayList<DescData>
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference()
    var prodname : String = ""
    var prodprice : String = ""
    var price : Int = 0
    var prodlink : String = ""
    var prodrs = 0
    var v = 100

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_blank2, container, false)
        var message = BlankFragment6Args.fromBundle(requireArguments()).massgrea

        var cart = view.findViewById<Button>(R.id.button26)

        var carts = view.findViewById<Button>(R.id.button24)

        val lgout = view.findViewById<Button>(R.id.button25)

        carts.setOnClickListener(){
            view.findNavController().navigate(R.id.action_blankFragment6_to_blankFragment7)
        }

        lgout.setOnClickListener(){

            auth.signOut()
            view.findNavController().navigate(R.id.action_blankFragment6_to_blankFragment2)

        }

        recyclerview = view.findViewById(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        itemLists = arrayListOf<DescData>()

        getUserData(message)
        cart.setOnClickListener(){

            GlobalScope.launch(Dispatchers.IO){
                var g = false
                val a = async{
                    val pe = object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val u1 = snapshot.getValue()
                            if (u1.toString().equals("0")){
                                v = 0
                                g = true
                            }
                            else{
                                v = u1.toString().toInt()
                                g = true
                            }
                        }
                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    }
                    databaseReference.child(auth.uid.toString()).child(prodname).child("item").addValueEventListener(pe)
                }

                val b = async{
                    delay(3000L)
                    Log.d("TAGY",v.toString())
                    val database = Firebase.database.reference
                    database.child(auth.uid.toString()).child(prodname).child("item").setValue(v + 1)
                    database.child(auth.uid.toString()).child(prodname).child("link").setValue(prodlink)
                    database.child(auth.uid.toString()).child(prodname).child("price").setValue(prodprice)
                    database.child(auth.uid.toString()).child(prodname).child("rs").setValue(prodrs)
                    database.child(auth.uid.toString()).child(prodname).child("name").setValue(prodname)

                }

            }

        }
        return view
    }

    fun getUserData(i:String){

        dRef = FirebaseDatabase.getInstance().getReference("user")
        dRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (userSnapshot in snapshot.children){
                        val user = userSnapshot.getValue(DescData::class.java)
                        if (user?.name.toString().equals(i, ignoreCase = true)){
                            itemLists.add(user!!)
                            prodname = user?.name.toString()
                            prodprice = user?.price.toString()
                            prodlink = user?.link.toString()
                            prodrs = user?.rs!!.toInt()
                        }
                    }
                    recyclerview.adapter = DescAdapter(itemLists)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}