package com.lak.pokemonapp.model.remote.apiservice

import com.lak.pokemonapp.model.remote.apiresponse.PokeApiResponse
import com.lak.pokemonapp.model.remote.apiresponse.Pokemon
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>
    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Single<PokeApiResponse>
}