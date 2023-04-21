package dev.pluvial.siar.session

class GameFinishedEvent(var gameResult: GameResultEnum)
    : Event() {
    override fun process(session: Session) {
        session.gameState = GameState.FINISHED
        session.gameResult = gameResult
    }
}