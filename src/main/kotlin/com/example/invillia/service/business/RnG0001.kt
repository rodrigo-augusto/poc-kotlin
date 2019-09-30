package com.example.invillia.service.business

import com.example.invillia.component.InputValidator
import com.example.invillia.util.IMsg
import org.springframework.stereotype.Component

/**
 * @author s2it_ramaral
 * @since 9/20/19 11:52 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
class RnG0001 {

    fun process(inputValidator: InputValidator) {
        val args = inputValidator.getInvalidInputs()
        if (args.isNotEmpty()) throw BusinessException(IMsg.MSG_REQUIRED_FIELDS, args)
    }

}