package dev.pluvial.siar.session

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface SessionRepository : MongoRepository<Session, UUID>
