package com.example.kursovayafirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RequestAdapter(private val requests: List<Request>) :
    RecyclerView.Adapter<RequestAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_request, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val request = requests[position]
        holder.bind(request)
    }

    override fun getItemCount(): Int {
        return requests.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewID: TextView = itemView.findViewById(R.id.textViewId)
        private val textViewNameLiver: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewIssue: TextView = itemView.findViewById(R.id.textViewProblem)
        private val textViewAddres: TextView = itemView.findViewById(R.id.textViewAddress)

        fun bind(request: Request) {
            textViewID.text = "ID: ${request.id}"
            textViewNameLiver.text = "NameLiver: ${request.nameLiver}"
            textViewIssue.text = "Issue: ${request.issue}"
            textViewAddres.text = "Addres: ${request.address}"
        }
    }
}

