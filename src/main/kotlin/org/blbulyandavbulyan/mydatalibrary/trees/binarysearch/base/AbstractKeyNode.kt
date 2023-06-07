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
    fun hasChild(): Boolean = hasLeft() || hasRight()
    fun hasParent(): Boolean = parent != null
    fun hasLeft(): Boolean = left != null
    fun hasRight(): Boolean = right != null
    internal fun replaceChild(replacingChild: NT, newChild: NT?): NT{
        if(replacingChild === left) left = newChild
        else if(replacingChild === right) right = newChild
        else throw IllegalArgumentException("Невозможно заменить ребёнка, т.к. replacingChild не является ребёнком данной ноды")
//        newChild.parent = this as NT
        return replacingChild
    }
}