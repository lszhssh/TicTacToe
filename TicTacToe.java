/**
 * TODO: file header
 * 
 * Sources:
 * Checking for inappropriate user input: https://kodejava.org/how-do-i-validate-input-when-using-scanner/#:~:text=To%20validate%20input%20the%20Scanner,provide%20a%20positive%20integer%20number.
 * How to format text: https://www.javatpoint.com/java-string-format
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

/**
 * TODO: class header
 */
public class TicTacToe {
    String[][] board;

    private static final int DIMENSION = 3;
    private static final String EMPTY_SYMBOL = "-";
    private static final String PLAYER_SYMBOL = "x";
    private static final String COMPUTER_SYMBOL = "0";
    private static final String PLAYER_WON = "Player Won!";
    private static final String COMPUTER_WON = "Computer Won!";

    /**
     * Initializes an empty 3x3 board.
     */
    public TicTacToe() {
        this.board = new String[DIMENSION][DIMENSION];
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[0].length; c++) {
                this.board[r][c] = EMPTY_SYMBOL;
            }
        }
    }

    /**
     * Randomly chooses an empty spot as the computer's move
     * @return the concatenation of the row and column number of the move
     */
    public String getComputerMove() {
        ArrayList<Integer> availableRow = new ArrayList<>();
        ArrayList<Integer> availableCol = new ArrayList<>();
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[0].length; c++) {
                if (this.board[r][c].equals(EMPTY_SYMBOL)) {
                    availableRow.add(r);
                    availableCol.add(c);
                }
            }
        }
        int numAvailableSpots = availableRow.size();
        int randIdx = (int) Math.random() * numAvailableSpots;
        String row = Integer.toString(availableRow.get(randIdx));
        String col = Integer.toString(availableCol.get(randIdx));
        return row + col;
    }

    /**
     * Returns the winner of the game or null if no one has won yet
     * @return the winner of the game or null if no one has won yet
     * TODO: get rid of the magic numbers
     */
    public String getWinner() {
        for (int r = 0; r < this.board.length; r++) {
            if (this.board[r][0].equals(this.board[r][1]) && 
                    this.board[r][1].equals(this.board[r][2]) &&
                    !this.board[r][0].equals(EMPTY_SYMBOL)) {
                if (this.board[r][0].equals(PLAYER_SYMBOL)) {
                    return PLAYER_WON;
                } else {
                    return COMPUTER_WON;
                }
            }
        }
        for (int c = 0; c < this.board[0].length; c++) {
            if (this.board[0][c].equals(this.board[1][c]) && 
                    this.board[1][c].equals(this.board[2][c]) &&
                    !this.board[0][c].equals(EMPTY_SYMBOL)) {
                if (this.board[0][c].equals(PLAYER_SYMBOL)) {
                    return PLAYER_WON;
                } else {
                    return COMPUTER_WON;
                }
            }
        }
        if (this.board[0][0].equals(this.board[1][1]) && 
                this.board[1][1].equals(this.board[2][2]) &&
                !this.board[0][0].equals(EMPTY_SYMBOL)) {
            if (this.board[0][0].equals(PLAYER_SYMBOL)) {
                return PLAYER_WON;
            } else {
                return COMPUTER_WON;
            }        
        }
        if (this.board[0][2].equals(this.board[1][1]) && 
                this.board[1][1].equals(this.board[2][0]) &&
                !this.board[0][0].equals(EMPTY_SYMBOL)) {
            if (this.board[0][0].equals(PLAYER_SYMBOL)) {
                return PLAYER_WON;
            } else {
                return COMPUTER_WON;
            }        
        }
        return null;
    }

    /**
     * Returns a string representation of the
     * game board.
     * @return string representation of the
     * game board.
     */
    public String toString() {
        String board = "";
        for (int r = 0; r < this.board.length; r++) {
            for (int c = 0; c < this.board[0].length; c++) {
                board += this.board[r][c] + " ";
            }
            board += "\n";
        }
        return board;
    }

    /**
     * Plays the game with helper methods.
     * TODO: does main() need a documentation?
     * @param args
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        boolean running = true;
        System.out.println(game.toString());
        while (running) {
            Scanner in = new Scanner(System.in);
            boolean moveComplete = false;
            while (!moveComplete) {
                System.out.println("Please enter the row number of your move: ");
                while (!in.hasNextInt()) {
                    // wrong way of comparing
                    if (in.nextInt() < 1 || in.nextInt() > DIMENSION) {
                        System.out.println("i ran");
                    }
                    String invalidRow = in.next();
                    System.out.printf("%s is not a valid row", invalidRow);
                }
                int row = in.nextInt();
                System.out.println("Please enter the column number of your move: ");
                while (!in.hasNextInt() || in.nextInt() < 1 || in.nextInt() > DIMENSION) {
                    String invalidRow = in.nextLine();
                    System.out.printf("%s is not a valid row", invalidRow);
                }
                int col = in.nextInt();
                if (!game.board[row][col].equals(EMPTY_SYMBOL)) {
                    System.out.println("Invalid move. Please try again");
                } else {
                    moveComplete = true;
                    game.board[row - 1][col - 1] = PLAYER_SYMBOL;
                }
            }
            System.out.println(game.toString());
            String computerMove = game.getComputerMove();
            int computerMoveRow = Integer.parseInt(computerMove.substring(0, 1));
            int computerMoveCol = Integer.parseInt(computerMove.substring(1, 2));
            System.out.println("The computer chose to move to (" + computerMoveRow + ", " + computerMoveCol + ").");
            game.board[computerMoveRow][computerMoveCol] = COMPUTER_SYMBOL;
            System.out.println(game.toString());
            String winner = game.getWinner();
            if (winner != null) {
                running = false;
                if (winner.equals(PLAYER_WON)) {
                    System.out.println(PLAYER_WON);
                } else {
                    System.out.println(COMPUTER_WON);
                }
            }
        }
    }
}