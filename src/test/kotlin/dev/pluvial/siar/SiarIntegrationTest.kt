package dev.pluvial.siar

import io.restassured.RestAssured
import org.junit.jupiter.api.Test
import io.restassured.RestAssured.*
import io.restassured.config.JsonConfig.jsonConfig
import io.restassured.matcher.RestAssuredMatchers.*
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.assertj.core.api.InstanceOfAssertFactories.BIG_DECIMAL
import org.hamcrest.Matchers.*
import java.util.UUID

class SiarIntegrationTest {

    @Test
    fun `test something`() {
        val message: String =
            Given {
                port(8888)
//                header("Header", "Header")
//                body("hello")
            } When {
                val uuid = UUID.randomUUID()
                put("/" + uuid + "/init")
            } Then {
                statusCode(200)
                body("message", equalTo("Another World"))
            } Extract {
                path("message")
            }
    }

}
