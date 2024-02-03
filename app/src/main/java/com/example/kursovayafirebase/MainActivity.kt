package com.example.kursovayafirebase

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
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
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private var reqList: MutableList<Request> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var logoutButton: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var adapter: RequestAdapter
    private lateinit var rndBtn: Button
    private lateinit var callBtn: Button
    private lateinit var supBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        logoutButton = findViewById(R.id.buttonLogout)
        rndBtn = findViewById(R.id.randomCallBTN)
        callBtn = findViewById(R.id.callListBTN)
        supBtn = findViewById(R.id.supportBTN)

        recyclerView.layoutManager = LinearLayoutManager(this)

        mAuth = FirebaseAuth.getInstance()


        rndBtn.setOnClickListener {
            val intent = Intent(this, RandomCallActivity::class.java)
            startActivity(intent)
        }

        callBtn.setOnClickListener {
            Toast.makeText(this, "callBtn Clicked", Toast.LENGTH_SHORT).show()
        }

        supBtn.setOnClickListener {
            Toast.makeText(this, "supBtn Clicked", Toast.LENGTH_SHORT).show()
        }

        logoutButton.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }

      //  val requestsRef = FirebaseDatabase.getInstance().getReference("Requests")
        val requestsRef=Firebase.database.getReference("Requests")
        requestsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val id = snapshot.child("ID").value.toString()
                    val nameLiver = snapshot.child("NameLiver").value.toString()
                    val issue = snapshot.child("Issue").value.toString()
                    val address = snapshot.child("Addres").value.toString()
                    val request = Request(id, nameLiver, issue, address)
                    reqList.add(request)
                }
                adapter = RequestAdapter(reqList)
                recyclerView.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("Firebase", "Error reading data from Firebase", databaseError.toException())
            }
        })
    }
}
//https://www.amazon.com/dp/B08L7YXQ43/ref=cm_sw_r_as_gl_api_glt_i_QC0QZG5GYGEMD6TYPVAS?linkCode=ml1&tag=tangerine06-20&th=1
