package com.example.android.quizapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.quizapp.R
import com.example.android.quizapp.model.Question

class optionAdapter(val context: Context, val question: Question) :
    RecyclerView.Adapter<optionAdapter.OptionViewHolder>() {

    private var options: List<String> =
        listOf(question.option1, question.option2, question.option3, question.option4)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item, parent, false)
        return OptionViewHolder(view)
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener {

            question.useranswer = options[position]
            notifyDataSetChanged()

        }

        if (question.useranswer == options[position]) {
            holder.itemView.setBackgroundResource(R.drawable.option_item_selectedbg)
        } else {
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)

        }
    }

    override fun getItemCount(): Int {
        return options.size
    }

    inner class OptionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var optionView: TextView = itemView.findViewById(R.id.quiz_option)
    }

}