package com.example.android.quizapp.adapter

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
import com.example.android.quizapp.R
import com.example.android.quizapp.activites.QuestionActivity
import com.example.android.quizapp.model.Quiz
import com.example.android.quizapp.utils.ColorPicker
import com.example.android.quizapp.utils.IconPicker

class QuizAdapter(val context: Context, val quizzes: List<Quiz>) :
    RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
   val view:View=LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
     holder.textview.text= quizzes[position].title
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))
        holder.imageView.setImageResource(IconPicker.getIcon())
        holder.imageView.setOnClickListener{
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()
            val intent=Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    inner class QuizViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        var textview: TextView = itemview.findViewById(R.id.quizTitle)
        var imageView: ImageView = itemview.findViewById(R.id.quizIcon)
        var cardContainer: CardView = itemview.findViewById(R.id.cardContainer)

    }
}