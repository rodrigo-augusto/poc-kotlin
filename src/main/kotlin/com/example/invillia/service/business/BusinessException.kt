package com.example.invillia.service.business

/**
 * @author s2it_ramaral
 * @since 9/12/19 4:58 PM
 * @version : $<br/>
 *          : $
 *
 */
class BusinessException(message: String?) : RuntimeException(message) {

    var args: Array<Any>? = null

    constructor(message: String?, args: Array<Any>) : this(message) {
        this.args = args
    }

}