package com.lak.pokemonapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.lak.pokemonapp.databinding.SinglePokemonSearchBinding
import com.lak.pokemonapp.model.remote.apiresponse.PokeResult


class PokemonListAdapter(val pokemonClick: (Int) -> Unit): RecyclerView.Adapter<PokemonListAdapter.SearchViewHolder>() {

    private val diffCallback = object: DiffUtil.ItemCallback<PokeResult>(){
        override fun areItemsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: PokeResult, newItem: PokeResult): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = SinglePokemonSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = differ.currentList[position] as PokeResult
        holder.bind(pokemon, position)

        holder.itemView.setOnClickListener { pokemonClick(position + 1) }
    }

    inner class SearchViewHolder(private val binding: SinglePokemonSearchBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: PokeResult, position: Int) {
            binding.pokemonText.text = pokemon.name
        }
    }
}