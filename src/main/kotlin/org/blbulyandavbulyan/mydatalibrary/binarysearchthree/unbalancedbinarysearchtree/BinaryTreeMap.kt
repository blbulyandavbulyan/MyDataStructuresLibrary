package org.blbulyandavbulyan.mydatalibrary.binarysearchthree.unbalancedbinarysearchtree

import gb.classwork.lesson4.keynode.KeyValueNode
import org.blbulyandavbulyan.mydatalibrary.AbstractStorage

class BinaryTreeMap<K:Comparable<K>, V> : AbstractUnbalancedBinarySearchTree<K, KeyValueNode<K, V>>({ key->KeyValueNode(key, null)}),
    AbstractStorage<K, V> {
    override fun findValue(key: K): V? {
        return find(key).value
    }
    override fun addValue(key: K, value: V) {
        add(key).value = value
    }
    override fun getAndRemove(key: K): V? {
        return remove(key).value
    }
}