package wordsearch;

import java.util.*;

/**
 * Class which contains all heuristics to solve a word search puzzle.
 * @author Flip van Rijn
 */
public class Solver
{
    private Grid grid;
    private String[] words;
    private Map<String, Coord> solutions = new HashMap<String, Coord>();

    /**
     * Class for the coordinates of the begin and end position of a word.
     */
    public class Coord
    {
        public int startX, startY, endX, endY;
        public Coord(int startX, int startY, int endX, int endY)
        {
            this.startX = startX;
            this.startY = startY;
            this.endX = endX;
            this.endY = endY;
        }

        /**
         * Basic toString method to show the coordinates on screen.
         * @return String
         */
        @Override
        public String toString()
        {
            StringBuilder string = new StringBuilder();

            string.append(startX).append("x").append(startY).append(" to ");
            string.append(endX).append("x").append(endY);

            return string.toString();
        }
    }

    /**
     * Constructor which sets the grid and the words to look for.
     * @param grid The grid with characters
     * @param words The words
     */
    public Solver(char[][] grid, String[] words)
    {
        this.grid = new Grid(grid);
        this.words = words;
    }

    /**
     * Method which searches for each word in the grid.
     */
    public void solve()
    {
        for (String word : words)
            findWord(word);
    }

    /**
     * Method which searches the given word in the grid with characters, by
     * going by each character in the grid and look if it matches the first
     * letter. If that is the case, the method continues to expand in
     * each direction.
     * @param word The word
     */
    private void findWord(String word)
    {
        char firstLetter = word.charAt(0);

        for (int y = 0; y < grid.height(); y++)
        {
            for (int x = 0; x < grid.width(); x++)
            {
                if (grid.at(x, y) == firstLetter)
                {
                    if (this.checkLeft(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x - word.length() + 1), y));
                    else if (this.checkRight(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x + word.length() - 1), y));
                    else if (this.checkDown(x, y, word))
                        solutions.put(word, this.new Coord(x, y, x, (y + word.length() - 1)));
                    else if (this.checkUp(x, y, word))
                        solutions.put(word, this.new Coord(x, y, x, (y - word.length() + 1)));
                    else if (this.checkLeftUp(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x - word.length() + 1), (y - word.length() + 1)));
                    else if (this.checkRightDown(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x + word.length() - 1), (y + word.length() - 1)));
                    else if (this.checkLeftDown(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x - word.length() + 1), (y + word.length() - 1)));
                    else if (this.checkRightUp(x, y, word))
                        solutions.put(word, this.new Coord(x, y, (x + word.length() - 1), (y - word.length() + 1)));
                }
            }
        }
    }

    /**
     * Expands to the left if the word is in that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkLeft(int x, int y, String word)
    {
        if ((x + 1) - word.length() < 0)
            return false;

        int index = x;
        for (char letter : word.toCharArray())
        {
            if (grid.at(index, y) != letter)
                return false;
            index--;
        }

        return true;
    }

    /**
     * Expands to the right if the word is in that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkRight(int x, int y, String word)
    {
        if (x + word.length() > grid.width())
            return false;

        int index = x;
        for (char letter : word.toCharArray())
        {
            if (grid.at(index, y) != letter)
                return false;
            index++;
        }

        return true;
    }

    /**
     * Expands downwards if the word is in that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkDown(int x, int y, String word)
    {
        if (y + word.length() > grid.height())
            return false;

        int index = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(x, index) != letter)
                return false;
            index++;
        }

        return true;
    }

    /**
     * Expands upwards if the word is in that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkUp(int x, int y, String word)
    {
        if ((y + 1) - word.length() < 0)
            return false;

        int index = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(x, index) != letter)
                return false;
            index--;
        }

        return true;
    }

    /**
     * Expands to the diagonal to left-upwards (\) if the word is in
     * that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkLeftUp(int x, int y, String word)
    {
        if ((y + 1) - word.length() < 0 || (x + 1) - word.length() < 0)
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX--;
            indexY--;
        }

        return true;
    }

    /**
     * Expands to the diagonal to right-downwards (\) if the word is in
     * that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkRightDown(int x, int y, String word)
    {
        if (y + word.length() > grid.height() || x + word.length() > grid.width())
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX++;
            indexY++;
        }

        return true;
    }

    /**
     * Expands to the diagonal to left-downwards (/) if the word is in
     * that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkLeftDown(int x, int y, String word)
    {
        if (y + word.length() > grid.height() || (x + 1) - word.length() < 0)
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX--;
            indexY++;
        }

        return true;
    }

    /**
     * Expands to the diagonal to right-upwards (/) if the word is in
     * that direction.
     * @param x The column
     * @param y The row
     * @param word The word
     * @return boolean
     */
    private boolean checkRightUp(int x, int y, String word)
    {
        if ((y + 1) - word.length() < 0 || x + word.length() > grid.width())
            return false;

        int indexX = x, indexY = y;
        for (char letter : word.toCharArray())
        {
            if (grid.at(indexX, indexY) != letter)
                return false;
            indexX++;
            indexY--;
        }

        return true;
    }

    /**
     * A basic toString function to show the grid and the words along with
     * their start- and end-coordinates on the screen.
     * @return String
     */
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("Words: ");

        for (String word : words)
            output.append(word).append(" ");

        output.append("\n\n");

        for (int y = 0; y < grid.height(); y++)
        {
            for (int x = 0; x < grid.width(); x++)
                output.append(grid.at(x, y)).append(" ");
            output.append("\n");
        }

        Set set = solutions.entrySet();
        Iterator it = set.iterator();

        while (it.hasNext())
        {
            Map.Entry entry = (Map.Entry) it.next();

            Coord coord = (Coord) entry.getValue();
            output.append(entry.getKey()).append(": ").append(coord.toString());
            output.append("\n");
        }

        return output.toString();
    }
}
