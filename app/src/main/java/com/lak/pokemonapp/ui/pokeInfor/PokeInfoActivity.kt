package com.lak.pokemonapp.ui.pokeInfor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lak.pokemonapp.databinding.ActivityPokeinfoBinding


class PokeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPokeinfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokeinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}