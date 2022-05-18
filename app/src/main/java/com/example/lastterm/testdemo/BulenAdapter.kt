package com.example.lastterm.testdemo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.R
import com.example.lastterm.models.Quiz
import com.example.lastterm.util.Coloer
import com.example.lastterm.util.IconPicker

class BulenAdapter(val context : Context , val quizzes : List<Quiz>) :
    RecyclerView.Adapter<BulenAdapter.BulenViewHolder>() {


        inner class BulenViewHolder(item : View) : RecyclerView.ViewHolder(item) {
            var textViewTitle : TextView = item.findViewById(R.id.quizTitle)
            var iconView : ImageView = item.findViewById(R.id.quizIcon)
            var cardView : CardView = item.findViewById(R.id.cardContainer)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BulenViewHolder {
        val view  = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return BulenViewHolder(view)
    }

    override fun onBindViewHolder(holder: BulenViewHolder, position: Int) {
            holder.textViewTitle.text = quizzes[position].title
            holder.cardView.setCardBackgroundColor(Color.parseColor(Coloer.getColor()))
            holder.iconView.setImageResource(IconPicker.getIcon())
            holder.itemView.setOnClickListener {
                Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            }
    }

    override fun getItemCount(): Int {
       return quizzes.size
    }


}