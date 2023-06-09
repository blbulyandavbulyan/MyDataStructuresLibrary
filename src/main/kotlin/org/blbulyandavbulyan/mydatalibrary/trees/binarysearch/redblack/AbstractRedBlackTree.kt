package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.redblack

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base.AbstractBinarySearchTree

open class AbstractRedBlackTree<K: Comparable<K>, NT: AbstractRedBlackKeyNode<K, NT>>(ntProducer: (K)->NT) : AbstractBinarySearchTree<K, NT>(ntProducer) {
    override fun remove(key: K): NT {
        val node: NT = find(key);
        if(node.isRed() && node.hasLeft() &&  node.hasRight()){

        }

        TODO("Исправить это место, написать и удаление и балансировку после него")
    }
    override fun add(key: K): NT {
        val insertedNode = super.add(key)
        fixInsertedNode(insertedNode)
        return insertedNode
    }
    protected fun fixInsertedNode(insertedNode: NT){
        var x = insertedNode
        // пока наш x - красный и у него родитель красный
        while (x.isRed() && x.parent?.isRed() == true){
            val father: NT = x.parent ?: break // WTF ? parent же не может быть здесь null
            val grandfather: NT = father.parent ?: throw IllegalStateException("Ошибка балансировки, у родителя красного балансируемого элемента отсутствует родителль!")
            //здесь избыточная проверка обоих детей дедушки, на то что они красные, чтобы проверить что наш дядя тоже красный
            //для того чтобы не разбираться кто из них левый, а кто правый
            if(grandfather.areBothChildrenRed()){//красный дядя
                //делаем свап цветов и переходим к дедушке
                grandfather.swapColor()
                x = grandfather
            }
            //сюда мы попадём если у нас чёрный дядя
            else if((x.isRight() && father.isLeft()) || (x.isLeft() && father.isRight())){ //по-хорошему, нам надо знать какой ребёнок у нас x(левый или правый)
                x = father
                if(father.isLeft())x.rotateLeft()
                else x.rotateRight()

            }
            else if((x.isLeft() && father.isLeft()) || (x.isRight() && father.isRight())){
                father.makeBlack()
                grandfather.makeRed()
                val possibleRoot = if(x.isLeft()) grandfather.rotateRight() else grandfather.rotateLeft()
                if(possibleRoot.parent == null)root=possibleRoot
                break
            }
        }
        //делаем корень чёрным, поскольку он должен быть всегда чёрным
        root?.makeBlack()
    }

}