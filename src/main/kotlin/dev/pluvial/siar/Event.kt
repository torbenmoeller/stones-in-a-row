package dev.pluvial.siar

import java.time.Instant

abstract class Event (
    var timestamp: Instant = Instant.now()
){
    abstract fun process()
}