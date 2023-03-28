package dev.pluvial.siar.stonesinarow

fun printGrid(grid: Grid) {
    println()
    for (row in 5 downTo 0) {
        for (column in 0..6) {
            var cell = grid.getCell(column, row)
            print("${cell?.short ?: "x"} ")
        }
        println()
    }
}