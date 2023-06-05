package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode

abstract class AbstractRedBlackKeyNode<K: Comparable<K>, NT: AbstractRedBlackKeyNode<K, NT>>(key: K): AbstractKeyNode<K, NT>(key){
    enum class Color{RED, BLACK}
    var color: Color = Color.RED
        internal set
    /**
     * Функция делает свап цветов
     * Ноду над которой вызывается - делает красной
     * А её потомков чёрными
     * */
    internal fun swapColor(){
        parent?.color = Color.RED
        left?.color = Color.BLACK
        right?.color = Color.BLACK
    }
    internal fun rotateLeft(){

    }
    internal fun rotateRight(){

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
