package dev.pluvial.siar.session

import dev.pluvial.siar.Game
import org.springframework.stereotype.Service
import java.util.*

@Service
class SessionService(val sessionRepository: SessionRepository) {

    fun getSession(uuid: UUID): Game {
        val session = sessionRepository.findById(uuid)
        return if(session.isPresent) {
            session.get().game
        } else {
            Game()
        }
    }

    fun updateSession(uuid: UUID, game: Game) {
        sessionRepository.save(Session(uuid, game))
    }


}
