package com.lak.pokemonapp.ui.pokeInfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.lak.pokemonapp.databinding.ActivityPokeinfoBinding
import com.lak.pokemonapp.model.remote.apiresponse.Pokemon
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeinfoBinding
    private lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)

        val id = intent.extras?.get("id") as Int
        viewModel.getPokemonInfo(id)

        viewModel.state.observe(this, { pokemon ->
            initializeViews(pokemon)
        })

    }

    private fun initializeViews(pokemon: Pokemon) {

        binding.tvPokeName.text = "##${pokemon.id}  ${pokemon.name}"
        binding.tvHeight.text = "Height: ${pokemon.height/10.0}m"
        binding.tvWeight.text = "Weight: ${pokemon.weight/10.0}"

        Glide.with(this).load(pokemon.sprites.frontDefault).into(binding.ivPokeImage)
    }

}