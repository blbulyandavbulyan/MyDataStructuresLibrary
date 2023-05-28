package org.blbulyandavbulyan.mydatalibrary.binarysearchthree.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.keynode.KeyValueNode

class BinaryTreeMap<K:Comparable<K>, V> : AbstractUnbalancedBinarySearchTree<K, KeyValueNode<K, V>>({ key-> KeyValueNode(key, null) }){
    fun findValue(key: K): V? {
        return find(key).value
    }
    fun addValue(key: K, value: V) {
        add(key).value = value
    }
    fun getAndRemove(key: K): V? {
        return remove(key).value
    }
}