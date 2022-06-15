package com.lak.pokemonapp.model.remote.apiresponse

import com.google.gson.annotations.SerializedName

data class Pokemon (
    @SerializedName("abilities") val abilities: List<Ability>,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("sprites") val sprites: Sprites
    )

data class Sprites (
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("front_shiny") val frontShiny: String?
)

data class Ability(
    @SerializedName("ability")val ability: AbilityX,
    @SerializedName("is_hidden") val is_hidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class AbilityX(
    @SerializedName("ability_name") val name: String,
    @SerializedName("ability_url") val url: String
)