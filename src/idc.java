import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class idc {
    public static void main (String [] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        BestFirst s = new BestFirst();

        Board b = new Board(sc.next());
        List<Ilayout> a = new ArrayList<>();
        a = b.children();
        for (Ilayout e : a) {
            System.out.println(e);
        }
    }
}