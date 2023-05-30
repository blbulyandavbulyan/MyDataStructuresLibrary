package org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.keynode

class KeyValueNode<K : Comparable<K>, V>(key: K, var value: V?) : AbstractKeyNode<K, KeyValueNode<K, V>>(key)
