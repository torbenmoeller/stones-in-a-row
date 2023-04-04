package dev.pluvial.siar.session

import dev.pluvial.siar.Game
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(val sessionRepository: SessionRepository) {

    /**
     * Gets the game session for the given UUID
     * or creates a new one if it doesn't exist.
     */
    fun getSession(uuid: UUID): Game {
        val session = sessionRepository.findById(uuid)
        return session.map { x -> x.game }
            .orElse(Game())
    }

    fun updateSession(uuid: UUID, game: Game) {
        sessionRepository.save(Session(uuid, game))
    }


}
