package dev.pluvial.siar.session

import java.util.*

data class InitGameSessionDto(val player1: UUID,
                              val player2: UUID,
                              val gridWidth: Int,
                              val gridHeight: Int,
                              val winningLength: Int)