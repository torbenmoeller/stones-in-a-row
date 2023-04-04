package dev.pluvial.siar

fun printGrid(grid: Grid) :String {
    val sb = StringBuilder()
    for (row in 5 downTo 0) {
        val rowString = StringBuilder()
        for (column in 0..6) {
            val cell = grid.getCell(column, row)
            rowString.append("${cell?.short ?: "x"} ")
        }
        sb.appendLine(rowString.toString())
    }
    return sb.toString()
}