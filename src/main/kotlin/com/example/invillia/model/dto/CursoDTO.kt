package com.example.invillia.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

/**
 * @author s2it_ramaral
 * @since 9/23/19 5:06 PM
 * @version : $<br/>
 *          : $
 *
 */
class CursoDTO {

    var id: Long? = null

    var assunto: String? = null

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataInicio: Calendar? = null

    @JsonFormat(pattern = "dd/MM/yyyy")
    var dataTermino: Calendar? = null

    var quantidadeAluno: Int? = null

    var categoria: CategoriaDTO? = null

    override fun toString() =
        "CursoDTO(id=$id, assunto=$assunto, dataInicio=$dataInicio, dataTermino=$dataTermino, quantidadeAluno=$quantidadeAluno, categoria=$categoria)"

}