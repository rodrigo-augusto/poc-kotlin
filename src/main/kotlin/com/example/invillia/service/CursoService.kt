package com.example.invillia.service

import com.example.invillia.component.InputValidator
import com.example.invillia.model.CategoriaEntity
import com.example.invillia.model.CursoEntity
import com.example.invillia.model.dto.CategoriaDTO
import com.example.invillia.model.dto.CursoDTO
import com.example.invillia.repository.CursoRepository
import com.example.invillia.service.business.*
import com.example.invillia.util.IMsg
import com.example.invillia.util.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.util.*
import java.util.function.Consumer
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collectors

/**
 * @author s2it_ramaral
 * @since 9/27/19 11:45 AM
 * @version : $<br/>
 *          : $
 *
 */
@Service
class CursoService {

    @Autowired
    private val cursoRepository: CursoRepository? = null

    @Autowired
    private val rn0001: Rn0001? = null

    @Autowired
    private val rn0002: Rn0002? = null

    @Autowired
    private val rn0003: Rn0003? = null

    @Autowired
    private val rn0004: Rn0004? = null

    @Autowired
    private val rnG0001: RnG0001? = null

    @Autowired
    private val validator: InputValidator? = null

    fun findAll() =
        cursoRepository!!
            .findAll()
            .stream()
            .map(getDtoFromEntityFunction())
            .collect(Collectors.toList())

    fun findById(id: Long?) =
        cursoRepository!!
            .findById(id)
            .map(getDtoFromEntityFunction())
            .orElse(null)

    fun findByAssunto(assunto: String) =
        cursoRepository!!
            .findByAssuntoContaining(assunto)
            .stream()
            .map(getDtoFromEntityFunction())
            .collect(Collectors.toList())

    fun save(dto: CursoDTO): CursoDTO {
        val entity = Optional
            .of(dto)
            .map(getEntityFromDtoFunction())
            .orElse(null)

        rnG0001!!.process(getValidatorToSave(entity))
        rn0001!!.process<Long, CursoEntity>(entity.id, Function { id -> cursoRepository!!.findById(id) })
        rn0002!!.process(entity.dataInicio!!)
        rn0003!!.process(entity.dataInicio!!, entity.dataTermino!!)
        rn0004!!.process(entity)

        return Optional.of(cursoRepository!!.save(entity))
            .map(getDtoFromEntityFunction())
            .orElse(null)
    }

    fun delete(id: Long?) {
        cursoRepository!!.deleteById(id)
    }

    private fun getEntityFromDtoFunction(): Function<CursoDTO, CursoEntity> {
        return Function { dto ->
            Mapper.map(
                dto,
                CursoEntity::class.java,
                Consumer { cursoEntity ->
                    cursoEntity.categoriaEntity = Mapper.map(dto.categoria, CategoriaEntity::class.java)
                })
        }
    }

    private fun getDtoFromEntityFunction(): Function<CursoEntity, CursoDTO> {
        return Function { entity ->
            Mapper.map(
                entity,
                CursoDTO::class.java,
                Consumer { dto -> dto.categoria = Mapper.map(entity.categoriaEntity, CategoriaDTO::class.java) })
        }
    }

    private fun getValidatorToSave(entity: CursoEntity) =
        validator!!
            .check(Supplier { !StringUtils.isEmpty(entity.assunto) }, IMsg.INPUT_ASSUNTO_CURSO)
            .check(Supplier { entity.dataInicio != null }, IMsg.INPUT_DT_INI_CURSO)
            .check(Supplier { entity.dataTermino != null }, IMsg.INPUT_DT_FIM_CURSO)
            .check(
                Supplier { entity.categoriaEntity != null && entity.categoriaEntity!!.id != null },
                IMsg.INPUT_CATEGORIA_CURSO
            )

}