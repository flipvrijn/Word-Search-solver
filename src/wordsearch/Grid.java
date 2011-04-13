package wordsearch;

/**
 *
 * @author flipvanrijn
 */
public class Grid
{
    private char[][] grid;

    public Grid(char[][] grid)
    {
        this.grid = grid;
    }

    public int height()
    {
        return grid.length;
    }

    public int width()
    {
        return grid[0].length;
    }

    public char at(int x, int y)
    {
        if (x >= 0 && x < width() && y >= 0 && y < height())
            return grid[y][x];

        return Character.UNASSIGNED;
    }
}
