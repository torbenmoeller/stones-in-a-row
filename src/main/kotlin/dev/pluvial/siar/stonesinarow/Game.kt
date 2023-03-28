package dev.pluvial.siar.stonesinarow

class Game(
    val colorPlayerOne: Color = Color.RED,
    val colorPlayerTwo: Color = Color.YELLOW,
    var turnOfPlayer: Player = Player.PLAYER_ONE,
    val grid: Grid = Grid()
) {

    fun play(column: Int) {
        grid.dropTokenIntoColumn(column, getPlayerColor())
        nextTurn()
    }

    fun nextTurn() {
        turnOfPlayer = if (turnOfPlayer == Player.PLAYER_ONE) Player.PLAYER_TWO else Player.PLAYER_ONE
    }

    fun getPlayerColor(): Color {
        return if (turnOfPlayer == Player.PLAYER_ONE) colorPlayerOne else colorPlayerTwo
    }

}