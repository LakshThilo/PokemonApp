package com.lak.pokemonapp.repo

import com.lak.pokemonapp.model.remote.apiresponse.PokeApiResponse
import com.lak.pokemonapp.model.remote.apiresponse.Pokemon
import com.lak.pokemonapp.model.remote.apiservice.PokeApi
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ActivityScoped
class PokemonRepoImpl @Inject constructor(
    private val api: PokeApi
) {

    fun getPokeListFromApi(limit: Int, offset: Int): Single<PokeApiResponse> {
        return api.getPokemonList(100, 0)
            .subscribeOn(Schedulers.io())
            .doOnSuccess {  }
            .doOnError {  }
    }

    fun getPokemonFromApi(id: Int): Call<Pokemon> {
        return api.getPokemonInfo(id)
    }
}