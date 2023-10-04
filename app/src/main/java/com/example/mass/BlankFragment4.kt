package com.example.mass
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BlankFragment4 : Fragment() {

    public val db = Firebase.firestore
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val databaseReference = FirebaseDatabase.getInstance().getReference()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val topview = view?.findViewById<MaterialToolbar>(R.id.toolbar)
        val view = inflater.inflate(R.layout.fragment_buy, container, false)
        val bn1 = view.findViewById<Button>(R.id.button2)
        val bn2 = view.findViewById<Button>(R.id.button3)
        val bn3 = view.findViewById<Button>(R.id.button4)
        val bn4 = view.findViewById<Button>(R.id.button5)
        var v = 0

        var name = "Mass 20ltr can"
        var pricetexts = "Rs. 90/can"
        var fake = "90"
        var price = 90
        var link = "https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fltrtwen.jpg?alt=media&token=f5090417-70b4-45c9-a830-f6df7f413f8f"

        var s = ""
        var n = 0
        val bn6 = view.findViewById<ImageButton>(R.id.imageButton)
        val bn7 = view.findViewById<ImageButton>(R.id.imageButton2)
        val bn8 = view.findViewById<ImageButton>(R.id.imageButton3)
        val bn9 = view.findViewById<ImageButton>(R.id.imageButton4)
        val bn10 = view.findViewById<Button>(R.id.button10)

        val bn11 = view.findViewById<EditText>(R.id.editText)
        val bn12 = view.findViewById<Button>(R.id.button)

        bn12.setOnClickListener(){
            s = bn11.text.toString()
            n = Integer.parseInt(s)
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
                    databaseReference.child(auth.uid.toString()).child(name).child("item").addValueEventListener(pe)
                }

                val b = async{
                    delay(3000L)
                    Log.d("TAGY",v.toString())
                    val database = Firebase.database.reference
                    database.child(auth.uid.toString()).child(name).child("item").setValue(v + n)
                    database.child(auth.uid.toString()).child(name).child("link").setValue(link)
                    database.child(auth.uid.toString()).child(name).child("price").setValue(pricetexts)
                    database.child(auth.uid.toString()).child(name).child("rs").setValue(price)
                    database.child(auth.uid.toString()).child(name).child("name").setValue(name)
                }
            }

        }

        bn10.setOnClickListener(){
            view.findNavController().navigate(R.id.action_blankFragment4_to_blankFragment7)
        }

        bn6.setOnClickListener(){
            val massgreat = "Mass"
            val action = BlankFragment4Directions.actionBlankFragment4ToBlankFragment5(massgreat)
            view.findNavController().navigate(action)
        }
        bn7.setOnClickListener(){
            val massgreat = "veda"
            val action = BlankFragment4Directions.actionBlankFragment4ToBlankFragment5(
                massgreat
            )
            view.findNavController().navigate(action)
        }
        bn8.setOnClickListener(){
            val massgreat = "Soda"
            val action = BlankFragment4Directions.actionBlankFragment4ToBlankFragment5(
                massgreat
            )
            view.findNavController().navigate(action)
        }
        bn9.setOnClickListener(){
            val massgreat = "Fizz"
            val action = BlankFragment4Directions.actionBlankFragment4ToBlankFragment5(
                massgreat
            )
            view.findNavController().navigate(action)
        }

        bn1.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val img = view?.findViewById<ImageView>(R.id.imageView2)
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1= snapshot.getValue()
                        link = u1.toString()
                        Picasso.get().load(u1.toString()).resize(500,470).into(img)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child("20ltr").addValueEventListener(pe)
            }

            CoroutineScope(Dispatchers.IO).launch {
                val pricetext= view?.findViewById<TextView>(R.id.textView10)
                val titletext= view?.findViewById<TextView>(R.id.textView9)
                val docRef = db.collection("users").document("McyUptY1SLwDOI3Erz4g")
                docRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        titletext?.text = "${document.data?.get("Name")}"
                        name = "${document.data?.get("Name")}"
                        pricetext?.text = "${document.data?.get("Price")}"
                        pricetexts = "${document.data?.get("Price")}"
                        price = 90
                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val database = Firebase.database.reference
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1 = snapshot.getValue()
                        if (u1.toString().equals("null")) {
                            database.child(auth.uid.toString()).child("Mass 20ltr can")
                                .child("item").setValue(0)
                            database.child(auth.uid.toString()).child("Mass 20ltr can")
                                .child("link")
                                .setValue("https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fltrtwen.jpg?alt=media&token=f5090417-70b4-45c9-a830-f6df7f413f8f")
                            database.child(auth.uid.toString()).child("Mass 20ltr can")
                                .child("price").setValue("Rs. 90/can")
                            database.child(auth.uid.toString()).child("Mass 20ltr can").child("rs")
                                .setValue(0)
                            database.child(auth.uid.toString()).child("Mass 20ltr can")
                                .child("name").setValue("Mass 20ltr can")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child(auth.uid.toString()).child("Mass 20ltr can")
                    .addValueEventListener(pe)
            }

        }

        bn2.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val img = view?.findViewById<ImageView>(R.id.imageView2)
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1= snapshot.getValue()
                        link = u1.toString()
                        Picasso.get().load(u1.toString()).resize(500,470).into(img)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child("veda1").addValueEventListener(pe)
            }

            CoroutineScope(Dispatchers.IO).launch {

                val pricetext= view?.findViewById<TextView>(R.id.textView10)
                val titletext= view?.findViewById<TextView>(R.id.textView9)

                val docRef = db.collection("users").document("GZIINz2ccH9dy9Cnmaa8")
                docRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        titletext?.text = "${document.data?.get("Name")}"
                        name = "${document.data?.get("Name")}"
                        pricetext?.text = "${document.data?.get("Price")}"
                        pricetexts = "${document.data?.get("Price")}"
                        price = 720

                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val database = Firebase.database.reference
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1 = snapshot.getValue()
                        if (u1.toString().equals("null")) {
                            database.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle")
                                .child("item").setValue(0)
                            database.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle")
                                .child("link")
                                .setValue("https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fnewved.jpg?alt=media&token=35088c3a-8fb6-4488-8dd4-e21af617a662")
                            database.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle")
                                .child("price").setValue("Rs. 720/case")
                            database.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle").child("rs")
                                .setValue(0)
                            database.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle")
                                .child("name").setValue("Mass Vedic 1 ltr Bottle")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child(auth.uid.toString()).child("Mass Vedic 1 ltr Bottle")
                    .addValueEventListener(pe)
            }
        }

        bn3.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val img = view?.findViewById<ImageView>(R.id.imageView2)
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1= snapshot.getValue()
                        link = u1.toString()
                        Picasso.get().load(u1.toString()).resize(500,470).into(img)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child("veda250").addValueEventListener(pe)
            }

            CoroutineScope(Dispatchers.IO).launch {

                val pricetext= view?.findViewById<TextView>(R.id.textView10)
                val titletext= view?.findViewById<TextView>(R.id.textView9)

                val docRef = db.collection("users").document("0lSNy5uowmlw4MRROYe2")
                docRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        titletext?.text = "${document.data?.get("Name")}"
                        name = "${document.data?.get("Name")}"
                        pricetext?.text = "${document.data?.get("Price")}"
                        pricetexts = "${document.data?.get("Price")}"
                        price = 180

                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val database = Firebase.database.reference
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1 = snapshot.getValue()
                        if (u1.toString().equals("null")) {
                            database.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle")
                                .child("item").setValue(0)
                            database.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle")
                                .child("link")
                                .setValue("https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fveda250.jpg?alt=media&token=f293792a-6a86-487e-b793-69920bb06cf1")
                            database.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle")
                                .child("price").setValue("Rs. 180/case")
                            database.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle").child("rs")
                                .setValue(0)
                            database.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle")
                                .child("name").setValue("Mass Vedic 250ml Bottle")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child(auth.uid.toString()).child("Mass Vedic 250ml Bottle")
                    .addValueEventListener(pe)
            }
        }

        bn4.setOnClickListener(){
            CoroutineScope(Dispatchers.IO).launch {
                val img = view?.findViewById<ImageView>(R.id.imageView2)
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object:ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1= snapshot.getValue()
                        link = "https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fnormal250.jpg?alt=media&token=c2088d57-3464-418d-9b05-48c60eacd6d3"
                        Picasso.get().load(link).resize(500,470).into(img)
                    }
                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child("normal250").addValueEventListener(pe)
            }

            CoroutineScope(Dispatchers.IO).launch {

                val pricetext= view?.findViewById<TextView>(R.id.textView10)
                val titletext= view?.findViewById<TextView>(R.id.textView9)

                val docRef = db.collection("users").document("PbXOh28juHa91bcDRU31")
                docRef.get().addOnSuccessListener { document ->
                    if (document != null) {
                        titletext?.text = "${document.data?.get("Name")}"
                        name = "${document.data?.get("Name")}"
                        pricetext?.text = "${document.data?.get("Price")}"
                        pricetexts = "${document.data?.get("Price")}"
                        price = 720

                    }
                }
            }

            CoroutineScope(Dispatchers.Main).launch {
                val database = Firebase.database.reference
                val auth: FirebaseAuth = FirebaseAuth.getInstance()
                val databaseReference = FirebaseDatabase.getInstance().getReference()
                val pe = object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val u1 = snapshot.getValue()
                        if (u1.toString().equals("null")) {
                            database.child(auth.uid.toString()).child("Mass water 250ml Bottle")
                                .child("item").setValue(0)
                            database.child(auth.uid.toString()).child("Mass water 250ml Bottle")
                                .child("link")
                                .setValue("https://firebasestorage.googleapis.com/v0/b/water-managerproject.appspot.com/o/objects%2Fnormal250.jpg?alt=media&token=c2088d57-3464-418d-9b05-48c60eacd6d3")
                            database.child(auth.uid.toString()).child("Mass water 250ml Bottle")
                                .child("price").setValue("Rs. 288/case")
                            database.child(auth.uid.toString()).child("Mass water 250ml Bottle").child("rs")
                                .setValue(0)
                            database.child(auth.uid.toString()).child("Mass water 250ml Bottle")
                                .child("name").setValue("Mass water 250ml Bottle")
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                }
                databaseReference.child(auth.uid.toString()).child("Mass water 250ml Bottle")
                    .addValueEventListener(pe)
            }
        }
        return view
    }
}