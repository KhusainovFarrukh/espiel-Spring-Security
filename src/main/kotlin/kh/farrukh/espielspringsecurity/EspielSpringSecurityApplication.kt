package kh.farrukh.espielspringsecurity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class EspielSpringSecurityApplication

fun main(args: Array<String>) {
    runApplication<EspielSpringSecurityApplication>(*args)
}
