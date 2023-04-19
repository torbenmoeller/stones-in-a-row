package dev.pluvial.siar.player

import dev.pluvial.siar.session.DiscColor
import java.util.*

data class Player (
    val id: UUID,
    val name: String,
    val type: PlayerType,
    val discColor: DiscColor,
)