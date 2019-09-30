package com.example.invillia.model

import javax.persistence.*

/**
 * @author s2it_ramaral
 * @since 9/12/19 5:19 PM
 * @version : $<br/>
 *          : $
 *
 */
@Entity
@Table(name = "CATEGORIA")
class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CATEGORIA", nullable = false)
    var id: Long? = null

    @Column(name = "NM_CATEGORIA", nullable = false)
    var descricao: String? = null

    override fun toString() = "CategoriaEntity(id=$id, descricao=$descricao)"

}