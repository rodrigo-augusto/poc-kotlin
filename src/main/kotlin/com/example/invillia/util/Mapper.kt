package com.example.invillia.util

import org.springframework.beans.BeanUtils
import java.util.function.Consumer

/**
 * @author s2it_ramaral
 * @since 9/13/19 9:39 AM
 * @version : $<br/>
 *          : $
 *
 */
object Mapper {

    fun <S, T> map(source: S, classTarget: Class<T>): T {
        val target = createInstanceOf(classTarget)
        BeanUtils.copyProperties(source, target!!)
        return target
    }

    fun <S, T> map(source: S, classTarget: Class<T>, postMap: Consumer<T>): T {
        val target = map(source, classTarget)
        postMap.accept(target)
        return target
    }

    private fun <T> createInstanceOf(clazz: Class<T>): T? {
        return try {
            clazz.newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}