package com.example.invillia.repository

import com.example.invillia.model.CursoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

/**
 * @author s2it_ramaral
 * @since 9/27/19 10:36 AM
 * @version : $<br/>
 *          : $
 *
 */
interface CursoRepository : JpaRepository<CursoEntity, Long> {

    abstract fun findByAssuntoContaining(assunto: String): List<CursoEntity>

    @Query(
        value = "select * " +
                "  from CURSO c " +
                " where ( c.DT_INI_CURSO Between :dataInicio and :dataTermino )    " +
                "    or ( c.DT_FIM_CURSO Between :dataInicio and :dataTermino )    " +
                "    or ( :dataInicio Between c.DT_INI_CURSO and c.DT_FIM_CURSO )  " +
                "    or ( :dataTermino Between c.DT_INI_CURSO and c.DT_FIM_CURSO ) ", nativeQuery = true
    )
    abstract fun findConflicts(
        @Param("dataInicio") dataInicio: Calendar,
        @Param("dataTermino") dataTermino: Calendar
    ): List<CursoEntity>

}