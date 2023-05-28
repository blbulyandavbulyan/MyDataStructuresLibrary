package org.blbulyandavbulyan.mydatalibrary.binarysearchthree.unbalancedbinarysearchtree

import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.keynode.AbstractKeyNode
import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.exceptions.KeyAlreadyAddedException
import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.exceptions.KeyNotFoundException
import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.exceptions.UnexpectedException
import org.blbulyandavbulyan.mydatalibrary.binarysearchthree.AbstractBinaryTree
import java.util.function.Function
/**
* Данный класс предоставляет реализацию двоичного дерева поиска без балансировки
* @author David Blbulyan
* */
abstract class AbstractUnbalancedBinarySearchTree<K: Comparable<K>, NT : AbstractKeyNode<K, NT>>(private val ntProducer: Function<K, NT>) :
    AbstractBinaryTree<K, NT> {
    private var size: Int = 0
    private var root: NT? = null;
    override fun find(key: K): NT = findNode(key) ?: throw KeyNotFoundException()

    override fun add(key: K): NT {
        val node = ntProducer.apply(key)
        findPlaceAndInsertNode(node)
        size++;
        return node
    }

    override fun remove(key: K): NT {
        // FIXME: Здесь ещё внезапно может вылететь исключение KeyAlreadyAddedException проверить почему !!!
        val nodeForDelete = findNode(key) ?: throw KeyNotFoundException();//ищем удаляемую ноду
//        val value = nodeForDelete.value
        if(nodeForDelete.hasParent()){//если наш найденный элемент это не корень
            val parent: NT = nodeForDelete.parent ?: throw UnexpectedException("WTF, сюда не мог попасть null!")
            if(parent.left === nodeForDelete){
                //у нас удаляемый элемент левый в parent, значит он меньше чем parent, а значит правый элемент в удаляемом элементе тоже будет меньше чем parent
                parent.left = nodeForDelete.left
                parent.left?.parent = parent//установка нового родителя
                val right = nodeForDelete.right
                if(right != null)findPlaceAndInsertNode(right)//если правый элемент у удаляемого был, добавляем его
            }
            else if(parent.right === nodeForDelete){
                //у нас удаляемый элемент правый в parent, значит он больше parent, значит правый элемент удаляемого элемента тоже будет больше parent
                parent.right = nodeForDelete.right
                parent.right?.parent = parent//установка нового родителя
                val left = nodeForDelete.left
                if(left != null)findPlaceAndInsertNode(left)//если левый элемент у удаляемого был, добавляем его
            }
            else throw UnexpectedException("Что-то пошло не так и родительский элемент не является родительским элементом...")
        }
        else {
            //здесь вероятно нужно более детально выбирать какой ребёнок теперь станет корневым
            val newRoot: NT? = (root?.right ?: root?.left) //выбираем правого если он не null, иначе выбираем левого
            val futureChild: NT? = (root?.left ?: root?.right)//а здесь наоборот, выбираем левого если он не null, иначе правого
            newRoot?.parent = null;
            root = newRoot//если новый корень null, значит у него не было потомков
            if(newRoot != null){//бум, мы точно знаем что у старого корня были дети
                if(newRoot !== futureChild && futureChild != null)
                    findPlaceAndInsertNode(futureChild)
            }
        }
        size--
        return nodeForDelete
    }
    private fun findNode(key: K): NT?{
        var currentRoot = root
        while (currentRoot?.hasChild() == true){
            if(key == currentRoot.key)
                return currentRoot;
            else if(key > currentRoot.key)
                currentRoot = currentRoot.right;
            else if(key < currentRoot.key)
                currentRoot = currentRoot.left;
        }
        return if(currentRoot?.key == key) currentRoot
        else null
    }
    protected fun findPlaceAndInsertNode(node: NT){
        val nnRoot: NT = root ?: node;
        if (nnRoot === node){
            root = node;
            return;
        }
        else{
            var currentNodeToInsert = nnRoot;
            while (currentNodeToInsert.left != null || currentNodeToInsert.right != null){
                currentNodeToInsert = if (node.key > currentNodeToInsert.key) currentNodeToInsert.right ?: break
                else if(node.key < currentNodeToInsert.key) currentNodeToInsert.left ?: break
                else throw KeyAlreadyAddedException()
            }
            node.parent = currentNodeToInsert
            if(currentNodeToInsert.right == null && node.key > currentNodeToInsert.key)
                currentNodeToInsert.right = node
            else if (currentNodeToInsert.left == null && node.key <= currentNodeToInsert.key)
                currentNodeToInsert.left = node
            else if(node.key != currentNodeToInsert.key) throw UnexpectedException("Неожиданное исключение, ссылка в которую нужно записать оказалась не null!") //по идее это исключение не должно возникнуть, но мало ли;
        }
    }
    override fun exists(key: K): Boolean {
        return findNode(key) != null
    }
}