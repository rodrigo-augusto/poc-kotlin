package com.example.invillia.service.business

import com.example.invillia.util.IMsg
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author s2it_ramaral
 * @since 9/27/19 11:26 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
class Rn0003 {

    fun process(dataInicial: Calendar, dataFinal: Calendar) {
        if (dataInicial.after(dataFinal)) {
            throw BusinessException(IMsg.MSG_INVALID_PERIOD)
        }
    }
}