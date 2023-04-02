package dev.pluvial.siar.stonesinarow

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    val games : HashMap<String, Game> = HashMap()

    @PostMapping("/{uuid}/{column}")
    fun makeMove(@PathVariable uuid: String,
                 @PathVariable column: Int): String {
        val game = findOrCrateGame(uuid)
        if(game.gameState != GameState.IN_PROGRESS) {
            return "Game is over. Winner:" + game.winner?.name
        }
        game.play(column)
        return printGrid(game.grid)
    }

    private fun findOrCrateGame(uuid: String): Game {
        if (games[uuid] == null) {
            games[uuid] = Game()
        }
        return games.getValue(uuid)
    }

}