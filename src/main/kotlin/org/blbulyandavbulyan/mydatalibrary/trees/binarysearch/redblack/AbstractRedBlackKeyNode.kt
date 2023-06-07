package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode

abstract class AbstractRedBlackKeyNode<K: Comparable<K>, NT: AbstractRedBlackKeyNode<K, NT>>(key: K): AbstractKeyNode<K, NT>(key){
    enum class Color{RED, BLACK}
    var color: Color = Color.RED
        internal set
    /**
     * Перекрашивает текущую ноду в чёрный
     * */
    internal fun makeBlack(){
        color = Color.BLACK
    }
    /**
     * Перекрашивает текущую ноду в красный
     * */
    internal fun makeRed(){
        color = Color.RED
    }
    /**
     * Функция делает свап цветов
     * Ноду над которой вызывается - делает красной
     * А её потомков чёрными
     * */
    internal fun swapColor(){
        parent?.makeRed()
        left?.makeBlack()
        right?.makeBlack()
    }
    internal fun rotateLeft(): NT{
        val b: NT = this as NT
        val d: NT = b.right ?: throw RuntimeException("Нет правого ребёнка для левого поворота")
        b.parent?.replaceChild(b, d) ?: {d.parent = null}
        b.right = d.left
        b.right?.parent = b
        d.left = b
        b.parent = d
        return d
    }
    internal fun rotateRight(): NT{
        val d: NT = this as NT
        val b: NT = d.left ?: throw RuntimeException("Нет левого ребёнка для правого поворота!")
        d.parent?.replaceChild(d, b) ?: {b.parent = null}
        d.left = b.right
        d.left?.parent = d
        b.right = d
        d.parent = b
        return b
    }
    internal fun replaceChild(replacingChild: NT, newChild: NT){
        if(replacingChild === left) left = newChild
        else if(replacingChild === right) right = newChild
        else throw IllegalStateException("Невозможно заменить ребёнка, т.к. replacingChild не является ребёнком данной ноды")
        newChild.parent = this as NT
    }
    /**
     * Функция проверяет на красноту обеих детей
     * @return True - если оба ребёнка красные
     * */
    fun areBothChildrenRed() =  left?.isRed() == true && right?.isRed() == true
    /**
     * Функция проверяет текущую ноду на красноту
     * @return True - если красная, иначе False
     * */
    fun isRed(): Boolean = color == Color.RED
    /**
     * Функция проверяет текущую ноду на черноту
     * @return True - если чёрная, иначе False
     * */
    fun isBlack(): Boolean = color == Color.BLACK
    /**
     * Функция проверяет текущую ноду на то, левая ли она у своего родителя
     * @return True - если да, False - если нет и если нет родителя у текущей ноды
     * */
    fun isLeft(): Boolean = parent?.left === this
    /**
     * Функция проверяет текущую ноду на то, правая ли она у своего родителя
     * @return True - если да, False - если нет и если нет родителя у текущей ноды
     * */
    fun isRight(): Boolean = parent?.right === this

}
