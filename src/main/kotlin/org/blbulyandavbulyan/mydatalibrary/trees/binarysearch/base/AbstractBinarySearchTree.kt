package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.AbstractBinaryTree
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.KeyAlreadyAddedException
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.KeyNotFoundException
import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.UnexpectedException
import java.util.function.Function
/**
* Данный класс предоставляет реализацию двоичного дерева поиска без балансировки
* @author David Blbulyan
* */
abstract class AbstractBinarySearchTree<K: Comparable<K>, NT : AbstractKeyNode<K, NT>>(private val ntProducer: Function<K, NT>) :
    AbstractBinaryTree<K, NT> {
    private var size: Int = 0
    private var root: NT? = null;
    override fun find(key: K): NT{
        val foundNode = findEndPoint(key)
        if(foundNode.key == key)return foundNode;
        else throw KeyNotFoundException();
    }

    override fun add(key: K): NT {
        val node = ntProducer.apply(key)
        findPlaceAndInsertNode(node)
        size++;
        return node
    }

    override fun remove(key: K): NT {
        // FIXME: Здесь ещё внезапно может вылететь исключение KeyAlreadyAddedException проверить почему !!!
        val nodeForDelete = findEndPoint(key)
        if(nodeForDelete.key == key){
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
        else throw KeyNotFoundException()
    }
    /**
     * Функция находит наиболее подходящую ноду по ключу
     * @param key ключ, для поиска
     * @return Либо ноду содержащую ключ key, либо ноду, которая должна была быть родительской, если такой нет
     * @throws IllegalStateException если корень дерева null
     * */
    protected fun findEndPoint(key: K): NT{
        var currentRoot = root ?: throw IllegalStateException()
        while (currentRoot.hasChild()){
            if(key == currentRoot.key)
                return currentRoot;
            else if(key > currentRoot.key)
                currentRoot = currentRoot.right ?: break
            else if(key < currentRoot.key)
                currentRoot = currentRoot.left ?: break
        }
        return currentRoot
    }
    /**
     * Находит и вставляет ноду по правилам бинарного дерева
     * @param node нода для вставки
     * @throws KeyAlreadyAddedException если такой ключ в дереве уже есть
     * @throws UnexpectedException если найденная ссылка на ноду для вставки уже содержит значения в требуемом месте
     * */
    protected fun findPlaceAndInsertNode(node: NT){
        val nnRoot: NT = root ?: node;
        if (nnRoot === node){
            root = node;
            return;
        }
        else{
            val endPoint = findEndPoint(node.key)
            if(endPoint.key == node.key)
                throw KeyAlreadyAddedException()
            else if(node.key > endPoint.key && !endPoint.hasRight())
                endPoint.right = node
            else if(node.key < endPoint.key && !endPoint.hasLeft())
                endPoint.left = node
            else throw UnexpectedException("Неожиданное исключение, ссылка в которую нужно записать оказалась не null!") //по идее это исключение не должно возникнуть, но мало ли;
        }
    }
    /**
     * Проверяет есть ли заданный ключ в дереве
     * @param key ключ для проверки
     * @return True - если такой ключ есть, иначе False
    * */
    override fun contains(key: K): Boolean {
        return findEndPoint(key).key == key
    }
}