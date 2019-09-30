package com.example.invillia.controller

import com.example.invillia.model.dto.CursoDTO
import com.example.invillia.service.CursoService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.function.Supplier
import javax.validation.Valid

/**
 * @author s2it_ramaral
 * @since 9/27/19 1:34 PM
 * @version : $<br/>
 *          : $
 *
 */
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Controller
@RequestMapping("/curso")
@Api(value = "Curso")
class CursoController : BaseController() {

    @Autowired
    private val cursoService: CursoService? = null

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Recupera todos os recursos")
    fun findAll() = cursoService!!.findAll()

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Recupera um recurso por ID")
    fun findById(@PathVariable(value = "id") id: Long?) =
        processNoContent(cursoService!!.findById(id))

    @GetMapping("/assunto/{assunto}")
    @ResponseBody
    @ApiOperation(value = "Recupera recursos com aplicacao de filtros")
    fun findByAssunto(@PathVariable(value = "assunto") assunto: String) =
        cursoService!!.findByAssunto(assunto)

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Insere um recurso")
    fun create(@Valid @RequestBody dto: CursoDTO) =
        processBusinessWithReturn(Supplier { cursoService!!.save(dto) })

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recursos")
    fun update(@Valid @RequestBody dto: CursoDTO) =
        processBusinessWithoutReturn(Supplier { cursoService!!.save(dto) })

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    fun delete(@PathVariable(value = "id") id: Long?) =
        processBusinessVoid(Runnable { cursoService!!.delete(id) })

}