package com.example.mass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val getstarted = view.findViewById<Button>(R.id.submit)

        getstarted.setOnClickListener(){
            view.findNavController().navigate(R.id.action_blankFragment_to_blankFragment2)
        }

        return view
    }


}