package com.lak.pokemonapp.ui.pokelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lak.pokemonapp.model.remote.apiresponse.PokeApiResponse
import com.lak.pokemonapp.model.remote.apiresponse.PokeResult
import com.lak.pokemonapp.repo.PokemonRepoImpl
import com.lak.pokemonapp.utils.PokemonListState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repo: PokemonRepoImpl
) : ViewModel() {

    private val _state = MutableLiveData<PokemonListState>()
    val state: LiveData<PokemonListState> = _state

    fun fetchPokemonList() {
            repo.getPokeListFromApi(10,10)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    emitPokemonList(it)
                },{
                    emitError(it)
                })
    }

    private fun emitError(it: Throwable) {
        _state.value = PokemonListState.Error
    }

    private fun emitPokemonList(it: PokeApiResponse) {
        _state.value = PokemonListState.PokemonList(it.results)
    }

}