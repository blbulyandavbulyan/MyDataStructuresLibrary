package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractBinarySearchTree

class UnbalancedBinarySearchTree<K : Comparable<K>> : AbstractBinarySearchTree<K, KeyNode<K>>({ key-> KeyNode(key) })
