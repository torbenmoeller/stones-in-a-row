package dev.pluvial.siar.session

class SessionInitiatedEvent : Event() {
    override fun process(session: Session) {
        session.gameState = GameState.IN_PROGRESS
    }
}