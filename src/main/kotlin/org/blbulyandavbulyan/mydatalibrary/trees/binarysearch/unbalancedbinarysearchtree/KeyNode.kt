package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode

class KeyNode<K: Comparable<K>>(key: K) : AbstractKeyNode<K, KeyNode<K>>(key)