package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractKeyNodeTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
class AbstractRedBlackKeyNodeTest : AbstractKeyNodeTest<RedBlackKeyNode<Int>>() {
    override fun createNode(key: Int): RedBlackKeyNode<Int> {
        return RedBlackKeyNode(key);
    }
    @Test fun `rotate left all children of operating values are not null and rotating node doesn't have a parent`(){
        val b = createNode(20)
        val a = createNode(10)
        val d = createNode(30)
        val c = createNode(25)
        val e = createNode(35)
        b.right = d
        b.left = a
        d.left = c
        d.right = e
        val top = b.rotateLeft()
        //проверка на то что у нашей новой D, которая оказалась сверху в результате поворота отсутсвует родитель, т.к. у b родителя нет
        assertTrue(top === d,  "Корень поддерева не соответствует ожидаемому после поворота")
        assertTrue(top.parent == null, "У новой вершины с верху родитель не null")
        assertTrue(top.left === b, "Левый элемент у корня поддерева не соответствует ожидаемому")
        assertTrue(d.right === e, "Правый элемент у корня поддерева изменился!")
        assertTrue(b.left === a, "Левый элемент у правого элемента корня поддерева изменился!")
        assertTrue(b.right === c, "Правый элемент у левого элемента корня поддерева не соответствует ожидаемому")
    }
    @Test fun `rotateLeft must throw UnsupportedOperationException if there is no right child`(){
        val b = createNode(20)
        val a = createNode(15)
        b.left = a
        assertThrows(UnsupportedOperationException::class.java){
            b.rotateLeft()
        }
    }

    @Test fun `rotate right all of operating children are not null and rotating node doesn't have a parent`(){
        val d = createNode(20)
        val b = createNode(15)
        val a = createNode(30)
        val c = createNode(19)
        val e = createNode(10)
        d.left = b
        d.right = a
        b.left = e
        b.right = c
        val top = d.rotateRight()
        assertTrue(top === b, "Корень поддерева не соответствует ожидаемому после поворота!")
        assertTrue(top.parent == null, "У новой вершины с верху родитель не null")
        assertTrue(top.left  === e, "Левый ребёнок у корня не соответствует ожидаемому!")
        assertTrue(b.right === d, "Правый ребёнок у корня не соответствует ожидаемому!")
        assertTrue(d.left === c, "Левый ребёнок у правого ребёнка корня не соответствует ожидаемому!")
        assertTrue(d.right === a, "Правый ребёнок у правого ребёнка корня не соответствует ожидаемому")
    }
    @Test fun `rotateRight must throw UnsupportedOperationException if there is no left child`(){
        val r = createNode(20);
        r.right = createNode(30)
        assertThrows(UnsupportedOperationException::class.java){
            r.rotateRight()
        }
    }
    @Test fun `new node should be red`(){
        val g = createNode(10)
        assertTrue(g.isRed())
    }
    @Test fun `swapping color`(){
        val g = createNode(30)
        val l = createNode(10)
        val r = createNode(40)
        g.left = l
        g.right = r
        g.swapColor()
        assertTrue(g.isRed())
        assertTrue(l.isBlack())
        assertTrue(r.isBlack())
    }
}