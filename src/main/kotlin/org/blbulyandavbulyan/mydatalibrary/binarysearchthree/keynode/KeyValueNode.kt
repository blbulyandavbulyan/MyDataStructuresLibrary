package org.blbulyandavbulyan.mydatalibrary.binarysearchthree.keynode

class KeyValueNode<K : Comparable<K>, V>(key: K, var value: V?) : AbstractKeyNode<K, KeyValueNode<K, V>>(key)
