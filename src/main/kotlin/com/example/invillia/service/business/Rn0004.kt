package com.example.invillia.service.business

import com.example.invillia.model.CursoEntity
import com.example.invillia.repository.CursoRepository
import com.example.invillia.util.IMsg
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author s2it_ramaral
 * @since 9/27/19 11:29 AM
 * @version : $<br/>
 *          : $
 *
 */
@Component
class Rn0004 {

    @Autowired
    private val cursoRepository: CursoRepository? = null

    fun process(entity: CursoEntity) {
        val conflitos = cursoRepository!!.findConflicts(
            entity.dataInicio!!,
            entity.dataTermino!!
        )

        val isSameEntity = conflitos
            .stream()
            .allMatch { e -> e.id == entity.id && conflitos.size == 1 }

        if (!isSameEntity && !conflitos.isEmpty()) {
            throw BusinessException(IMsg.MSG_CONFLIC_PERIOD)
        }
    }
}