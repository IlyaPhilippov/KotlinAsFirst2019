@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

fun monthToNumber(month: String): Int {
    return when (month) {
        "января" -> 1
        "февраля" -> 2
        "марта" -> 3
        "апреля" -> 4
        "мая" -> 5
        "июня" -> 6
        "июля" -> 7
        "августа" -> 8
        "сентября" -> 9
        "октября" -> 10
        "ноября" -> 11
        "декабря" -> 12
        else -> -1
    }
}

fun numberToMonth(month: Int): String {
    return when (month) {
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11 -> "ноября"
        12 -> "декабря"
        else -> ""
    }
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main() {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    try {
        val date = str.split(" ")
        if (date.size != 3)
            return ""
        val day = date[0].toInt()
        val month = monthToNumber(date[1])
        val year = date[2].toInt()
        if ((month == -1) || (day > daysInMonth(month, year)) || (day < 1) || (year < 0))
            return ""
        return String.format("%02d.%02d.%d", day, month, year)
    } catch (exc: NumberFormatException) {
        return ""
    }


}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    try {
        val date = digital.split(".")
        if (date.size != 3)
            return ""
        val day = date[0].toInt()
        val month = numberToMonth(date[1].toInt())
        val year = date[2].toInt()
        if ((month == "") || (day > daysInMonth(date[1].toInt(), year)) || (day < 1) || (year < 0))
            return ""

        return String.format("%d %s %d", day, month, year)
    } catch (exp: NumberFormatException) {
        return ""
    }
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку.
 *
 * PS: Дополнительные примеры работы функции можно посмотреть в соответствующих тестах.
 */
fun flattenPhoneNumber(phone: String): String {
    for (i in 0 until phone.length)
        if ((phone[i] == '(') && (phone[i + 1] == ')'))
            return ""
    val a = Regex("""[^+\- 0-9()]""")
    if (a.findAll(phone, startIndex = 0).toMutableList() != emptyList<MatchResult>())
        return ""
    return Regex("""[\- ()]""").replace(phone, "")


}


/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    val a = Regex("""[^ 0-9%\-]""")
    if (a.findAll(jumps, startIndex = 0).toMutableList() != emptyList<MatchResult>())
        return -1
    val replace = (Regex("""[%\- ]""").replace(jumps, " ")).split(" ").toMutableList()
    var max = -1000
    for (i in 0 until replace.size)
        replace.remove("")
    for (i in 0 until replace.size)
        if (replace[i].toInt() > max)
            max = replace[i].toInt()
    return if (max == -1000) -1
    else max


}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки, а также в случае отсутствия удачных попыток,
 * вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    val a = Regex("""[+]""")
    if (a.findAll(jumps, startIndex = 0).toMutableList() == emptyList<MatchResult>())
        return -1
    val b = Regex("""[^ 0-9%\-+]""")
    if (b.findAll(jumps, startIndex = 0).toMutableList() != emptyList<MatchResult>())
        return -1
    val replace = Regex("""[%\- ]""").replace(jumps, " ").split(" ").toMutableList()
    var max = -1000
    for (i in 0 until replace.size)
        replace.remove("")
    for (i in 0 until replace.size) {
        if (replace[i] == "+")
            replace[i - 1] += replace[i]
    }
    replace.removeIf { it == "+" || it.last() != '+' }
    val res = replace.joinToString(separator = "").split("+").toMutableList()
    res.removeIf { it == "" }
    for (i in 0 until res.size)
        if (res[i].toInt() > max)
            max = res[i].toInt()

    return max


}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    try {
        val replace = Regex("""[ ]""").replace(expression, " ").split(" ").toMutableList()
        replace.removeIf { it.isEmpty() }
        if (replace[0].startsWith('+') || replace[0].startsWith('-'))
            throw IllegalArgumentException()
        var counter = 0
        var counter2 = 0
        var sum = 0
        for (element in replace) {
            if (element == "-")
                counter++
            if (element == "+")
                counter2++
        }
        for (i in 0 until replace.size - counter2)
            if (replace[i] == "+") {
                replace[i] += replace[i + 1]
                replace.remove(replace[i + 1])
            }
        for (i in 0 until replace.size - counter)
            if (replace[i] == "-") {
                replace[i] += replace[i + 1]
                replace.remove(replace[i + 1])
            }
        for (element in replace)
            sum += element.toInt()
        return sum
    } catch (exp: Exception) {
        throw IllegalArgumentException()
    }
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val a = str.split(" ")
    var res = 0
    var counter = 0
    var sum = 0
    var length = 0
    for (i in 0 until a.size - 1) {
        length = a[i].length
        sum += length
        if (a[i].toLowerCase() == a[i + 1].toLowerCase()) {
            counter++
            res = (sum + i) - length
            break
        }
    }
    return if (counter > 0) res
    else -1

}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    try {
        val a = description.split(";")
        var c = listOf<String>()
        var cost = " "
        var name = " "
        var maxcost = -1.0
        for (i in 0 until a.size) {
            c = a[i].split(" ").toMutableList()
            c.removeIf { it.isEmpty() }
            if (c[1].toDouble() > maxcost) {
                maxcost = c[1].toDouble()
                name = c[0]
            }
        }
        return name
    } catch (exp: Exception) {
        return ""
    }
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int = TODO()

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    val a = Regex("""[^ +\-><\[\]]""")
    if (a.findAll(commands, 0).toMutableList() != emptyList<MatchResult>())
        throw IllegalArgumentException()
    var countOfClose = 0
    var countOfOpen = 0
    var openindex = 0
    var closeindex = 0
    for (i in 0 until commands.length) {
        if (commands[i] == '[')
            countOfOpen++
        if (commands[i] == ']')
            countOfClose++
    }
    if (countOfClose != countOfOpen)
        throw IllegalArgumentException()

    var res = MutableList(cells) { 0 }
    var position = 0
    position = if (cells > 2)
        cells / 2
    else 1

    var i = 0
    var c = 0
    val listofidexes = mutableListOf<Int>()
    while ((i < commands.length) && (c < limit)) {
        when (commands[i]) {
            '+' -> res[position]++
            '-' -> res[position]--
            '>' -> {
                position++
                if (position > cells)
                    throw IllegalStateException()
            }
            '<' -> {
                position--
                if (position < 0)
                    throw IllegalStateException()
            }
            '[' -> if (res[position] == 0) {
                openindex = i
                closeindex = findindex(openindex, commands)
                i = closeindex
            } else listofidexes += i
            ']' -> if (res[position] != 0)
                i = listofidexes.last()
            else listofidexes -= listofidexes.last()
        }
        i++
        c++
    }

    return res

}

fun findindex(openindex: Int, str: String): Int {
    var a = openindex + 1
    var counter = 0
    while (a < str.length) {
        if (str[a] == '[')
            counter++
        if (str[a] == ']') {
            if (counter != 0)
                counter--
            else break
        }
        a++
    }
    return a
}


