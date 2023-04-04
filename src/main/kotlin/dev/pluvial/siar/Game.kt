package dev.pluvial.siar

import dev.pluvial.siar.player.Player
import dev.pluvial.siar.player.PlayerType

class Game(
    val playerOne: Player = Player("Player 1", PlayerType.HUMAN, Color.RED),
    val playerTwo: Player = Player("Player 2", PlayerType.BOT, Color.YELLOW),
    val winLength: Int = 4
) {

    val grid: Grid = Grid()
    private var turnOfPlayer: Player = playerOne
    var gameState: GameState = GameState.IN_PROGRESS
    var winner: Player? = null

    fun play(column: Int) {
        val row = grid.dropTokenIntoColumn(column, turnOfPlayer.color)
        val isGameWon = checkWin(column, row)
        if (isGameWon) {
            println("Game won by ${turnOfPlayer.name}")
            this.gameState = GameState.FINISHED
            this.winner = turnOfPlayer
        }
        val isGameTie = isTie()
        if (isGameTie) {
            println("Game is a tie")
            this.gameState = GameState.FINISHED
        }
        nextTurn()
    }

    private fun checkWin(column: Int, row: Int): Boolean {
        val playerColor = turnOfPlayer.color
        // Check for horizontal win
        val isHorizontalWin = checkHorizontalWin(row, playerColor)
        if (isHorizontalWin) {
            return true
        }
        // Check for vertical win
        val isVerticalWin = checkVerticalWin(column, playerColor)
        if (isVerticalWin) {
            return true
        }
        // Check for diagonal win (down-right)
        val isDownRightWin = checkDiagonalDownRightWin(row, column, playerColor)
        if (isDownRightWin) {
            return true
        }
        // Check for diagonal win (down-left)
        val isDownLeftWin = checkDiagonalDownLeftWin(row, column, playerColor)
        if (isDownLeftWin) {
            return true
        }
        // No winner yet
        return false
    }

    /**
     * Counts the number of tokens of the same color in a row.
     * Returns true if the number of tokens surpasses the win length.
     */
    private fun checkHorizontalWin(row: Int, playerColor: Color): Boolean {
        var count = 0
        for (c in 0 until grid.getWidth()) {
            if (grid.getCell(c, row) == playerColor) {
                count++
                if (count == winLength) {
                    return true
                }
            } else {
                count = 0
            }
        }
        return false
    }

    /**
     * Counts the number of tokens of the same color in a column.
     * Returns true if the number of tokens surpasses the win length.
     */
    private fun checkVerticalWin(column: Int, playerColor: Color): Boolean {
        var count = 0
        for (r in 0 until grid.getHeight()) {
            if (grid.getCell(column, r) == playerColor) {
                count++
                if (count == winLength) {
                    return true
                }
            } else {
                count = 0
            }
        }
        return false
    }

    private fun checkDiagonalDownRightWin(
        row: Int,
        column: Int,
        playerColor: Color
    ): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r > 0 && c > 0) {
            r--
            c--
        }
        while (r < grid.getHeight() && c < grid.getWidth()) {
            if (grid.getCell(c, r) == playerColor) {
                count++
                if (count == winLength) {
                    return true
                }
            } else {
                count = 0
            }
            r++
            c++
        }
        return false
    }

    private fun checkDiagonalDownLeftWin(
        row: Int,
        column: Int,
        playerColor: Color
    ): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r > 0 && c < grid.getWidth() - 1) {
            r--
            c++
        }
        while (r < grid.getHeight() && c >= 0) {
            if (grid.getCell(c, r) == playerColor) {
                count++
                if (count == winLength) {
                    return true
                }
            } else {
                count = 0
            }
            r++
            c--
        }
        return false
    }

    /**
     * Returns true if the grid is full.
     */
    private fun isTie(): Boolean {
        for (column in 0..6) {
            if (!grid.isColumnFull(column)) {
                return false
            }
        }
        return true
    }

    private fun nextTurn() {
        turnOfPlayer = if (turnOfPlayer == playerOne) playerTwo else playerOne
    }

}