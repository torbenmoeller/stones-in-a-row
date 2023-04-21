package dev.pluvial.siar.session

import dev.pluvial.siar.player.Player
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("siar-sessions")
class Session (
    @Id
    val id: UUID = UUID.randomUUID(),
    val playerOne: Player,
    val playerTwo: Player,
    val rules: Rules,
) {
    var grid: Grid = Grid(width = rules.gridWidth, height = rules.gridHeight)
    var winLength: Int = rules.winningLength
    var turnOfPlayer: Player = playerOne
    var gameState: GameState = GameState.IN_PROGRESS
    var gameResult: GameResultEnum? = null
    var events: MutableList<Event> = ArrayList()

    fun dropToken(column: Int) {
        val row = grid.dropTokenIntoColumn(column, turnOfPlayer.discColor)
        events.add(PlayerDroppedDiskEvent(turnOfPlayer, column))
        val isGameWon = checkWin(column, row)
        if (isGameWon) {
            println("Game won by ${turnOfPlayer.name}")
            this.gameState = GameState.FINISHED
            if(playerOne == turnOfPlayer)
                this.gameResult = GameResultEnum.PLAYER1_WON
            else
                this.gameResult = GameResultEnum.PLAYER2_WON
            events.add(GameFinishedEvent(gameResult!!))
        }
        val isGameTie = isTie()
        if (isGameTie) {
            println("Game is a draw")
            this.gameState = GameState.FINISHED
            this.gameResult = GameResultEnum.DRAW
            events.add(GameFinishedEvent(gameResult!!))
        }
        nextTurn()
    }

    private fun checkWin(column: Int, row: Int): Boolean {
        val playerColor = turnOfPlayer.discColor
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
    private fun checkHorizontalWin(row: Int, playerDiscColor: DiscColor): Boolean {
        var count = 0
        for (c in 0 until grid.getWidth()) {
            if (grid.getCell(c, row) == playerDiscColor) {
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
    private fun checkVerticalWin(column: Int, playerDiscColor: DiscColor): Boolean {
        var count = 0
        for (r in 0 until grid.getHeight()) {
            if (grid.getCell(column, r) == playerDiscColor) {
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
        playerDiscColor: DiscColor
    ): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r > 0 && c > 0) {
            r--
            c--
        }
        while (r < grid.getHeight() && c < grid.getWidth()) {
            if (grid.getCell(c, r) == playerDiscColor) {
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
        playerDiscColor: DiscColor
    ): Boolean {
        var count = 0
        var r = row
        var c = column
        while (r > 0 && c < grid.getWidth() - 1) {
            r--
            c++
        }
        while (r < grid.getHeight() && c >= 0) {
            if (grid.getCell(c, r) == playerDiscColor) {
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
