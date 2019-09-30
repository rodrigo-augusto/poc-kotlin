package com.example.invillia.model.dto

/**
 * @author s2it_ramaral
 * @since 9/11/19 2:24 PM
 * @version : $<br/>
 *          : $
 *
 */
open class CategoriaDTO {

    var id: Long? = null
    var descricao: String? = null

    override fun toString() = "CategoriaDTO(id=$id, descricao='$descricao')"

}