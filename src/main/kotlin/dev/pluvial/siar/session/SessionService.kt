package dev.pluvial.siar.session

import dev.pluvial.siar.player.Player
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(val sessionRepository: SessionRepository) {

    /**
     * Gets the game session for the given UUID
     * or creates a new one if it doesn't exist.
     */
    fun getSession(uuid: UUID): Optional<Session> {
        return sessionRepository.findById(uuid)
    }

    fun createSession(uuid: UUID,
                      player1: Player,
                      player2: Player,
                      rules: Rules) {
        val session = Session(
            id = uuid,
            playerOne = player1,
            playerTwo = player2,
            rules = rules)
        val event = SessionInitiatedEvent()
        session.events.add(event)
        sessionRepository.save(session)
    }

    fun updateSession(session: Session) {
        sessionRepository.save(session)
    }

    fun deleteSession(uuid: UUID) {
        sessionRepository.deleteById(uuid)
    }

}
