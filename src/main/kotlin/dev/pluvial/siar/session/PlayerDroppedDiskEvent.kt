package dev.pluvial.siar.session

import dev.pluvial.siar.Event
import dev.pluvial.siar.player.Player

data class PlayerDroppedDiskEvent  (
    val player: Player,
    val column: Int
): Event() {
    override fun process() {
        TODO("Not yet implemented")
    }
}