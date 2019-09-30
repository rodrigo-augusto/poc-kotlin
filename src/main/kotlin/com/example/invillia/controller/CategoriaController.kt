package com.example.invillia.controller

import com.example.invillia.model.dto.CategoriaDTO
import com.example.invillia.service.CategoriaService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.function.Supplier
import javax.validation.Valid

/**
 * @author s2it_ramaral
 * @since 9/11/19 2:15 PM
 * @version : $<br/>
 *          : $
 *
 */
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@RestController
@RequestMapping("/categoria")
@Api(value = "Categoria")
open class CategoriaController : BaseController() {

    @Autowired
    private val categoriaService: CategoriaService? = null

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "Recupera todos os recursos")
    fun findAll() = categoriaService!!.findAll()

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Recupera um recurso por ID")
    fun findById(@PathVariable(value = "id") id: Long) =
        processNoContent(categoriaService!!.findById(id))

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "Insere um recurso")
    fun create(@Valid @RequestBody dto: CategoriaDTO) =
        processBusinessWithReturn(Supplier { categoriaService!!.save(dto) })

    @PatchMapping
    @ResponseBody
    @ApiOperation(value = "Atualiza um recurso")
    fun update(@Valid @RequestBody dto: CategoriaDTO) =
        processBusinessWithoutReturn(Supplier { categoriaService!!.save(dto) })

    @DeleteMapping("/{id}")
    @ResponseBody
    @ApiOperation(value = "Deleta um recurso")
    fun delete(@PathVariable(value = "id") id: Long) =
        processBusinessVoid(Runnable { categoriaService!!.delete(id) })

}