/**
 * This interface checks if the column is free and than it checks if the token place is a horizontally,
 * vertically, diagonal win, or tie. This interface does not place the token or print the board.
 *
 * Defines: Row: Z
 *          Column: Z
 *          Win: Z
 *
 * Constraints: MIN <= row <= ROWNUM
 *              MIN <= column <= COLNUM
 *              MIN <= win <= WINNUM
 *
 * Initialization Ensures: Row = row and Column = column and Win = win and board[][] = board[Row][Column]
 *                          and board[Row][Column] = ' '
 */

public interface IGameBoard {

    public static final int ROWNUM = 100;
    public static final int COLNUM = 100;
    public static final int WINNUM = 25;
    public static final int MIN = 3;
    /**
    * @pre
    * MIN <= row <= ROWNUM
    *
    * @return
    * the number of rows
    */
    int getNumRows();

    /**
     * @pre
     * MIN <= column <= COLNUM
     *
     * @return
     * the number of columns
     */
    int getNumColumns();

    /**
     * @pre
     * MIN <= win <= COLNUM
     *
     * @return
     * the number of win
     */
    int getNumToWin();

    /**
    *
    * @param p is the token('X' or 'O');
    * @param c is the number of column
    *
    * @pre
    * checkIfFree must be called before the function
    *
    * @post
    * The token will be placed in the lowest available row in column c
    */
    void placeToken(char p, int c);

    /**
    *
    * @param r is row number
    * @param c is column number
    *
    * @pre 0 <= c <= getNumColumns()
    *      0 <= r <= getNumRows()
    * @post no variables are changed
    *
    * @return the char that is in in row r and column c of the game board
    */
    char whatsAtPos(int r, int c);


    /**
     * @param c is the number of columns
     *
     * @pre 0 <= c <= getNumColumns()
     *
     * @post no variables are changed in this function
     *
     * @return returns true if column c is not full, false if column c is full
     */
    default boolean checkIfFree(int c) {
        if (c >= 0 && c < getNumColumns()) {
            for (int i = 0; i < getNumRows(); i++) {
                if (whatsAtPos(i, c) == ' ') {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param c is the number of column
     *
     * @pre placeToken must be called before this function is called
     *      0 <= c <= getNumColumns()
     *
     * @post calls checkHorizWin, checkVertWin, and checkDiagWin functions for each player
     *
     * @return true if the last token placed is a winner move, if not return false
     */
    default boolean checkForWin(int c) {

        int i = 0;
        int rowPos = 0;
        char token;

        //To get the token in the last position placed
        while (i >= 0 && i < getNumRows()) {
            if (whatsAtPos(i, c) != ' ')
                i++;
            else {
                rowPos = i-1;
                break;
            }
        }

        token = whatsAtPos(rowPos, c);

        return(checkHorizWin(rowPos, c, token)|| checkVertWin(rowPos, c, token) || checkDiagWin(rowPos, c, token));
    }

    /**
    * @param r is the row number
    * @param c is column number
    * @param p is the token
    *
    * @pre p is either O or X
    *      0 <= c <= getNumColumns()
    *      0 <= r <= getNumRows()
    * @post Compare the number of counts to the win variable and determine if there is a win
    *
    * @return true if the last token placed resulted the player getting a horizontally win, else false
    */
    default boolean checkHorizWin(int r, int c, char p) {
        int count = 1;
        //checks the columns after the last token placed
        for (int currentCol = c + 1; currentCol < getNumColumns(); currentCol++) {
            if (whatsAtPos(r, currentCol) != p) {
                break;
            } else {
                count++;
            }
        }

        //checks the columns before the last token placed
        for (int currentCol = c - 1; currentCol >= 0; currentCol--) {
            if (whatsAtPos(r, currentCol) != p) {
                break;
            } else {
                count++;
            }
        }

        return (count >= getNumToWin());
    }

    /**
    * @param r is the row number
    * @param c is column number
    * @param p is the token
    *
    * @pre p is either O or X
    *      0 <= c <= getNumColumns()
    *      0 <= r <= getNumRows()
    *
    * @post Compare the number of counts to the win variable and determine if there is a win
    *
    * @return true if the last token placed resulted the player getting 4 vertically, else false
    */
    default boolean checkVertWin(int r, int c, char p) {
        int count = 1;

        //Checks above the last token placed in the column
        for (int currentRow = r + 1; currentRow < getNumRows(); currentRow++) {
            if (whatsAtPos(currentRow, c) != p) {
                break;
            } else {
                count++;
            }
        }

        //Checks below the last token placed in the column
        for (int currentRow = r - 1; currentRow >= 0; currentRow--) {
            if (whatsAtPos(currentRow, c) != p) {
                break;
            } else {
                count++;
            }
        }

        return (count >= getNumToWin());
    }

    /**
    * @param r the row number
    * @param c the column number
    * @param p the token
    *
    * @pre p is either O or X
    *      0 <= c <= getNumColumns()
    *      0 <= r <= getNumRows()
    * @post Compare the number of counter to the win variable and determine if there is a win
    *
    * @return true if the last token placed resulted in the player getting 4 diagonally, else false
    */
   default boolean checkDiagWin(int r, int c, char p) {
        int rowcheck, colcheck;
        int counter = 1;

        //Checks for diagonal that are left to right
        //Must check the row and column at the same time
        for (rowcheck = r - 1, colcheck = c - 1; rowcheck >= 0 && colcheck >= 0; rowcheck--, colcheck--) {
            if (whatsAtPos(rowcheck, colcheck) != p) {
                break;
            } else
                counter++;
        }

        for (rowcheck = r + 1, colcheck = c + 1; rowcheck < getNumRows() && colcheck < getNumColumns(); rowcheck++, colcheck++) {
            if (whatsAtPos(rowcheck, colcheck) != p) {
                break;
            } else
                counter++;
        }
        if(counter >= getNumToWin())
            return true;

        counter = 1;

        //Checks for diagonal that right to left
        //Must check the row and column at the same time
        for (rowcheck = r - 1, colcheck = c +1; rowcheck >= 0 && colcheck < getNumColumns(); rowcheck--, colcheck++) {
            if (whatsAtPos(rowcheck, colcheck) != p) {
                break;
            } else
                counter++;
        }

        for (rowcheck = r + 1, colcheck = c - 1; rowcheck < getNumRows() && colcheck >= 0; rowcheck++, colcheck--) {
            if (whatsAtPos(rowcheck, colcheck) != p) {
                break;
            } else
                counter++;
        }

        return (counter >= getNumToWin());
    }

   /**
   *
   * @pre Must call the placeToken function before this function
   *
   * @post no variables are changed in the function
   *
   * @return true if the game board results in a tie game, else false
   *
   */
   default boolean checkTie() {

        //Checks each row and column if it has a token
        int marker = 0;
        for(int i = 0; i < getNumRows(); i++){
            for (int j = 0; j < getNumColumns(); j++) {
               if (whatsAtPos(i, j) == ' ') {
                   return false;
               }
               else
               marker++;
            }
        }

        //Size is the size of the board
        int size = (getNumRows() * getNumColumns());
        return(marker == size);
   }
}