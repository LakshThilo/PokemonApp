package com.lak.pokemonapp.ui.pokeInfo


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lak.pokemonapp.model.remote.apiresponse.Pokemon
import com.lak.pokemonapp.repo.PokemonRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PokeInfoViewModel @Inject constructor(
    private val pokemonRepoImpl: PokemonRepoImpl
) : ViewModel() {

    private val _state = MutableLiveData<Pokemon>()
    val state: LiveData<Pokemon> = _state

    fun getPokemonInfo(id: Int){

        pokemonRepoImpl.getPokemonFromApi(id).enqueue(object : Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    _state.postValue(pokemon)
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }
        })
    }
}