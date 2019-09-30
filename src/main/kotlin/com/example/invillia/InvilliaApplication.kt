package com.example.invillia

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class InvilliaApplication

fun main(args: Array<String>) {
    runApplication<InvilliaApplication>(*args)
}
