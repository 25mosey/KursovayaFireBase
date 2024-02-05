package com.example.kursovayafirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestAdapter(private val requests: List<Request>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>()  {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewID: TextView = itemView.findViewById(R.id.textViewId)
        val textViewNameLiver: TextView = itemView.findViewById(R.id.textViewName)
        val textViewIssue: TextView = itemView.findViewById(R.id.textViewProblem)
        val textViewAddres: TextView = itemView.findViewById(R.id.textViewAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_request, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = requests[position]
        holder.textViewID.text = request.id
        holder.textViewNameLiver.text = request.nameLiver
        holder.textViewIssue.text = request.issue
        holder.textViewAddres.text = request.address


    }

    override fun getItemCount(): Int {
        return requests.size
    }

}
