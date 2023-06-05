package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractBinarySearchTree

class AbstractRedBlackTree<K: Comparable<K>, NT: AbstractRedBlackKeyNode<K, NT>>(ntProducer: (K)->NT) : AbstractBinarySearchTree<K, NT>(ntProducer) {
    override fun remove(key: K): NT {
        TODO("Исправить это место, написать и удаление и балансировку после него")
    }
    override fun add(key: K): NT {
        val insertedNode = super.add(key)
        fixInsertedNode(insertedNode)
        return insertedNode
    }
    protected fun fixInsertedNode(insertedNode: NT){
        var x = insertedNode
        //
        while (x.hasParent()){

        }
        root?.color = AbstractRedBlackKeyNode.Color.BLACK
        TODO("Сделать балансировку здесь")
    }

}