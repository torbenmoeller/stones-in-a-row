package dev.pluvial.siar

class Grid {

    /**
     * 7 columns, 6 rows
     * array of columns, each column is an array of colors
     */
    private var grid = Array(7) { arrayOfNulls<Color>(6) }

    /**
     * drop Token into column
     */
    fun dropTokenIntoColumn(column: Int, token: Color): Int {
        isColumnFull(column)
        for (row in 0..5) {
            if (grid[column][row] == null) {
                grid[column][row] = token
                return row
            }
        }
        throw RuntimeException("Column is full")
    }

    fun isColumnFull(column: Int): Boolean {
        return grid[column][0] != null
    }

    fun getCell(column: Int, row: Int): Color? {
        return grid[column][row]
    }

    fun getHeight(): Int {
        return grid[0].size
    }

    fun getWidth(): Int {
        return grid.size
    }

}
