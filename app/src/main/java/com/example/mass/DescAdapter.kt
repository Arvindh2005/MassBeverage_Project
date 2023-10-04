package com.example.mass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DescAdapter (val descsList: ArrayList<DescData>):RecyclerView.Adapter<DescAdapter.descHolder>(){

        inner class descHolder(descView: View): RecyclerView.ViewHolder(descView){

            var itemPic : TextView
            var mass : TextView
            var great : ImageView


            init{
                itemPic = itemView.findViewById<TextView>(R.id.textView12)
                mass = itemView.findViewById<TextView>(R.id.textView13)
                great = itemView.findViewById<ImageView>(R.id.imageView4)

            }

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): descHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.template, parent, false)
            return descHolder(v)
        }

        override fun getItemCount(): Int {
            return descsList.size
        }

        override fun onBindViewHolder(holder: descHolder, position: Int) {
            holder.itemPic.setText(descsList[position].name)
            holder.mass.setText(descsList[position].desc)
            val imageTarget = descsList[position].link
            Picasso.get().load(imageTarget).resize(150,170).into(holder.great)

        }
}