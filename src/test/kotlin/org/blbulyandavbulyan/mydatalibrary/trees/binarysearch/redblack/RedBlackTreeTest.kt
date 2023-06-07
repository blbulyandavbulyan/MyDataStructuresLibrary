package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractBinarySearchTreeTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RedBlackTreeTest: AbstractBinarySearchTreeTest<RedBlackTree<Int>>() {
    override fun createBinaryTree(): RedBlackTree<Int>  = RedBlackTree()
    @Test fun `all paths have the same black depth`(){
        val (nnTree, _) = getNotNullData()
        val depths = mutableSetOf<Int>()
        nnTree.iterator().asSequence().filter { node->!node.hasLeft() || !node.hasRight() }.forEach {endPoint->
            var currentEndPoint = endPoint
            var currentBlackDepth = 0
            while (currentEndPoint.hasParent()){
                if(currentEndPoint.isBlack())
                    currentBlackDepth++
                currentEndPoint = currentEndPoint.parent ?: throw RuntimeException("hasParent вернул true, а parent равен null")
            }
            depths.add(currentBlackDepth)
        }
        Assertions.assertEquals(1, depths.size, "На некоторых путях обнаружены разные чёрные глубины!")
    }
    @Test fun `all red nodes have only red children`(){
        val (nnTree, _) = getNotNullData()
        nnTree.iterator().asSequence().filter { node->node.hasParent() && node.isRed() }.forEach {node->
            Assertions.assertFalse(node.parent?.isRed() == true, "У красной ноды есть красный родитель!")
        }
    }

}