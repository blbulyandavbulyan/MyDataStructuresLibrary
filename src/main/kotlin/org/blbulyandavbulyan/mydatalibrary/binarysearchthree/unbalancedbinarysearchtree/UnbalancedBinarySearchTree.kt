package org.blbulyandavbulyan.mydatalibrary.binarysearchthree.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.keynode.KeyNode

class UnbalancedBinarySearchTree<K : Comparable<K>> : AbstractUnbalancedBinarySearchTree<K, KeyNode<K>>({ key-> KeyNode(key) })
