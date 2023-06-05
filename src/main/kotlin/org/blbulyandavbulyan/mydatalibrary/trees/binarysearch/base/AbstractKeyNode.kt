package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base

abstract class AbstractKeyNode<K: Comparable<K>, NT: AbstractKeyNode<K, NT>>(val key: K) {
    var parent: NT? = null
        internal set
    var left: NT? = null
        internal set
    var right: NT? = null
        internal set
    fun hasChild(): Boolean = hasLeft() || hasRight()
    fun hasParent(): Boolean = parent != null
    fun hasLeft(): Boolean = left != null
    fun hasRight(): Boolean = right != null
}