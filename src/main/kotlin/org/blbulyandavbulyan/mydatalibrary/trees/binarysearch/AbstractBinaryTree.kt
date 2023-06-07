package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode

interface AbstractBinaryTree<K : Comparable<K>, NT : AbstractKeyNode<K, NT>> : Iterable<NT> {
    fun contains(key: K): Boolean
    fun add(key: K): NT
    fun remove(key: K): NT
    fun find(key: K): NT
}
