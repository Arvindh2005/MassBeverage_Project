package com.example.mass

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyAdapter(val itemsList: ArrayList<PicData>) :RecyclerView.Adapter<MyAdapter.itemHolder>(){

    inner class itemHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var itemPic : TextView
        var mass : TextView
        var great : ImageView


        init{
            itemPic = itemView.findViewById<TextView>(R.id.idTVCourse)
            mass = itemView.findViewById<TextView>(R.id.idTVCourses)
            great = itemView.findViewById<ImageView>(R.id.massC)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.templatecard, parent, false)
        return itemHolder(v)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: itemHolder, position: Int) {
        val massgrea = itemsList[position].name
        var name = holder.adapterPosition
        holder.itemPic.setText(itemsList[name].name)
        holder.mass.setText(itemsList[name].Price)
        val imageTarget = itemsList[name].link
        Picasso.get().load(imageTarget).resize(150,170).into(holder.great)
        holder.itemView.setOnClickListener(){view ->
            CoroutineScope(Dispatchers.Main).launch {
                val database = Firebase.database.reference
                val auth : FirebaseAuth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1 = snapshot.getValue()
                        if (u1.toString().equals("null")){
                            database.child(auth.uid.toString()).child(itemsList[name].name).child("item").setValue(0)
                            database.child(auth.uid.toString()).child(itemsList[name].name).child("link").setValue(itemsList[name].link)
                            database.child(auth.uid.toString()).child(itemsList[name].name).child("price").setValue(itemsList[name].Price)
                            database.child(auth.uid.toString()).child(itemsList[name].name).child("rs").setValue(0)
                            database.child(auth.uid.toString()).child(itemsList[name].name).child("name").setValue(itemsList[name].name)
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child(auth.uid.toString()).child(itemsList[name].name).addValueEventListener(pe)


                val action = BlankFragment5Directions.actionBlankFragment5ToBlankFragment6(massgrea)
                view.findNavController().navigate(action)
            }

        }

    }

}