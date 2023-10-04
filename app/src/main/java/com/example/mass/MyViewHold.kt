package com.example.mass

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyViewHold(itemView : View): RecyclerView.ViewHolder(itemView) {

    fun bind(item: ListItem.Price){

        val pri = itemView.findViewById<TextView>(R.id.textView11)
        pri.text = item.text
    }
}

class ImgViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    fun bind(item : ListItem.Commodity){
        val comm = itemView.findViewById<ImageView>(R.id.imageView5)
        Picasso.get().load(item.link).resize(150,170).into(comm)
    }
}