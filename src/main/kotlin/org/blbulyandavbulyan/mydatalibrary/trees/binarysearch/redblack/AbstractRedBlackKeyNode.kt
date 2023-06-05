package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode

abstract class AbstractRedBlackKeyNode<K: Comparable<K>, NT: AbstractRedBlackKeyNode<K, NT>>(key: K): AbstractKeyNode<K, NT>(key){
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
