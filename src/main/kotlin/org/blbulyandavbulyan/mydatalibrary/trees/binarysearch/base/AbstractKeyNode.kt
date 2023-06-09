package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base

abstract class AbstractKeyNode<K: Comparable<K>, NT: AbstractKeyNode<K, NT>>(val key: K) {
    var parent: NT? = null
        internal set
    var left: NT? = null
        internal set(value){
            if(value == null || value.key < key){
                field = value
                value?.parent = this as NT
            }
            else throw IllegalArgumentException("Нода не может быть добавлена к данной в качестве левой, т.к. добавляемая нода не null и её ключ должен быть меньше чем ключ в данной!")
        }
    var right: NT? = null
        internal set(value){
            if(value == null || value.key > key){
                field = value
                value?.parent = this as NT
            }
            else throw IllegalArgumentException("Нода не может быть добавлена к данной в качестве правой, т.к. добавляемая нода не null и её ключ должен быть больше чем ключ в данной!")
        }
    /**
     * Проверяет, есть ли у данной ноды хотя бы один ребёнок
     * @return true если есть, иначе false
     * */
    fun hasChild(): Boolean = hasLeft() || hasRight()
    /**
     * Проверяет, есть ли у данной ноды родитель
     * @return true если есть, иначе false
     * */
    fun hasParent(): Boolean = parent != null
    /**
     * Проверяет, имеет ли текущая нода левого ребёнка
     * @return true если да, иначе false
     * */
    fun hasLeft(): Boolean = left != null
    /**
     * Проверяет, имеет ли текущая нода правого ребёнка
     * @return true если да, иначе false
     * */
    fun hasRight(): Boolean = right != null
    /**
     * Заменяет соответствующего ребёнка в данной ноде, на нового
     * @return старого ребёнка
     * @throws IllegalArgumentException если этот ребёнок не принадлежит данной ноде
     * */
    internal fun replaceChild(replacingChild: NT, newChild: NT?): NT{
        if(replacingChild === left) left = newChild
        else if(replacingChild === right) right = newChild
        else throw IllegalArgumentException("Невозможно заменить ребёнка, т.к. replacingChild не является ребёнком данной ноды")
        return replacingChild
    }
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