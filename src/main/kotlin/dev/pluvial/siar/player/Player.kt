package dev.pluvial.siar.player

import dev.pluvial.siar.DiscColor

data class Player (
    val name: String,
    val type: PlayerType,
    val discColor: DiscColor
)