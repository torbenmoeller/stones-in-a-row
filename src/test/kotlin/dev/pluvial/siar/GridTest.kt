package dev.pluvial.siar

fun main(args: Array<String>) {
    val g = Grid()
    g.dropTokenIntoColumn(0, Color.RED)
    g.dropTokenIntoColumn(0, Color.YELLOW)
    printGrid(g)
    g.dropTokenIntoColumn(1, Color.GREEN)
    g.dropTokenIntoColumn(1, Color.BLUE)
    printGrid(g)
    g.dropTokenIntoColumn(1, Color.RED)
    g.dropTokenIntoColumn(1, Color.YELLOW)
    printGrid(g)
}