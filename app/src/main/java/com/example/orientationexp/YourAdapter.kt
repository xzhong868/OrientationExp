package com.example.orientationexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class YourAdapter(var rdata: List<User>) : RecyclerView.Adapter<YourAdapter.rViewHolder>() {

    inner class rViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstnameTextView: TextView = itemView.findViewById(R.id.firstnametextView)
        val lastnameTextView: TextView = itemView.findViewById(R.id.lastnametextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.data_display, parent, false)
        return rViewHolder(view)
    }

    override fun onBindViewHolder(holder: rViewHolder, position: Int) {
        holder.itemView.apply {
            val firstnameTextView = findViewById<TextView>(R.id.firstnametextView)
            val lastnameTextView = findViewById<TextView>(R.id.lastnametextView)
            firstnameTextView.text = rdata[position].first_name
            lastnameTextView.text = rdata[position].last_name
        }
    }

    override fun getItemCount(): Int {
        return rdata.size
    }

}