package dev.pluvial.siar

class Grid(
    private val width: Int=7,
    private val height: Int=6
) {

    /**
     * 7 columns, 6 rows
     * array of columns, each column is an array of colors
     */
    private var grid = Array(width) { arrayOfNulls<DiscColor>(height) }

    /**
     * Drop token into column
     */
    fun dropTokenIntoColumn(column: Int, token: DiscColor): Int {
        isColumnFull(column)
        for (row in 0..5) {
            if (grid[column][row] == null) {
                grid[column][row] = token
                return row
            }
        }
        throw RuntimeException("Column is full")
    }

    /**
     * Checks the top spot of the column is empty.
     * Returns true if the column is full.
     */
    fun isColumnFull(column: Int): Boolean {
        return grid[column][0] != null
    }

    fun getCell(column: Int, row: Int): DiscColor? {
        return grid[column][row]
    }

    fun getHeight(): Int {
        return height
    }

    fun getWidth(): Int {
        return width
    }

}
