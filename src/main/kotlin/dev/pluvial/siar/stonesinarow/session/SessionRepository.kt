package dev.pluvial.siar.stonesinarow.session

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SessionRepository : MongoRepository<Session, UUID>{
}
