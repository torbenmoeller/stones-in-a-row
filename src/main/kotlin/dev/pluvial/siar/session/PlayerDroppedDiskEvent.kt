package dev.pluvial.siar.session

import dev.pluvial.siar.player.Player

data class PlayerDroppedDiskEvent  (
    val player: Player,
    val column: Int
): Event() {
    override fun process(session: Session) {
        session.grid.dropTokenIntoColumn(column, player.discColor)
    }
}