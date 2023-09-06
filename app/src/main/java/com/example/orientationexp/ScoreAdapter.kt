package com.example.orientationexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ScoreAdapter(var rdata: List<Score>) : RecyclerView.Adapter<ScoreAdapter.rViewHolder>() {
    inner class rViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_display, parent, false)
        return rViewHolder(view)
    }

    override fun onBindViewHolder(holder: rViewHolder, position: Int) {
        holder.itemView.apply {
            val nameTextView = findViewById<TextView>(R.id.nameTextview)
            val scoreTextView = findViewById<TextView>(R.id.scoreTextview)
            nameTextView.text = rdata[position].name
            scoreTextView.text = rdata[position].score.toString()
        }
    }

    override fun getItemCount(): Int {
        return rdata.size
    }
}