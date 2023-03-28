package dev.pluvial.siar.stonesinarow

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GameController {

    @PostMapping("/{uuid}")
    fun makeMove(@PathVariable uuid: String): {

    }

}