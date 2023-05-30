package org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.keynode.KeyNode

class UnbalancedBinarySearchTree<K : Comparable<K>> : AbstractUnbalancedBinarySearchTree<K, KeyNode<K>>({ key-> KeyNode(key) })
