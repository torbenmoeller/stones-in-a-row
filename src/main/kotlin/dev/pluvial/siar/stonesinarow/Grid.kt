package dev.pluvial.siar.stonesinarow

class Grid {

    var grid = Array(7) { arrayOfNulls<Color>(6) }

    /**
     * drop Token into column
     */
    fun dropTokenIntoColumn(column: Int, token: Color) {
        isColumnFull(column)
        for (row in 0..5) {
            if (grid[column][row] == null) {
                grid[column][row] = token
                break
            }
        }
    }

    fun isColumnFull(column: Int): Boolean {
        return grid[column][0] != null
    }

    fun getCell(column: Int, row: Int): Color? {
        return grid[column][row]
    }

}
