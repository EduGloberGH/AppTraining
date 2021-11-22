package com.edu.glob.pokeapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.glob.pokeapp.Stats
import com.edu.glob.pokeapp.databinding.ItemStatBinding

class PokemonStatsListAdapter (var stats: ArrayList<Stats>): RecyclerView.Adapter<PokemonStatsListAdapter.StatsViewHolder>() {

    fun updateStats(newStats: List<Stats>){
        stats.clear()
        stats.addAll(newStats)
        notifyDataSetChanged()
    }

    inner class StatsViewHolder(private val binding: ItemStatBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(stat:Stats){
            binding.apply {
               statName.text = stat.stat.name
                if (stat.stat.name.contains("-")){
                    val first = stat.stat.name.substringBefore("-")
                    val second = stat.stat.name.substringAfter("-")

                    "$first- $second".also { statName.text = it }
                }
                statCount.text = stat.base_stat.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
       val binding = ItemStatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(stats[position])
    }

    override fun getItemCount() = stats.size

}