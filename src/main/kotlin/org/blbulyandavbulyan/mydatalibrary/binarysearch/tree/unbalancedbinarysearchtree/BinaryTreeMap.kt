package org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.keynode.KeyValueNode

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