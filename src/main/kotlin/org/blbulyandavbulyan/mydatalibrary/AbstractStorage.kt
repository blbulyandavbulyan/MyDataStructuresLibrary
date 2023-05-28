package org.blbulyandavbulyan.mydatalibrary

/**
 * Этот интерфейс предназначен для абстрактного хранилища
 * @author David Blbulyan
 */
interface AbstractStorage<K : Comparable<K>?, V> {
    /**
     * Ищет значение по ключу
     * @param key ключ, по которому будет производиться поиск
     * @return значение, по ключу key
     * @throws gb.classwork.lesson4.binarysearchthree.exceptions.KeyNotFoundException если такого ключа нет
     */
    fun findValue(key: K): V?

    /** Добавляет значение по ключу
     * @param key - ключ, по которому будет добавлено значение
     * @param value - значение, которое будет добавлено по ключу
     * @throws gb.classwork.lesson4.binarysearchthree.exceptions.KeyAlreadyAddedException если такой ключ уже был добавлен
     */
    fun addValue(key: K, value: V)

    /**
     * Удаляет значение по ключу и возвращает удалённое значение
     * @param key ключ, по которому будет производиться удаление.
     * @return значение, если такой ключ был
     * @throws gb.classwork.lesson4.binarysearchthree.exceptions.KeyNotFoundException если такого ключа не было
     *
     */
    fun getAndRemove(key: K): V?
}
