import java.util.Arrays;
import java.util.List;

class Board implements Ilayout, Cloneable {

    private static final int dim = 3;
    private int board[][];

    public Board() {
        board = new int[dim][dim];
    }

    public Board(String str) throws IllegalStateException {
        if (str.length() != dim * dim) throw new
                IllegalStateException("Invalid arg in Board constructor");
        board = new int[dim][dim];
        int si = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                board[i][j] = Character.getNumericValue(str.charAt(si++));

    }

    @Override
    public List<Ilayout> children() {
        return null;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return false;
    }

    @Override
    public double getG() {
        return this.getG();
    }
    //... TO BE COMPLETED

    public String toString() {
        String a = "";
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 0)
                    a += " ";
                else
                    a+=String.valueOf(board[i][j]);
            }
            a += "\n";
        }
        return a;
    }

    public boolean equals(Object o) {
        // TO BE COMPLETED
        boolean a = true;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(this.board[i][j] != ((Board) o).board[i][j]) {
                    return false;
                }
            }
        }
        return a;
    }

    public int hashCode() {
        // TO BE COMPLETED
        return 0;
    }
}