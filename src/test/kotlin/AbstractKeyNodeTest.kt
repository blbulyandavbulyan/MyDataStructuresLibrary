import org.blbulyandavbulyan.mydatalibrary.binarysearch.tree.keynode.KeyNode
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
class AbstractKeyNodeTest {
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

    // Вспомогательная функция для создания экземпляра класса KeyNode

    private fun <K: Comparable<K>> createNode(key: K): KeyNode<K> {
        return KeyNode(key)
    }
}