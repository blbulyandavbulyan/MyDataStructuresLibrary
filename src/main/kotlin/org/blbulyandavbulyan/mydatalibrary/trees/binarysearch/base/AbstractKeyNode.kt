package org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.base

import org.blbulyandavbulyan.mydatalibrary.trees.binarysearch.exceptions.IllegalRealizationException

abstract class AbstractKeyNode<K: Comparable<K>, NT: AbstractKeyNode<K, NT>>(key: K) {
    var key: K = key
        private set(value){
            //проверка перед установкой нового ключа:
            //если есть правый ребёнок у данной ноды, то данный ключ должен быть меньше её ключа
            //если есть левый ребёнок у данной ноды, то данный ключ должен быть больше её ключа
            //если у данной ноды есть родитель, то данный ключ должен быть: больше ключа родителя, если она правая или меньше ключа родителя если она левая

            //сравнение с ключом детей, если таковые имеются
            //сравнение с ключом правого ребёнка
            if (hasRight() && value >= (right?.key ?: throw IllegalRealizationException("Неверная реализация функции hasRight! Должна вернуть false если правый ребёнок null!")))
                throw IllegalArgumentException("Новый ключ должен быть меньше чем у правой ноды!")
            //сравнение с ключом левого ребёнка
            if (hasLeft() && value <= (left?.key ?: throw IllegalRealizationException("Неверная реализация функции hasLeft! Должна вернуть false если левый ребёнок null!")))
                throw IllegalArgumentException("Новый ключ должен быть больше чем у левой ноды!")
            //сравнение с родительским ключом, если есть родитель
            //сравнение с родительским ключом если мы левый ребёнок
            if(isLeft() && value >= (parent?.key ?: throw IllegalRealizationException("Неверная реализация функции isLeft! Должна вернуть false если родитель null!")))
                throw IllegalArgumentException("Текущая нода левый ребёнок, новый ключ должен быть меньше родительского!")
            //сравнение с родительским ключом если мы правый ребёнок
            if (isRight() && value <= (parent?.key ?: throw IllegalRealizationException("Неверная реализация функции isRight! Должна вернуть false если родитель null!")))
                throw IllegalArgumentException("Текущая нода правый ребёнок и её значение должно быть больше родительского!")
            //все проверки прошли успешно, значит наш ключ можно заменять на новй
            field = value
        }
    var parent: NT? = null
        internal set
    var left: NT? = null
        /**
         * Устанавливает левого ребёнка в данной ноде
         * @param value новый левый ребёнок
         * @throws IllegalArgumentException если добавляемая нода не null и у неё ключ не меньше чем у данной
         * */
        internal set(value){
            if(value == null || value.key < key){
                field = value
                value?.parent = this as NT
            }
            else throw IllegalArgumentException("Нода не может быть добавлена к данной в качестве левой, т.к. добавляемая нода не null и её ключ должен быть меньше чем ключ в данной!")
        }
    var right: NT? = null
        /**
         * Устанавливает правого ребёнка в данной ноде и в нём устанавливает родителя на текущую ноду
         * @param value новый правый ребёнок
         * @throws IllegalArgumentException если добавляемая нода не null и у неё ключ не больше чем у данной
         * */
        internal set(value){
            if(value == null || value.key > key){
                field = value
                value?.parent = this as NT
            }
            else throw IllegalArgumentException("Нода не может быть добавлена к данной в качестве правой, т.к. добавляемая нода не null и её ключ должен быть больше чем ключ в данной!")
        }
    /**
     * Проверяет есть ли у данной ноды оба ребёнка
     * @return true если да, иначе false
     * */
    fun hasTwoChildren(): Boolean = hasLeft() && hasRight()

    /**
     * Проверяет, есть ли у данной ноды хотя бы один ребёнок
     * @return true если есть, иначе false
     * */
    fun hasChild(): Boolean = hasLeft() || hasRight()
    /**
     * Проверяет, есть ли у данной ноды родитель
     * @return true если есть, иначе false
     * */
    fun hasParent(): Boolean = parent != null
    /**
     * Проверяет, имеет ли текущая нода левого ребёнка
     * @return true если да, иначе false
     * */
    fun hasLeft(): Boolean = left != null
    /**
     * Проверяет, имеет ли текущая нода правого ребёнка
     * @return true если да, иначе false
     * */
    fun hasRight(): Boolean = right != null

    /**
     * Функция проверяет текущую ноду на то, левая ли она у своего родителя
     * @return True - если да, False - если нет и если нет родителя у текущей ноды
     * */
    fun isLeft(): Boolean = parent?.left === this

    /**
     * Функция проверяет текущую ноду на то, правая ли она у своего родителя
     * @return True - если да, False - если нет и если нет родителя у текущей ноды
     * */
    fun isRight(): Boolean = parent?.right === this

    /**
     * Функция обменивает ключи между текущей нодой и данной
     * */
    internal fun swapKeys(anotherNode: NT){
        val myOldKey: K = replaceKey(anotherNode.key)
        anotherNode.replaceKey(myOldKey)
    }

    /**
     * Функция заменяет текущий ключ на новый
     * @param newKey новый ключ
     * @return старый ключ
     * @throws IllegalArgumentException если ключ не вписывается в иерархию дерева, если он не больше левого или не меньше правого
     * либо если данная нода левая и ключ не меньше родительского, либо она правая и ключ не больше родительского
     * */
    internal fun replaceKey(newKey: K): K{
        val oldValue: K = this.key
        this.key = newKey
        return oldValue
    }

    /**
     * Заменяет соответствующего ребёнка в данной ноде, на нового
     * @return старого ребёнка
     * @throws IllegalArgumentException если этот ребёнок не принадлежит данной ноде
     * */
    internal fun replaceChild(replacingChild: NT, newChild: NT?): NT{
        if(replacingChild === left) left = newChild
        else if(replacingChild === right) right = newChild
        else throw IllegalArgumentException("Невозможно заменить ребёнка, т.к. replacingChild не является ребёнком данной ноды")
        return replacingChild
    }
}