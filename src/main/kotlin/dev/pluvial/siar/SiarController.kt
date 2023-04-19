package dev.pluvial.siar

import dev.pluvial.siar.session.DiscColor
import dev.pluvial.siar.session.Rules
import dev.pluvial.siar.session.GameState
import dev.pluvial.siar.player.Player
import dev.pluvial.siar.player.PlayerType
import dev.pluvial.siar.session.InitGameSessionDto
import dev.pluvial.siar.session.SessionService
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*

private const val SESSION_DOES_NOT_EXIST = "Session does not exist"

@RestController
class SiarController(val sessionService: SessionService) {

    @PostMapping("/{sessionId}/init")
    fun initGameSession(@PathVariable sessionId: UUID,
                        @RequestBody data: InitGameSessionDto) {
        val session = sessionService.getSession(sessionId)
        if(session.isPresent) {
            throw IllegalStateException("Session already exists")
        }
        val rules = Rules(
            gridWidth = data.gridWidth,
            gridHeight = data.gridHeight,
            winningLength = data.winningLength)
        val player1 = Player(data.player1, "Player 1", PlayerType.HUMAN, DiscColor.RED)
        val player2 = Player(data.player2, "Player 2", PlayerType.BOT, DiscColor.YELLOW)
        sessionService.createSession(sessionId, player1, player2, rules)
    }

    @PostMapping("/{sessionId}/column/{column}")
    fun dropToken(@PathVariable sessionId: UUID,
                 @PathVariable column: Int): String {
        val sessionOptional = sessionService.getSession(sessionId)
        if(sessionOptional.isEmpty) {
            throw IllegalStateException(SESSION_DOES_NOT_EXIST)
        }
        val session = sessionOptional.get()
        if(session.gameState != GameState.IN_PROGRESS) {
            throw IllegalStateException("Game is not in progress")
        }
        session.dropToken(column)
        sessionService.updateSession(session)
        return printGrid(session.grid)
    }

    @GetMapping("/{sessionId}")
    fun getSession(@PathVariable sessionId: UUID): String {
        val sessionOptional = sessionService.getSession(sessionId)
        if(sessionOptional.isEmpty) {
            throw IllegalStateException(SESSION_DOES_NOT_EXIST)
        }
        val session = sessionOptional.get()
        return printGrid(session.grid)
    }

    @GetMapping("/{sessionid}/{timestamp}")
    fun getSessionStateAtTimestampe(@PathVariable sessionid: UUID): String {
        TODO("Not yet implemented")
    }

    @DeleteMapping("/{sessionid}")
    fun deleteSessions(@PathVariable sessionid: UUID) {
        sessionService.deleteSession(sessionid)
    }

}