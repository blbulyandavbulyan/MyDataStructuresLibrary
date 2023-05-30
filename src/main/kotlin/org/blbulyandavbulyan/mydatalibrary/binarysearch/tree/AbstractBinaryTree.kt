package org.blbulyandavbulyan.mydatalibrary.binarysearch.tree

import org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.keynode.AbstractKeyNode

interface AbstractBinaryTree<K : Comparable<K>, NT : AbstractKeyNode<K, NT>> {
    fun contains(key: K): Boolean
    fun add(key: K): NT
    fun remove(key: K): NT
    fun find(key: K): NT
}
