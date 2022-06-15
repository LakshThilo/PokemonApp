package com.lak.pokemonapp.ui.pokelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.lak.pokemonapp.databinding.ActivityPokemonlistBinding
import com.lak.pokemonapp.ui.adapter.PokemonListAdapter
import com.lak.pokemonapp.ui.pokeInfo.PokeInfoActivity
import com.lak.pokemonapp.utils.PokemonListState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PokemonListActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonListViewModel
    private lateinit var binding: ActivityPokemonlistBinding
    private lateinit var pokeAdapter: PokemonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonlistBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this).get(PokemonListViewModel::class.java)

        setRecyclerView()

        viewModel.fetchPokemonList()

        viewModel.state.observe(this, Observer { response ->
            when(response){
                is PokemonListState.PokemonList -> pokeAdapter.differ.submitList(response.pokemon)
                PokemonListState.Error -> { }
            }
        })
    }

    private fun setRecyclerView(){

        pokeAdapter = PokemonListAdapter{
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            startActivity(intent)
        }

        binding.pokelistRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pokeAdapter
        }

    }
}