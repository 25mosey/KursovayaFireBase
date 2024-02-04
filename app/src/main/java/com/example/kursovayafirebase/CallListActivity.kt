package com.example.kursovayafirebase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class CallListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RequestAdapter
    private lateinit var reqList: MutableList<Request>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_call_list)
        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener(View.OnClickListener {
            finish()
        })

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        reqList = mutableListOf()
        adapter = RequestAdapter(reqList)
        recyclerView.adapter = adapter

        val requestsRef = Firebase.database.getReference("Requests")
        requestsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                reqList.clear()
                for (snapshot in dataSnapshot.children) {
                    val id = snapshot.child("ID").value.toString()
                    val nameLiver = snapshot.child("NameLiver").value.toString()
                    val issue = snapshot.child("Issue").value.toString()
                    val address = snapshot.child("Addres").value.toString()
                    val request = Request(id, nameLiver, issue, address)
                    reqList.add(request)
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Firebase", "Error reading data from Firebase", databaseError.toException())
            }
        })

    }
}
