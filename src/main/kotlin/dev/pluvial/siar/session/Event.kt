package dev.pluvial.siar.session

import java.time.Instant
import java.util.*

abstract class Event (
    var id: String = UUID.randomUUID().toString(),
    var timestamp: Instant = Instant.now()
){
    abstract fun process(session: Session)
}