import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardRB implements Ilayout, Cloneable {
    private static final int dim = 5;
    private char board[][];
    private int spaceI;
    private int spaceJ;

    public BoardRB() {
        board = new char[dim][dim];
    }

    public BoardRB(String str) throws IllegalStateException {
        if (str.length() != (dim * dim) - 8) throw new
                IllegalStateException("Invalid arg in Board constructor");
        board = new char[dim][dim];
        int si = 0;
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                if (i <= 2 && j <= 2 || i >= 2 && j >= 2) {
                    board[i][j] = str.charAt(si++);
                } else {
                    board[i][j] = 'N';
                }
    }

    private BoardRB changePosition(int i, int j) {
        BoardRB nova = clone();

        char position = nova.board[spaceI + i][spaceJ + j];
        nova.board[spaceI + i][spaceJ + j] = 'E';
        nova.board[spaceI][spaceJ] = position;
        return nova;
    }

    private void findSpace() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board[i][j] == 'E') {
                    spaceI = i;
                    spaceJ = j;
                    return;
                }
            }
        }
    }

    private boolean validateMove(int i, int j) {
        return i >= 0 && i < dim && j >= 0 && j < dim;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> child = new ArrayList<>();
        findSpace();

        if (spaceI - 1 >= 0) { //TOP
            if (board[spaceI - 1][spaceJ] == 'B') {
                child.add(changePosition(-1, 0));
            } else if (spaceI - 2 >= 0 && board[spaceI - 2][spaceJ] == 'B') { //TOP 2
                child.add(changePosition(-2, 0));
            }
        }
        if (spaceJ - 1 >= 0) { //RIGHT
            if (board[spaceI][spaceJ - 1] == 'B') {
                child.add(changePosition(0, -1));
            } else if (spaceJ - 2 >= 0 && board[spaceI][spaceJ - 2] == 'B') { //RIGHT 2
                child.add(changePosition(0, -2));
            }
        }
        if (spaceJ + 1 < dim) { //DOWN
            if (board[spaceI][spaceJ + 1] == 'R') {
                child.add(changePosition(0, 1));
            } else if (spaceJ + 2 < dim && board[spaceI][spaceJ + 2] == 'R') { //DOWN 2
                child.add(changePosition(0, 2));
            }
        }
        if (spaceI + 1 < dim) { //LEFT
            if (board[spaceI + 1][spaceJ] == 'R') {
                child.add(changePosition(1, 0));
            } else if (spaceI + 2 < dim && board[spaceI + 2][spaceJ] == 'R') { //LEFT 2
                child.add(changePosition(2, 0));
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
        return 1;
    }

    public String toString() {
        StringBuilder a = new StringBuilder();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (board[i][j] == 'E' || board[i][j] == 'N')
                    a.append(" ");
                else
                    a.append(board[i][j]);
            }
            a.append("\n");
        }
        return a.toString();
    }

    public boolean equals(Object o) {
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (this.board[i][j] != ((BoardRB) o).board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(board);
        return result;
    }

    @Override
    public BoardRB clone() {
        BoardRB clone = new BoardRB();
        //clone.board = new char[dim][dim];
        for (int i = 0; i < dim; i++) {
            clone.board[i] = Arrays.copyOf(this.board[i], dim);
        }
        return clone;
    }
}
