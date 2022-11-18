import java.awt.*;
import java.util.ArrayList;

public class Board {
    public ArrayList<Integer> boardState = new ArrayList<>();
    public Point move;


    public Board(int a, int b, int c, Point pMove) {

        boardState.add(a);
        boardState.add(b);
        boardState.add(c);
        move = pMove;

    }
}