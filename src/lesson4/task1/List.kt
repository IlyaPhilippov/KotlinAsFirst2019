@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

fun decades(b: Int): String {
    val n = b % 10
    val rus11to19 = listOf(
        "", "одиннадцать", "двенадцать", "тринадцать",
        "четыренадцать", "пятнадцать", "шестнадцать",
        "семнадцать", "восемнадцать", "девятнадцать"
    )
    return rus11to19[n]
}

fun thousands(d: Int): String {
    var counter = 0
    var k = d
    var b = 0
    val c = d % 100
    val t = d % 10
    val res = mutableListOf<String>()
    val rus1to9 = listOf(
        "", "одна тысяча", "две тысячи", "три тысячи",
        "четыре тысячи", "пять тысяч", "шесть тысяч",
        "семь тысяч", "восемь тысяч", "девять тысяч"
    )
    while (k > 0) {
        counter++
        b = k % 10
        k /= 10
        when {
            t == 0 -> res.add(
                0, when (counter) {
                    1 -> rus1to9[b]
                    2 -> rus10to90[b] + " тысяч"
                    3 -> rus100to900[b]
                    else -> ""
                }
            )
            c in 11..19 -> res.add(
                0, when (counter) {
                    2 -> decades(c) + " тысяч"
                    3 -> rus100to900[b]
                    else -> ""
                }
            )
            else -> res.add(
                0, when (counter) {
                    1 -> rus1to9[b]
                    2 -> rus10to90[b]
                    3 -> rus100to900[b]
                    else -> ""
                }
            )
        }
    }
    return res.joinToString(separator = " ").trim()
}

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    var abs = 0.0
    if (v.isEmpty())
        return 0.0
    else
        for (i in 0 until v.size)
            abs += v[i] * v[i]
    return sqrt(abs)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    val size = list.size
    var sum = 0.0
    if (list.isEmpty())
        return 0.0
    else
        for (i in 0 until size)
            sum += list[i]
    return sum / size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val mid = mean(list)
    if (list.isEmpty())
        return list
    else
        for (i in 0 until list.size)
            list[i] -= mid
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var sum = 0
    if (a.isEmpty() && b.isEmpty())
        return 0
    else
        for (i in 0 until a.size)
            sum += a[i] * b[i]
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if (p.isEmpty())
        return 0
    var sum = p[0]
    for (i in 1 until p.size)
        sum += p[i] * x.toDouble().pow(i).toInt()
    return sum
}


/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if (list.isEmpty())
        return list
    var sum = 0
    for (i in 0 until list.size) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    if (isPrime(n))
        return listOf(n)
    var k = n
    val divisiors = mutableListOf<Int>()
    var divisor = 2
    while (k / divisor != 0) {
        if (k % divisor == 0) {
            k /= divisor
            divisiors += divisor
        } else
            divisor++
    }
    return divisiors.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): MutableList<Int> {
    val result = mutableListOf<Int>()
    var k = n
    if (n == 0) {
        result.add(0)
        return result
    }

    while (k > 0) {
        result.add(0, k % base)
        k /= base
    }
    return result
}


/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val a = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"
        , "s", "t", "u", "v", "w", "x", "y", "z"
    )
    val convert = convert(n, base)
    return convert.joinToString(separator = "", transform = { if (it < 10) "$it" else a[it - 10] })
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var size = digits.size
    var result = 0
    for (i in 0 until size) {
        result += digits[i] * base.toDouble().pow(size - 1).toInt()
        size--
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val a = listOf(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r"
        , "s", "t", "u", "v", "w", "x", "y", "z"
    ).joinToString(separator = "")
    val list = mutableListOf<Int>()
    for (char in str)
        list.add(
            if (char in a)
                (char - 'a') + 10
            else
                (char - '0')
        )
    return decimal(list, base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var k = n
    var counter = 0
    var plist = listOf<String>()
    val list = mutableListOf<String>()
    val rim1 = listOf("I", "V", "X")
    val rim2 = listOf("X", "L", "C")
    val rim3 = listOf("C", "D", "M")
    val rim4 = listOf("M")
    var c = 0
    while (k > 0) {
        counter++
        c = k % 10
        k /= 10
        plist = when (counter) {
            1 -> rim1
            2 -> rim2
            3 -> rim3
            else -> rim4
        }
        list.add(
            0, when {
                counter >= 4 -> plist[0].repeat(c)
                c == 9 -> plist[0].repeat(10 - c) + plist[2]
                c == 0 -> ""
                c in 1..3 -> plist[0].repeat(c)
                c in 4..5 -> plist[0].repeat(5 - c) + plist[1]
                else -> plist[1] + (plist[0].repeat(c - 5))
            }
        )
    }
    return list.joinToString(separator = "")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
val rus1to9 = listOf(
    "", "один", "два", "три",
    "четыре", "пять", "шесть",
    "семь", "восемь", "девять"
)
val rus10to90 = listOf(
    "", "десять", "двадцать", "тридцать",
    "сорок", "пятьдесят", "шестьдесят",
    "семьдесят", "восемьдесят", "девяносто"
)
val rus100to900 = listOf(
    "", "сто", "двести", "триста",
    "четыреста", "пятьсот", "шестьсот",
    "семьсот", "восемьсот", "девятьсот"
)

fun russian(n: Int): String {
    val result = mutableListOf<String>()
    var k = n
    var counter = 0
    var c = 0
    val b = k % 100
    val d = k / 1000
    while (k > 0) {
        counter++
        c = k % 10
        k /= 10
        if (b in 11..19)
            result.add(
                0, when (counter) {
                    2 -> decades(b)
                    3 -> rus100to900[c]
                    4 -> thousands(d)
                    else -> ""
                }
            )
        else
            result.add(
                0, when (counter) {
                    1 -> rus1to9[c]
                    2 -> rus10to90[c]
                    3 -> rus100to900[c]
                    4 -> thousands(d)
                    else -> ""
                }
            )
    }
    return result.joinToString(separator = " ").trim()
}