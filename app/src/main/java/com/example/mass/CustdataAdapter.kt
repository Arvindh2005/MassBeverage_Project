package com.example.mass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class CustdataAdapter(val massList : ArrayList<CustData>):RecyclerView.Adapter<CustdataAdapter.essentialHolder>() {
    inner class essentialHolder(essentialView : View):RecyclerView.ViewHolder(essentialView){
        var essential : EditText
        init{
            essential = itemView.findViewById<EditText>(R.id.editText3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): essentialHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.getterslayout, parent, false)
        return essentialHolder(v)
    }

    override fun getItemCount(): Int {
        return massList.size
    }

    override fun onBindViewHolder(holder: essentialHolder, position: Int) {
        holder.essential.setHint(massList[position].data)
    }
}