package com.example.kursovayafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kursovayafirebase.AuthActivity
import com.example.kursovayafirebase.RandomCallActivity
import com.example.kursovayafirebase.Request
import com.example.kursovayafirebase.RequestAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var logoutButton: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var requestsRef: DatabaseReference
    private lateinit var adapter: RequestAdapter
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        requestsRef = FirebaseDatabase.getInstance().getReference("Request")

        recyclerView = findViewById(R.id.recyclerView)
        logoutButton = findViewById(R.id.buttonLogout)
        button1 = findViewById(R.id.randomCallBTN)
        button2 = findViewById(R.id.callListBTN)
        button3 = findViewById(R.id.supportBTN)

        adapter = RequestAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addFirebaseListener()

        button1.setOnClickListener {
            val intent = Intent(this, RandomCallActivity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            Toast.makeText(this, "Button 2 Clicked", Toast.LENGTH_SHORT).show()
        }

        button3.setOnClickListener {
            Toast.makeText(this, "Button 3 Clicked", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun addFirebaseListener() {
        requestsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val requestList = mutableListOf<Request>()

                for (snapshot in dataSnapshot.children) {
                    val id = snapshot.child("ID").value.toString()
                    val nameLiver = snapshot.child("NameLiver").value.toString()
                    val issue = snapshot.child("Issue").value.toString()
                    val address = snapshot.child("Addres").value.toString()

                    val request = Request(id, nameLiver, issue, address)
                    requestList.add(request)
                }


                requestList.forEach {
                    Log.d("Firebase", "Request: $it")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Firebase", "Error reading data from Firebase", databaseError.toException())
            }
        })
    }

}
//https://www.amazon.com/dp/B08L7YXQ43/ref=cm_sw_r_as_gl_api_glt_i_QC0QZG5GYGEMD6TYPVAS?linkCode=ml1&tag=tangerine06-20&th=1
