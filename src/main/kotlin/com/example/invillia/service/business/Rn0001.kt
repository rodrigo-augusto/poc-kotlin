package com.example.invillia.service.business

import com.example.invillia.util.IMsg
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Function

/**
 * @author s2it_ramaral
 * @since 9/20/19 11:53 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
class Rn0001 {

    fun <ID, E> process(id: ID?, function: Function<ID, Optional<E>>) {
        Optional.ofNullable(id)
            .map(function)
            .ifPresent { optE ->
                if (optE == Optional.empty<Any>()) {
                    throw BusinessException(IMsg.MSG_INVALID_UPDATE)
                }
            }
    }

}