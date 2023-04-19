package dev.pluvial.siar

import dev.pluvial.siar.session.DiscColor
import dev.pluvial.siar.session.Rules
import dev.pluvial.siar.session.GameState
import dev.pluvial.siar.player.Player
import dev.pluvial.siar.player.PlayerType
import dev.pluvial.siar.session.InitGameSessionDto
import dev.pluvial.siar.session.SessionService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class SiarController(val sessionService: SessionService) {

    @PostMapping("/{sessionid}/init")
    fun initGameSession(@PathVariable sessionid: UUID, @RequestBody data: InitGameSessionDto) {
        val session = sessionService.getSession(sessionid)
        if(session.isPresent) {
            throw IllegalStateException("Session already exists")
        }
        val rules = Rules(
            gridWidth = data.gridWidth,
            gridHeight = data.gridHeight,
            winningLength = data.winningLength)
        val player1 = Player(data.player1, "Player 1", PlayerType.HUMAN, DiscColor.RED)
        val player2 = Player(data.player2, "Player 2", PlayerType.BOT, DiscColor.YELLOW)
        sessionService.createSession(sessionid, player1, player2, rules)
    }

    @PostMapping("/{sessionid}/column/{column}")
    fun dropToken(@PathVariable sessionid: UUID,
                 @PathVariable column: Int): String {
        val sessionOptional = sessionService.getSession(sessionid)
        if(sessionOptional.isEmpty) {
            throw IllegalStateException("Session does not exist")
        }
        val session = sessionOptional.get()
        if(session.gameState != GameState.IN_PROGRESS) {
            throw IllegalStateException("Game is not in progress")
        }
        session.dropToken(column)
        sessionService.updateSession(session)
        return printGrid(session.grid)
    }

    @GetMapping("/{sessionid}")
    fun getSession(@PathVariable sessionid: UUID): String {
        val sessionOptional = sessionService.getSession(sessionid)
        if(sessionOptional.isEmpty) {
            throw IllegalStateException("Session does not exist")
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