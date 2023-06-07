package org.blbulyandavbulyan.mydatalibrary.trees.base

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.AbstractBinaryTree
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.KeyAlreadyAddedException
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.KeyNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import java.lang.RuntimeException
import kotlin.random.Random

abstract class AbstractBinarySearchTreeTest<ABT: AbstractBinaryTree<Int, *>> {
    private var inputData: Collection<Int>? = null
    private var tree: ABT? = null
    private fun generateData(count: Int = 100) = generateSequence(Random::nextInt).distinct().take(count).toList()
    private fun getNotNullData() : Pair<ABT, Collection<Int>> = Pair(tree ?: throw RuntimeException("WTF, tree is null!"), inputData ?: throw RuntimeException("WTF, inputdata is null!!"))
    private fun generateNotEqualData(data: Collection<Int>): Collection<Int> =
        generateSequence(Random::nextInt).filter { it !in data }.take(data.size).toList()
    abstract fun createBinaryTree(): ABT
    @BeforeEach
    fun fill_tree(){
        val tree = createBinaryTree()
        val inputData: Collection<Int> = generateData()
        for (key in inputData){
            try {
                tree.add(key);
            }
            catch (e: Exception){
                Assertions.fail("Ошибка добавления ключа $key, набор ключей: $inputData", e)
            }
        }
        this.inputData = inputData
        this.tree = tree
    }
    @Test
    fun `contains all added elements`(){
        val (nnTree, nnInputData) = getNotNullData()
        //проверка в том же порядке, в каком и добавляли
        val checker:(Int)->Unit = { k->
            try{
                Assertions.assertTrue({nnTree.contains(k)}, "Дерево не содержит ключа $k, набор ключей: $nnInputData")
            }
            catch (e: Exception){
                Assertions.fail("Ошибка проверки ключа $k на существования, набор ключей: $nnInputData", e)
            }
        }
        for(key in nnInputData ){
            checker(key)
        }
        //проверка в случайном порядке
        val shuffledInputData = nnInputData.shuffled()
        for (key in shuffledInputData){
            checker(key)
        }
    }
    @Test fun `not contains not added elements`(){
        val (nnTree, nnInputData) = getNotNullData()
        val shouldNotExist = generateNotEqualData(nnInputData)
        for (key in shouldNotExist){
            try {
                Assertions.assertFalse({nnTree.contains(key)}, "Дерево содержит ключ $key, который в него не добавляли, набор ключей: $nnInputData")
            }
            catch (e: Exception){
                Assertions.fail("Ошибка проверки на несуществующий ключ $key, набор ключей: $nnInputData", e)
            }
        }
    }
    @Test fun `ordered remove all data`(){
        val (nnTree, nnInputData) = getNotNullData()
        //удаляем все ключи
        for (key in nnInputData){
            try{
                nnTree.remove(key)
            }
            catch (e: KeyNotFoundException){
                Assertions.fail("Попытка удалить ключ $key, а его уже нет, набор ключей: $nnInputData", e)
            }
            catch (e: Exception){
                Assertions.fail("Ошибка при удалении ключа $key, набор ключа: $nnInputData")
            }
        }
        //далее проверяем, что их действительно нет
        for(key in nnInputData){
            Assertions.assertFalse({nnTree.contains(key)}, "Дерево содержит удалённый ключ: $key, набор ключей: $nnInputData")
        }
    }
    // Тестирование функции exists

    @Test
    fun `exists should return true when key exists in the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertTrue(tree.contains(10))
    }

    @Test
    fun `exists should return false when key does not exist in the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertFalse(tree.contains(20))
    }

    // Тестирование функции add

    @Test
    fun `add should add a new node to the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertTrue(tree.contains(5))
        Assertions.assertTrue(tree.contains(10))
        Assertions.assertTrue(tree.contains(15))
    }
    @Test
    fun `add should throw KeyAlreadyAddedException if the key already exists in the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertThrows(KeyAlreadyAddedException::class.java) {
            tree.add(10)
        }
    }

    @Test
    fun `remove should throw KeyNotFoundException if the key does not exist in the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertThrows(KeyNotFoundException::class.java) {
            tree.remove(20)
        }
    }
    // Тестирование функции remove

    @Test
    fun `remove should remove an existing node from the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertTrue(tree.contains(10))
        tree.remove(10)
        Assertions.assertFalse(tree.contains(10))
    }

    @Test
    fun `remove should not modify the tree if the key does not exist`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertFalse(tree.contains(20))
        Assertions.assertThrows(KeyNotFoundException::class.java) { tree.remove(20) }
        Assertions.assertFalse(tree.contains(20))
    }


    // Тестирование функции find

    @Test
    fun `find should return the node with the specified key`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        val node = tree.find(5)
        Assertions.assertNotNull(node)
        Assertions.assertEquals(5, node.key)
    }

    @Test
    fun `find should throw KeyNotFoundException if the key does not exist in the tree`() {
        val tree = createBinaryTree()
        tree.add(10)
        tree.add(5)
        tree.add(15)
        Assertions.assertThrows(KeyNotFoundException::class.java) {
            tree.find(20)
        }
    }

}

