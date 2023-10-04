package com.example.mass

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MassAdapter (private val items: List<ListItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    inner class MyViewHold(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item: ListItem.Price){
            val pri = itemView.findViewById<TextView>(R.id.textView11)
            pri.text = item.text
        }
    }

    class ImgViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(item : ListItem.Commodity){
            val comm = itemView.findViewById<ImageView>(R.id.imageView5)
            val name = itemView.findViewById<TextView>(R.id.textView38)
            val realprice = itemView.findViewById<TextView>(R.id.textView28)
            val greats = itemView.findViewById<TextView>(R.id.textView40)
            val cartitemno = itemView.findViewById<TextView>(R.id.textView39)
            cartitemno.text = item.item.toString()
            var re = item.rs * item.item
            greats.text = re.toString()
            name.text = item.name
            realprice.text = item.price
            Picasso.get().load(item.link).resize(130,170).into(comm)
            val minus = itemView.findViewById<Button>(R.id.button11)
            val plus = itemView.findViewById<Button>(R.id.button15)
            val auth : FirebaseAuth = FirebaseAuth.getInstance()
            var v = item.item
            plus.setOnClickListener(){
                val database = Firebase.database.reference
                database.child(auth.uid.toString()).child(item.name).child("item").setValue(item.item + 1)
                v += 1
                item.item += 1
                re = item.rs * item.item
                cartitemno.setText(v.toString())

            }

            minus.setOnClickListener(){
                val database = Firebase.database.reference
                database.child(auth.uid.toString()).child(item.name).child("item").setValue(item.item - 1)
                v -= 1
                item.item -= 1
                cartitemno.setText(v.toString())
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]){
            is ListItem.Price -> 0
            is ListItem.Commodity -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType){
            0 -> MyViewHold(LayoutInflater.from(parent.context).inflate(R.layout.pricetemplate, parent, false))
            1 -> ImgViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.imgtemplate, parent, false))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]){
            is ListItem.Price -> (holder as MyViewHold).bind(item)
            is ListItem.Commodity -> (holder as ImgViewHolder).bind(item)
        }
    }

}