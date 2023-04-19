package com.isea.hypotheticalsports.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.isea.hypotheticalsports.R
import com.isea.hypotheticalsports.databinding.TeamItemBinding
import com.isea.hypotheticalsports.model.Team

class TeamAdapter : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(private val binding: TeamItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.name.text = team.name
            Glide.with(binding.logo.context)
                .load(team.logo)
                .placeholder(R.drawable.ic_team)
                .into(binding.logo)
        }
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(
            oldItem: Team,
            newItem: Team
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Team,
            newItem: Team
        ): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var teams: List<Team>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team = teams[position]

        holder.bind(team)
    }
}