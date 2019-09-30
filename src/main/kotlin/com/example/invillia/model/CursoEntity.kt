package com.example.invillia.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*

/**
 * @author s2it_ramaral
 * @since 9/23/19 5:05 PM
 * @version : $<br/>
 *          : $
 *
 */
@Entity
@Table(name = "CURSO")
class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CURSO", nullable = false)
    var id: Long? = null

    @Column(name = "NM_ASSUNTO_CURSO", nullable = false)
    var assunto: String? = null

    @Column(name = "DT_INI_CURSO", nullable = false)
    var dataInicio: Calendar? = null

    @Column(name = "DT_FIM_CURSO", nullable = false)
    var dataTermino: Calendar? = null

    @Column(name = "NUM_ALUNO")
    var quantidadeAluno: Int? = null

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ID_CATEGORIA")
    var categoriaEntity: CategoriaEntity? = null

    @CreationTimestamp
    @Column(name = "DAT_CRIACAO", nullable = false)
    var dataCriacao: Calendar? = null

    @UpdateTimestamp
    @Column(name = "DAT_ALTERACAO")
    var dataAtualizacao: Calendar? = null

    override fun toString() =
        "CursoEntity(id=$id, assunto=$assunto, dataInicio=$dataInicio, dataTermino=$dataTermino, quantidadeAluno=$quantidadeAluno, categoriaEntity=$categoriaEntity, dataCriacao=$dataCriacao, dataAtualizacao=$dataAtualizacao)"

}