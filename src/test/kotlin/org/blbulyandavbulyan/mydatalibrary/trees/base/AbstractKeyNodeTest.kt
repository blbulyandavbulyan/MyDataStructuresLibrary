package org.blbulyandavbulyan.mydatalibrary.trees.base

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

abstract class AbstractKeyNodeTest<NT:  AbstractKeyNode<Int, NT>> {
    // Тестирование свойств key, parent, left и right при создании экземпляра класса

    @Test
    fun `key should be set correctly`() {
        val key = 10
        val node = createNode(key)
        assertEquals(key, node.key)
    }

    @Test
    fun `parent should be null by default`() {
        val node = createNode(10)
        assertNull(node.parent)
    }

    @Test
    fun `left should be null by default`() {
        val node = createNode(10)
        assertNull(node.left)
    }

    @Test
    fun `right should be null by default`() {
        val node = createNode(10)
        assertNull(node.right)
    }

    // Тестирование функций hasChild и hasParent

    @Test
    fun `hasChild should return false when node has no children`() {
        val node = createNode(10)
        assertFalse(node.hasChild())
    }

    @Test
    fun `hasChild should return true when node has left child`() {
        val node = createNode(10)
        node.left = createNode(5)
        assertTrue(node.hasChild())
    }

    @Test
    fun `hasChild should return true when node has right child`() {
        val node = createNode(10)
        node.right = createNode(15)
        assertTrue(node.hasChild())
    }

    @Test
    fun `hasParent should return false when node has no parent`() {
        val node = createNode(10)
        assertFalse(node.hasParent())
    }

    @Test
    fun `hasParent should return true when node has a parent`() {
        val node = createNode(10)
        node.parent = createNode(5)
        assertTrue(node.hasParent())
    }

    @Test
    fun `when set left, parent should be also set`(){
        val parentNode = createNode(10);
        val leftChild = createNode(5);
        parentNode.left = leftChild;
        assertTrue(parentNode.left === leftChild, "Левая нода у родительской не равна только что установленной!")
        assertTrue(leftChild.parent == parentNode, "Родитель у левой ноды не соответствует ожидаемому!")
    }
    @Test
    fun `when set right, parent should be also set`(){
        val parentNode = createNode(10)
        val rightChild = createNode(15)
        parentNode.right = rightChild
        assertTrue(parentNode.right === rightChild, "Правая нода у родительской не равна заданно!")
        assertTrue(rightChild.parent === parentNode, "Родитель у правой ноды не соответствует ожидаемому!")
    }
    @Test
    fun `set left node large than current should throw IllegalArgumentException`(){
        val r = createNode(10)
        assertThrows(IllegalArgumentException::class.java) {
            r.left = createNode(15)
        }
    }
    @Test
    fun `set right node less than current should throw IllegalArgumentException`(){
        val r = createNode(10)
        assertThrows(IllegalArgumentException::class.java) {
            r.right = createNode(5)
        }
    }
    // Вспомогательная функция для создания экземпляра класса KeyNode

    abstract fun createNode(key: Int): NT
}