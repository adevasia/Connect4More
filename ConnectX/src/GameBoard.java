/**
 * Correspondence: row = getNumRow()
 *                 column = getNumCol()
 *                 win = getNumWin()
 *                 board[][] = [0...getNumRow()-1)][0...getNumCol()-1]
 *
 * @invariants
 * MIN <= row <= ROWNUM
 * MIN <= column <= COLNUM
 * MIN <= win <= WINNUM
 * board = must have char passed in not numbers
 */
public class GameBoard implements IGameBoard {
    private int row;
    private int column;
    private int win;
    private char[][] board;

    /**
     *
     * @param col is the number of columns
     * @param rows is the number of rows
     * @param wins is the number of wins
     *
     * @pre MIN <= col <= ROWNUM
     *      MIN <= rows <= COLNUM
     *      MIN <= wins <= WINNUM
     *
     * @post row = rows
     *       column = col
     *       win = wins
     *       board[][] = ' '
     */
    public GameBoard(int col, int rows, int wins) {
        row = rows;
        column = col;
        win = wins;

        board = new char[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void placeToken(char p, int c) {
        //placed the token at the bottom of the grid, rather than at the top
        for (int r = 0; r < row; r++) {
            if (whatsAtPos(r,c) == ' ') {
                board[r][c] = p;
                break;
            }
        }
    }

    public char whatsAtPos(int r, int c) {
        return board[r][c];
    }

    public int getNumRows(){
        return row;
    }

    public int getNumColumns(){
        return column;
    }

    public int getNumToWin(){
        return win;
    }

    /**
    * @return the current board game
    *
    * @post
    * The board game will represent a 2D array
    */
    @Override
    public String toString() {
        String str = "";
        int maxNum = 9;

        str += "| ";

        //Formats the numbers at the top of the grid
        for(int j = 0; j < column; j++){
            if(j >= maxNum)
                str += j + "|";
            else
                str += j + "| ";
        }
        str += "\n";

        //Formats the lines to correlate with the number at the top
        for(int printLine = (row-1); printLine >= 0; printLine--){
            str += "|";

            for(int i=0; i < column; i++){
                str += whatsAtPos(printLine, i) + " |";
            }
            str+= "\n";
        }
        return str;
    }
}