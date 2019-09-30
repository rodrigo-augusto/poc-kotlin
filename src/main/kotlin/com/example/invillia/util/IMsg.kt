package com.example.invillia.util

/**
 * @author s2it_ramaral
 * @since 9/12/19 12:00 PM
 * @version : $<br/>
 *          : $
 *
 */
interface IMsg {

    companion object {

        /*
        *  MENSAGENS DE NEGOCIO
        * */
        const val MSG_GENERIC_ERROR = "msg.0001"
        const val MSG_INVALID_UPDATE = "msg.0002"
        const val MSG_INVALID_INIT_DATE = "msg.0003"
        const val MSG_INVALID_PERIOD = "msg.0004"
        const val MSG_CONFLIC_PERIOD = "msg.0005"
        const val MSG_REQUIRED_FIELDS = "msg.0006"


        /*
        *  CAMPOS NA TELA
        * */
        const val INPUT_DESC_CATEGORIA = "input.0001"
        const val INPUT_ASSUNTO_CURSO = "input.0002"
        const val INPUT_DT_INI_CURSO = "input.0003"
        const val INPUT_DT_FIM_CURSO = "input.0004"
        const val INPUT_CATEGORIA_CURSO = "input.0005"
    }
}