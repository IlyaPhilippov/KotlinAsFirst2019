@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(width, height, e)

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val width: Int, override val height: Int, val e: E) : Matrix<E> {
    val listOfLists = MutableList(width) { MutableList(height) { e } }
    override fun get(row: Int, column: Int): E = listOfLists[column][row]

    override fun get(cell: Cell): E = get(cell.column, cell.row)

    override fun set(row: Int, column: Int, value: E) {
        listOfLists[column][row] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.column, cell.row, value)
    }

    override fun equals(other: Any?) =
        other is MatrixImpl<*> &&
                height == other.height &&
                width == other.width &&
                listOfLists == other.listOfLists

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + (e?.hashCode() ?: 0)
        result = 31 * result + listOfLists.hashCode()
        return result
    }
}

