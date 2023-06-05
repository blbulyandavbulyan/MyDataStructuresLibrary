package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.unbalancedbinarysearchtree.KeyNode

class RedBlackKeyNode<K: Comparable<K>>(key: K): AbstractKeyNode<K, RedBlackKeyNode<K>>(key){
    enum class Color{RED, BLACK}
    var color: Color = Color.RED
        internal set
    fun swapColor(){
        parent?.color = Color.RED
        left?.color = Color.BLACK
        right?.color = Color.BLACK
    }

    fun rotateLeft(){

    }
    fun rotateRight(){

    }
}
