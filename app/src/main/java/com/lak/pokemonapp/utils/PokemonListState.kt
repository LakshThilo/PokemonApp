package com.lak.pokemonapp.utils

import com.lak.pokemonapp.model.remote.apiresponse.PokeResult


sealed class PokemonListState {
    data class PokemonList(var pokemon: List<PokeResult>):PokemonListState()
    object Error :PokemonListState()

}