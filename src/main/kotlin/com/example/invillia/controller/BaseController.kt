package com.example.invillia.controller

import com.example.invillia.service.business.BusinessException
import com.example.invillia.util.IMsg
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.*
import java.util.function.Supplier

/**
 * @author s2it_ramaral
 * @since 9/12/19 11:53 AM
 * @version : $<br/>
 *          : $
 *
 */
open class BaseController : MessageSourceAware {

    private var messageSource: MessageSource? = null

    override fun setMessageSource(messageSource: MessageSource) {
        this.messageSource = messageSource
    }

    internal fun <T> processNoContent(element: T?): ResponseEntity<*> {
        return if (element != null) ResponseEntity.ok(element) else ResponseEntity.noContent().build<Any>()
    }

    internal fun <T> processBusinessWithReturn(supplier: Supplier<T>): ResponseEntity<*> {
        return processBusiness<T>(supplier, java.lang.Boolean.TRUE)
    }

    internal fun <T> processBusinessWithoutReturn(supplier: Supplier<T>): ResponseEntity<*> {
        return processBusiness<T>(supplier, java.lang.Boolean.FALSE)
    }

    private fun <T> processBusiness(supplier: Supplier<T>, isReturnElement: Boolean): ResponseEntity<*> {
        return try {
            val ret = supplier.get()
            if (isReturnElement) ResponseEntity.ok(ret) else ResponseEntity.ok().build<Any>()
        } catch (ex: BusinessException) {
            processBusinessException(ex)
        } catch (ex: Exception) {
            getResponseGenericError(ex)
        }
    }

    internal fun processBusinessVoid(runnable: Runnable): ResponseEntity<*> {
        return try {
            runnable.run()
            ResponseEntity.ok().build<Any>()
        } catch (ex: BusinessException) {
            processBusinessException(ex)
        } catch (ex: Exception) {
            getResponseGenericError(ex)
        }

    }

    private fun processBusinessException(exception: BusinessException): ResponseEntity<*> {
        val msg = messageSource?.getMessage(
            processGetMessage(exception)
            , exception.args
            , null
            , Locale.getDefault()
        )
        return if (msg != null)
            ResponseEntity.status(HttpStatus.CONFLICT).body(msg) else getResponseGenericError(exception)
    }

    private fun processGetMessage(exception: Throwable): String {
        return Optional.ofNullable(exception.cause)
            .map<String>(Throwable::message)
            .orElse(exception.message)
    }

    private fun getResponseGenericError(exception: Exception): ResponseEntity<*> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                messageSource?.getMessage(
                    IMsg.MSG_GENERIC_ERROR,
                    listOf(Optional.ofNullable(exception.message).orElse(exception.toString())).toTypedArray(),
                    Locale.getDefault()
                )
            )
    }
}