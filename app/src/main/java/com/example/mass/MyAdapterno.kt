package com.example.mass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class MyAdapterno(val addressList : ArrayList<AddressData>):RecyclerView.Adapter<MyAdapterno.addressHolder>(){
    inner class addressHolder(addressView : View):RecyclerView.ViewHolder(addressView){
        var name : TextView
        var phone : TextView
        var address : TextView
        var cont : Button

        init{
            name = itemView.findViewById<TextView>(R.id.textView21)
            phone = itemView.findViewById<TextView>(R.id.textView22)
            address = itemView.findViewById<TextView>(R.id.textView23)
            cont = itemView.findViewById<Button>(R.id.button12)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapterno.addressHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cardnew, parent, false)
        return addressHolder(v)
    }
    override fun onBindViewHolder(holder: MyAdapterno.addressHolder, position: Int) {
        holder.name.setText(addressList[position].name)
        holder.phone.setText(addressList[position].phone)
        holder.address.setText(addressList[position].address)
        holder.cont.setOnClickListener(){
            holder.itemView.findNavController().navigate(R.id.action_blankFragment9_to_blankFragment11)
        }
    }
    override fun getItemCount(): Int {
        return addressList.size
    }
}