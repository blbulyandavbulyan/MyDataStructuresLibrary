package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.base.AbstractBinarySearchTreeTest
import org.junit.jupiter.api.Test

class RedBlackTreeTest: AbstractBinarySearchTreeTest<RedBlackTree<Int>>() {
    override fun createBinaryTree(): RedBlackTree<Int>  = RedBlackTree()
    @Test fun `all paths have the same black depth`(){
        TODO("Не реализовано на данный момент")
    }
    @Test fun `all red nodes have only red children`(){
        TODO("Не реализовано на данный момент")
    }

}