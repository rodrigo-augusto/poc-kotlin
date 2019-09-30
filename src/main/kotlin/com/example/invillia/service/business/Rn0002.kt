package com.example.invillia.service.business

import com.example.invillia.util.IMsg
import org.springframework.stereotype.Component
import java.util.*

/**
 * @author s2it_ramaral
 * @since 9/27/19 11:23 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
class Rn0002 {

    fun process(data: Calendar) {
        if (data.before(Calendar.getInstance())) {
            throw BusinessException(IMsg.MSG_INVALID_INIT_DATE)
        }
    }
}