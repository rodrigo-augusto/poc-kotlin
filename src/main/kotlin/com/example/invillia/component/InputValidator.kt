package com.example.invillia.component

import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Supplier
import java.util.stream.Collectors

/**
 * @author s2it_ramaral
 * @since 9/20/19 11:58 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
open class InputValidator : MessageSourceAware {

    private val DELIMITER = ", "
    private val DEFAULT_OBJECTS = arrayOf<Any>()

    private val invalidInputs = ArrayList<String>()
    private val mapInputs = HashMap<Supplier<Boolean>, String>()

    private var messageSource: MessageSource? = null

    override fun setMessageSource(messageSource: MessageSource) {
        this.messageSource = messageSource
    }

    fun check(supplier: Supplier<Boolean>, input: String): InputValidator {
        mapInputs[supplier] = input
        return this
    }

    fun getInvalidInputs(): Array<Any> {
        invalidInputs.clear()

        mapInputs.keys.forEach { supplierKey ->
            if (!supplierKey.get()) {
                invalidInputs.add(mapInputs[supplierKey]!!)
            }
        }

        val msg = invalidInputs.stream()
            .map { codeStr ->
                messageSource!!.getMessage(
                    codeStr,
                    null,
                    Locale.getDefault()
                )
            }.collect(Collectors.joining(DELIMITER))

        return if (msg.isEmpty()) DEFAULT_OBJECTS else listOf(msg).toTypedArray()
    }
}