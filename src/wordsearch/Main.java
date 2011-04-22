package wordsearch;

/**
 * Small word search solver.
 * @author Flip van Rijn
 */
public class Main
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        char[][] grid = {
            {'v', 'n', 'y', 'b', 'k', 'g', 's'},
            {'r', 'o', 'r', 'a', 'n', 'g', 'e'},
            {'e', 't', 'r', 'n', 'x', 'w', 'p'},
            {'l', 'a', 'e', 'a', 'l', 'k', 'a'},
            {'p', 'm', 'h', 'n', 'w', 'm', 'r'},
            {'p', 'o', 'c', 'a', 'x', 'b', 'g'},
            {'a', 't', 'n', 'o', 'm', 'e', 'l'}
        };

        String[] words = {
            "apple", "banana", "cherry", "grapes",
            "lemon", "orange", "tomato", "ham"
        };

        Solver solver = new Solver(grid, words);
        solver.solve();

        System.out.println(solver);
    }

}
