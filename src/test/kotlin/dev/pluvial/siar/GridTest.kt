package dev.pluvial.siar

fun main(args: Array<String>) {
    val g = Grid()
    g.dropTokenIntoColumn(0, DiscColor.RED)
    g.dropTokenIntoColumn(0, DiscColor.YELLOW)
    printGrid(g)
    g.dropTokenIntoColumn(1, DiscColor.GREEN)
    g.dropTokenIntoColumn(1, DiscColor.BLUE)
    printGrid(g)
    g.dropTokenIntoColumn(1, DiscColor.RED)
    g.dropTokenIntoColumn(1, DiscColor.YELLOW)
    printGrid(g)
}