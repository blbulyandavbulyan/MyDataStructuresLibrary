package org.blbulyandavbulyan.mydatalibrary.binarysearchthree

import gb.classwork.lesson4.keynode.AbstractKeyNode

interface AbstractBinaryTree<K : Comparable<K>, NT : AbstractKeyNode<K, NT>> {
    fun exists(key: K): Boolean
    fun add(key: K): NT
    fun remove(key: K): NT
    fun find(key: K): NT
}
