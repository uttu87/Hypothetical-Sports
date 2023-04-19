package com.isea.hypotheticalsports.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.isea.hypotheticalsports.databinding.MatchItemBinding
import com.isea.hypotheticalsports.model.Previous
import com.isea.hypotheticalsports.model.Upcoming

class MatchAdapter : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(private val binding: MatchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(match: Any) {
            if (match is Previous) {
                binding.txtName.text = match.description
                binding.txtDate.text = match.date
                binding.btnHighlight.visibility = View.VISIBLE
                binding.btnHighlight.setOnClickListener {
                    onHighlightListener?.play(match.highlights)
                }
            } else if (match is Upcoming) {
                binding.txtName.text = match.description
                binding.txtDate.text = match.date
                binding.btnHighlight.visibility = View.GONE
            }
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Any,
            newItem: Any
        ): Boolean {
            return false
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var matches: List<Any>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    var onHighlightListener: HighlightListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = MatchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val team = matches[position]

        holder.bind(team)
    }

    interface HighlightListener {
        fun play(url: String)
    }
}