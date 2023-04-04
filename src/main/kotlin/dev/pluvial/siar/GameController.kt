package dev.pluvial.siar

import dev.pluvial.siar.session.SessionService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class GameController(val sessionService: SessionService) {

    @PostMapping("/{uuid}/{column}")
    fun makeMove(@PathVariable uuid: UUID,
                 @PathVariable column: Int): String {
        val game = findOrCreateGame(uuid)
        if(game.gameState != GameState.IN_PROGRESS) {
            return "Game is over. Winner:" + game.winner?.name
        }
        game.play(column)
        updateSession(uuid, game)
        return printGrid(game.grid)
    }

    private fun findOrCreateGame(uuid: UUID): Game {
        return sessionService.getSession(uuid)
    }

    private fun updateSession(uuid: UUID, game:Game ) {
        sessionService.updateSession(uuid, game)
    }

}