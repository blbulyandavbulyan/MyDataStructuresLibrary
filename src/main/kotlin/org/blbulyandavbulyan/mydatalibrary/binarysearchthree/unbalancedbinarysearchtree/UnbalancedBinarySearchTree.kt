package gb.classwork.lesson4.binarysearchthree.unbalancedbinarysearchtree

import gb.classwork.lesson4.keynode.KeyNode
import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.unbalancedbinarysearchtree.AbstractUnbalancedBinarySearchTree

class UnbalancedBinarySearchTree<K : Comparable<K>> : AbstractUnbalancedBinarySearchTree<K, KeyNode<K>>({ key->KeyNode(key)}) {
}
