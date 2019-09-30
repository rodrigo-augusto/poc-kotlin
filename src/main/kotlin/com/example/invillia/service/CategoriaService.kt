package com.example.invillia.service

import com.example.invillia.component.InputValidator
import com.example.invillia.model.CategoriaEntity
import com.example.invillia.model.dto.CategoriaDTO
import com.example.invillia.repository.CategoriaRepository
import com.example.invillia.service.business.Rn0001
import com.example.invillia.service.business.RnG0001
import com.example.invillia.util.IMsg
import com.example.invillia.util.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors

/**
 * @author s2it_ramaral
 * @since 9/12/19 5:34 PM
 * @version : $<br/>
 *          : $
 *
 */
@Service
open class CategoriaService {

    @Autowired
    private val categoriaRepository: CategoriaRepository? = null

    @Autowired
    private val rn0001: Rn0001? = null

    @Autowired
    private val rnG0001: RnG0001? = null

    @Autowired
    private val validator: InputValidator? = null

    fun findAll() =
        categoriaRepository!!
            .findAll()
            .stream()
            .map(getDtoFromEntityFunction())
            .collect(Collectors.toList())

    fun findById(id: Long) =
        categoriaRepository!!
            .findById(id)
            .map(getDtoFromEntityFunction())
            .orElse(null)

    fun save(dto: CategoriaDTO): CategoriaDTO {
        val entity = Optional
            .of(dto)
            .map(getEntityFromDtoFunction())
            .orElse(null)

        rnG0001!!.process(getValidatorToSave(entity))
        rn0001!!.process<Long, CategoriaEntity>(entity.id, Function { id -> categoriaRepository!!.findById(id) })

        return Optional
            .of(categoriaRepository!!.save(entity))
            .map(getDtoFromEntityFunction())
            .orElse(null)
    }

    fun delete(id: Long) {
        categoriaRepository!!.deleteById(id)
    }

    private fun getEntityFromDtoFunction(): Function<CategoriaDTO, CategoriaEntity> {
        return Function { dto -> Mapper.map(dto, CategoriaEntity::class.java) }
    }


    private fun getDtoFromEntityFunction(): Function<CategoriaEntity, CategoriaDTO> {
        return Function { entity -> Mapper.map(entity, CategoriaDTO::class.java) }
    }

    private fun getValidatorToSave(entity: CategoriaEntity) =
        validator!!.check(Supplier { !StringUtils.isEmpty(entity.descricao) }, IMsg.INPUT_DESC_CATEGORIA)

}