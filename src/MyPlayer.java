import java.awt.*;
import java.util.ArrayList;

public class MyPlayer {
    public Chip[][] gameBoard;
    public int[] columns;
    int[] cols = new int[10];

    public ArrayList<Board> losingBoards = new ArrayList<>();
    public ArrayList<Board> winningBoards = new ArrayList<>();

    public MyPlayer() {
        columns = new int[10];

        /***
         * This code will run just once, when the game opens.
         * Add your code here.
         * Print all boards 3x3
         * Figure out best move (Steps)
         */
        BoardSetup();
        for (Board board : winningBoards) {
            System.out.println(board.boardState.get(0) + "" + board.boardState.get(1) + "" + board.boardState.get(2) +
                    "..." + board.move.x + "," + board.move.y);
        }
    }

    public Point move(Chip[][] pBoard) {

        System.out.println("MyPlayer Move");

        gameBoard = pBoard;
        checkChips(gameBoard);
        int column = 0;
        int row = 0;

        row = 1;
        column = 1;
        Point myMove = new Point(row, column);


        for(int x = 0; x <winningBoards.size();x++){
            if(winningBoards.get(x).boardState.get(0) ==cols[0] && winningBoards.get(x).boardState.get(1) ==cols[1] && winningBoards.get(x).boardState.get(2) == cols[2]){
                myMove = winningBoards.get(x).move;
            }
        }
        for(int x = 0; x <losingBoards.size();x++){
            if(losingBoards.get(x).boardState.get(0) ==cols[0] && losingBoards.get(x).boardState.get(1) ==cols[1] && losingBoards.get(x).boardState.get(2) == cols[2]){
                myMove = losingBoards.get(0).move;
            }
        }

        /***
         * This code will run each time the "MyPlayer" button is pressed.
         * Add your code to return the row and the column of the chip you want to take.
         * You'll be returning a data type called Point which consists of two integers.
         */


        System.out.println(myMove.x + "" + myMove.y);
//        myMove = new Point(row, column);
        return myMove;
    }

    public void checkChips(Chip[][] gameBoard){
        cols = new int[10];
        for (int c = 0; c < 10; c++){
            for (int r = 0; r < 10; r++){
                if (gameBoard[r][c].isAlive == true) {
                    cols[c]++;
                    //cols[c] = cols[c] + 1;
                }
            }
        }
    }
    public void BoardSetup() {
        losingBoards.add(new Board(1, 0, 0, new Point(0,0)));

        for (int a = 1; a <= 3; a++) {
            for (int b = 0; b <= a; b++) {
                for (int c = 0; c <= b; c++) {
                    int[] board = new int[10];
                    board[0] = a;
                    board[1] = b;
                    board[2] = c;
                    //System.out.println(board[0] + ", " + board[1] + ", " + board[2]);
                    System.out.println("The board is: " + board[0] + " " + board[1] + " " + board[2]);
                    System.out.println("Possible result board(s): ");
                    possibleResult(a, b, c);
                    //System.out.println("Best move: (" + "),(" + ")");
                    System.out.println(" ");

                }
            }
        }
    }

    public void possibleResult(int a, int b, int c) {

        boolean containsLoser = false;
        Point move = new Point(0,0);

        for (int c2 = c - 1; c2 >= 0; c2--) {
            int[] board2 = new int[10];
            board2[0] = a;
            board2[1] = b;
            board2[2] = c2;
            System.out.println(a + " " + b + " " + c2 + " | Move to get there: " + c2 + ", " + 2);

            for (Board loser : losingBoards) {
                if (loser.boardState.get(0) == a && loser.boardState.get(1) == b && loser.boardState.get(2) == c2) {
                    System.out.println("found losing board");
//                    losingBoards.add(new Board(a, b, c));
                    containsLoser = true;
                    move = new Point(c2,2);
                }
            }
        } // righthand column

        for (int b2 = b - 1; b2 >= 0; b2--) {
            int[] board3 = new int[10];
            board3[0] = a;
            board3[1] = b2;
            board3[2] = c;
            if (b2 < c) {
                System.out.println(a + " " + b2 + " " + b2 + " | Move to get there: " + b2 + ", " + 1);
                for (Board loser : losingBoards) {
                    if (loser.boardState.get(0) == a && loser.boardState.get(1) == b2 && loser.boardState.get(2) == b2) {
//                    losingBoards.add(new Board(a, b, c));
                        containsLoser = true;
                        move = new Point(b2, 1);
                    }
                }
            }
            else {
                System.out.println(a + " " + b2 + " " + c + " | Move to get there: " + b2 + ", " + 1);
                    for (Board loser : losingBoards) {
                        if (loser.boardState.get(0) == a && loser.boardState.get(1) == b2 && loser.boardState.get(2) == c) {
//                    losingBoards.add(new Board(a, b, c));
                            containsLoser = true;
                            move = new Point(b2, 1);
                        }
            }
            }

        } // middle column
        for (int a2 = a - 1; a2 >= 1; a2--) {
            int[] board4 = new int[10];
            board4[0] = a2;
            board4[1] = b;
            board4[2] = c;
            if (a2 < b && a2 < c) {
                System.out.println(a2 + " " + a2 + " " + a2 + " | Move to get there: " + a2 + ", " + 0);
                for (Board loser : losingBoards) {
                    if (loser.boardState.get(0) == a2 && loser.boardState.get(1) == a2 && loser.boardState.get(2) == a2) {
//                    losingBoards.add(new Board(a, b, c));
                        containsLoser = true;
                        move = new Point(a2, 0);
                    }
                }
            } else if (a2 < b) {
                System.out.println(a2 + " " + a2 + " " + c + " | Move to get there: " + a2 + ", " + 0);
                for (Board loser : losingBoards) {
                    if (loser.boardState.get(0) == a2 && loser.boardState.get(1) == a2 && loser.boardState.get(2) == c) {
//                    losingBoards.add(new Board(a, b, c));
                        containsLoser = true;
                        move = new Point(a2, 0);

                    }
                }
            } else {
                System.out.println(a2 + " " + b + " " + c + " | Move to get there: " + a2 + ", " + 0);
                for (Board loser : losingBoards) {
                    if (loser.boardState.get(0) == a2 && loser.boardState.get(1) == b && loser.boardState.get(2) == c) {
//                    losingBoards.add(new Board(a, b, c));
                        containsLoser = true;
                        move = new Point(a2, 0);

                    }
                }
            }
        } // lefthand column

        // if you found a losing board, the original board is a winner
        if (containsLoser == true) {
            System.out.println("Winner");
            winningBoards.add(new Board(a, b, c, move));
        }
        // if you only found winning boards, the original board is a loser
        else {
            System.out.println("Loser");
            losingBoards.add(new Board(a, b, c, move));
        }

    }
}
