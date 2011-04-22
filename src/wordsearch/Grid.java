package wordsearch;

/**
 * Class which represents the grid of letters.
 * @author Flip van Rijn
 */
public class Grid
{
    private char[][] grid;

    /**
     * Constructor which sets the grid.
     * @param grid The grid
     */
    public Grid(char[][] grid)
    {
        this.grid = grid;
    }

    /**
     * Gives the height of the grid.
     * @return height
     */
    public int height()
    {
        return grid.length;
    }

    /**
     * Gives the width of the grid.
     * @return width
     */
    public int width()
    {
        return grid[0].length;
    }

    /**
     * Gives the character at the given position.
     * @param x The column
     * @param y The row
     * @return character
     */
    public char at(int x, int y)
    {
        if (x >= 0 && x < width() && y >= 0 && y < height())
            return grid[y][x];

        return Character.UNASSIGNED;
    }
}
