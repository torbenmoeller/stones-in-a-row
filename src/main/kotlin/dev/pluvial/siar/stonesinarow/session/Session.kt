package dev.pluvial.siar.stonesinarow.session

import dev.pluvial.siar.stonesinarow.Game
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document("siar-sessions")
data class Session (
    @Id
    var id: UUID,
    var game: Game
)
