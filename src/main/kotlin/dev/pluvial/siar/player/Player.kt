package dev.pluvial.siar.player

import dev.pluvial.siar.Color

data class Player (
    val name: String,
    val type: PlayerType,
    val color: Color
)