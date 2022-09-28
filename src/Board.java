import java.util.ArrayList;
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

    private Board changePosition (Board b, int i, int j, int x, int y) {
        Board nova = new Board();
        for (int k = 0; k < dim; k++) {
            for (int l = 0; l < dim; l++) {
                nova.board[k][l] = b.board[k][l];
            }
        }
        int position = nova.board[j+y][i+x];
        nova.board[j+y][i+x] = 0;
        nova.board[j][i] = position;
        System.out.println(nova);
        return nova;
    }

    private int[] findzero (Board board) {
        int []a = new int[2];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board.board[i][j] == 0) {
                    a[0] = i;
                    a[1] = j;
                    return a;
                }
            }
        }
        return null;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> child = new ArrayList<>();
        int[] a = findzero(this);
        if (a != null) {
            if (a[0] - 1 >= 0 && a[0] - 1 < dim) {
                child.add(changePosition(this, a[1], a[0], -1, 0));
            }
            if (a[1] - 1 >= 0 && a[1] - 1 < dim) {
                child.add(changePosition(this, a[1], a[0], 0, -1));
            }
            if (a[0] + 1 >= 0 && a[0] + 1 < dim) {
                child.add(changePosition(this, a[1], a[0], 1, 0));
            }
            if (a[1] + 1 >= 0 && a[1] + 1 < dim) {
                child.add(changePosition(this, a[1], a[0], 0, 1));
            }
        }
        return child;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return equals(l);
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
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if(this.board[i][j] != ((Board) o).board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        // TO BE COMPLETED
        return 0;
    }
}