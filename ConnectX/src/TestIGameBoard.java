import org.junit.Test;
import static org.junit.Assert.*;


public class TestIGameBoard {
    private final double EPSLION = .01;


    @Test
    public void MinConstruct() {
        int expectedRows = 3;
        int expectedColumns = 3;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);

        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);
        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(expectedRows, gb.getNumRows(), EPSLION);
        assertEquals(expectedColumns, gb.getNumColumns(), EPSLION);
        assertEquals(expectedWins, gb.getNumToWin(), EPSLION);
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void MaxConstruct() {
        int expectedRows = 100;
        int expectedColumns = 100;
        int expectedWins = 25;

        char[][] board = testClearBoard(expectedRows, expectedColumns);

        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);
        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(expectedRows, gb.getNumRows(), EPSLION);
        assertEquals(expectedColumns, gb.getNumColumns(), EPSLION);
        assertEquals(expectedWins, gb.getNumToWin(), EPSLION);
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void BasicGameConstruct() {
        int expectedRows = 6;
        int expectedColumns = 7;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);

        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);
        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(expectedRows, gb.getNumRows(), EPSLION);
        assertEquals(expectedColumns, gb.getNumColumns(), EPSLION);
        assertEquals(expectedWins, gb.getNumToWin(), EPSLION);
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckIfFree_FULL() {
        int expectedRows = 5;
        int expectedColumns = 5;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);

        board[0][1] = 'O';
        gb.placeToken('O', 1);

        board[1][0] = 'X';
        gb.placeToken('X', 0);

        board[0][2] = 'O';
        gb.placeToken('O', 2);

        board[1][1] = 'X';
        gb.placeToken('X', 1);

        board[2][0] = 'O';
        gb.placeToken('O', 0);

        board[2][1] = 'X';
        gb.placeToken('X', 1);

        board[3][0] = 'O';
        gb.placeToken('O', 0);

        board[4][0] = 'X';
        gb.placeToken('X', 0);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false, gb.checkIfFree(0));
        assertEquals(TestString, gb.toString());

    }

    @Test
    public void CheckIfFree_NotFull() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true, gb.checkIfFree(1));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckIfFree_OneFullOneNot() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);
        board[2][0] = 'X';
        gb.placeToken('X', 0);
        board[3][0] = 'O';
        gb.placeToken('O', 0);

        board[0][3] = 'X';
        gb.placeToken('X', 3);
        board[1][3] = 'O';
        gb.placeToken('O', 3);
        board[2][3] = 'X';
        gb.placeToken('X', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true, gb.checkIfFree(3));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckHoriz_BottomRow() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O', 1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true, gb.checkHorizWin(0,2,'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckHoriz_TopRow() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O', 1);
        board[2][1] = 'X';
        gb.placeToken('X',1);
        board[3][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'O';
        gb.placeToken('O', 2);
        board[1][2] = 'X';
        gb.placeToken('X', 2);
        board[2][2] = 'O';
        gb.placeToken('O',2);
        board[3][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'X';
        gb.placeToken('X', 3);
        board[1][3] = 'O';
        gb.placeToken('O', 3);
        board[2][3] = 'X';
        gb.placeToken('X',3);
        board[3][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkHorizWin(3,3,'O'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckHoriz_MiddleWin() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);
        board[1][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkHorizWin(0,1,'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckHoriz_NoWin() {
        int expectedRows = 4;
        int expectedColumns = 5;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'X';
        gb.placeToken('X',1);
        board[1][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);

        board[0][4] = 'X';
        gb.placeToken('X',4);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkHorizWin(0,3,'O'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckHoriz_Wins() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);
        board[1][0] = 'O';
        gb.placeToken('O',0);
        board[2][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);
        board[1][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);
        board[2][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkHorizWin(1,1,'O'));
        assertEquals(TestString, gb.toString());
    }

        @Test
        public void CheckVert_Routine() {
            int expectedRows = 4;
            int expectedColumns = 5;
            int expectedWins = 3;

            char[][] board = testClearBoard(expectedRows, expectedColumns);
            IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

            board[0][1] = 'O';
            gb.placeToken('O',1);

            board[0][2] = 'X';
            gb.placeToken('X',2);
            board[1][2] = 'X';
            gb.placeToken('X',2);
            board[2][2] = 'X';
            gb.placeToken('X',2);

            board[0][3] = 'O';
            gb.placeToken('O',3);

            String TestString = testToString(expectedRows, expectedColumns, board);

            assertEquals(true,gb.checkVertWin(2,2, 'X'));
            assertEquals(TestString, gb.toString());
        }

    @Test
    public void CheckVert_NotinCol() {
        int expectedRows = 4;
        int expectedColumns = 5;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);
        board[1][1] = 'O';
        gb.placeToken('O',1);
        board[2][1] = 'O';
        gb.placeToken('O',1);
        board[3][1] = 'X';
        gb.placeToken('X',1);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkVertWin(2,2, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckVert_Win() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'O';
        gb.placeToken('O',0);
        board[1][0] = 'O';
        gb.placeToken('O',0);
        board[2][0] = 'X';
        gb.placeToken('X',0);
        board[3][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);
        board[2][1] = 'X';
        gb.placeToken('X',1);
        board[3][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'X';
        gb.placeToken('X',2);
        board[2][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkVertWin(3,1, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckVert_CheckRowNotCol() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);
        board[1][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkVertWin(0,3, 'O'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckVert_MidWin() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);
        board[1][0] = 'O';
        gb.placeToken('O',0);
        board[2][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);


        board[0][2] = 'O';
        gb.placeToken('O',2);
        board[1][2] = 'X';
        gb.placeToken('X',2);
        board[2][2] = 'X';
        gb.placeToken('X',2);
        board[3][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkVertWin(3,2, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_Ascending() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);
        board[1][2] = 'X';
        gb.placeToken('X',2);
        board[2][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(2,2, 'X'));
        assertEquals(TestString, gb.toString());
    }


    @Test
    public void CheckDiag_Descending() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'X';
        gb.placeToken('X',1);
        board[2][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(2,1, 'O'));
        assertEquals(TestString, gb.toString());
    }


    @Test
    public void CheckDiag_MidPlace() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O',0);
        board[2][0] = 'X';
        gb.placeToken('X',0);
        board[3][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(1,1, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_NoWin() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);
        board[2][2] = 'X';
        gb.placeToken('X', 2);

        board[0][3] = 'O';
        gb.placeToken('O',3);


        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkDiagWin(1,1, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_MidtoTop() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'X';
        gb.placeToken('X',1);
        board[1][1] = 'O';
        gb.placeToken('O',1);
        board[2][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);
        board[2][2] = 'O';
        gb.placeToken('O',2);
        board[3][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);
        board[1][3] = 'X';
        gb.placeToken('X',3);
        board[2][3] = 'O';
        gb.placeToken('O',3);
        board[3][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(3,3, 'O'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_wholeBoard() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 4;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O',0);
        board[2][0] = 'O';
        gb.placeToken('O',0);
        board[3][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O',1);
        board[2][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'X';
        gb.placeToken('X', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(0,3, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_MiddleWin() {
        int expectedRows = 5;
        int expectedColumns = 5;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'O';
        gb.placeToken('O', 0);
        board[1][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'O';
        gb.placeToken('O',2);
        board[2][2] = 'X';
        gb.placeToken('X', 2);

        board[0][3] = 'O';
        gb.placeToken('O', 3);
        board[1][3] = 'X';
        gb.placeToken('X', 3);
        board[2][3] = 'O';
        gb.placeToken('O',3);
        board[3][3] = 'X';
        gb.placeToken('X', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true,gb.checkDiagWin(2,2, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckDiag_EndO() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O',1);
        board[2][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'O';
        gb.placeToken('O', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false,gb.checkDiagWin(2,1, 'X'));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckTie_Tie() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 5;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);
        board[2][0] = 'X';
        gb.placeToken('X', 0);
        board[3][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O', 1);
        board[2][1] = 'X';
        gb.placeToken('X', 1);
        board[3][1] = 'O';
        gb.placeToken('O', 1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'O';
        gb.placeToken('O', 2);
        board[2][2] = 'X';
        gb.placeToken('X', 2);
        board[3][2] = 'O';
        gb.placeToken('O', 2);

        board[0][3] = 'X';
        gb.placeToken('X', 3);
        board[1][3] = 'O';
        gb.placeToken('O', 3);
        board[2][3] = 'X';
        gb.placeToken('X', 3);
        board[3][3] = 'O';
        gb.placeToken('O', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(true, gb.checkTie());
        assertEquals(TestString, gb.toString());
    }



    @Test
    public void CheckTie_RowFull() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 5;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);

        board[0][3] = 'X';
        gb.placeToken('X', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false, gb.checkTie());
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckTie_ColFull() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 5;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'O';
        gb.placeToken('O', 2);
        board[2][2] = 'X';
        gb.placeToken('X', 2);
        board[3][2] = 'O';
        gb.placeToken('O', 2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false, gb.checkTie());
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void CheckTie_BoardNotFull() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 5;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X', 0);
        board[1][0] = 'O';
        gb.placeToken('O', 0);
        board[2][0] = 'X';
        gb.placeToken('X', 0);
        board[3][0] = 'O';
        gb.placeToken('O', 0);

        board[0][1] = 'X';
        gb.placeToken('X', 1);
        board[1][1] = 'O';
        gb.placeToken('O', 1);
        board[2][1] = 'X';
        gb.placeToken('X', 1);
        board[3][1] = 'O';
        gb.placeToken('O', 1);

        board[0][2] = 'X';
        gb.placeToken('X', 2);
        board[1][2] = 'O';
        gb.placeToken('O', 2);
        board[2][2] = 'X';
        gb.placeToken('X', 2);

        board[0][3] = 'X';
        gb.placeToken('X', 3);
        board[1][3] = 'O';
        gb.placeToken('O', 3);
        board[2][3] = 'X';
        gb.placeToken('X', 3);
        board[3][3] = 'O';
        gb.placeToken('O', 3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(false, gb.checkTie());
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_blank() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals(' ', gb.whatsAtPos(0,3));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_onTop() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('X', gb.whatsAtPos(1,1));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_LeftBottom() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('X', gb.whatsAtPos(0,0));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_RightBottom() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('O', gb.whatsAtPos(0,3));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_RightTop() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);

        board[0][3] = 'O';
        gb.placeToken('O',3);
        board[1][3] = 'X';
        gb.placeToken('X',3);
        board[2][3] = 'O';
        gb.placeToken('O',3);
        board[3][3] = 'X';
        gb.placeToken('X',3);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('X', gb.whatsAtPos(3,3));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_LeftTop() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'O';
        gb.placeToken('O',0);
        board[1][0] = 'X';
        gb.placeToken('X',0);
        board[2][0] = 'X';
        gb.placeToken('X',0);
        board[3][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);
        board[1][1] = 'O';
        gb.placeToken('O',1);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('O', gb.whatsAtPos(3,0));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void WhatsAtPos_MidToken() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][1] = 'O';
        gb.placeToken('O',1);
        board[1][1] = 'X';
        gb.placeToken('X',1);
        board[2][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);
        board[1][2] = 'O';
        gb.placeToken('O',2);

        String TestString = testToString(expectedRows, expectedColumns, board);

        assertEquals('O', gb.whatsAtPos(0,1));
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void PlaceToken_Empty() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'O';
        gb.placeToken('O',1);

        String TestString = testToString(expectedRows, expectedColumns, board);
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void PlaceToken_TokenOnTop() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'O';
        gb.placeToken('O',0);

        board[0][1] = 'X';
        gb.placeToken('X',1);

        board[0][2] = 'O';
        gb.placeToken('O',2);

        board[1][2] = 'X';
        gb.placeToken('X',2);

        String TestString = testToString(expectedRows, expectedColumns, board);
        assertEquals(TestString, gb.toString());
    }

    @Test
    public void PlaceToken_TokenInMid() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][0] = 'X';
        gb.placeToken('X',0);

        board[0][2] = 'O';
        gb.placeToken('O',2);

        board[0][1] = 'X';
        gb.placeToken('X',1);

        String TestString = testToString(expectedRows, expectedColumns, board);
        assertEquals(TestString, gb.toString());
    }


    @Test
    public void PlaceToken_LeftSide() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        board[0][0] = 'O';
        gb.placeToken('O',0);

        String TestString = testToString(expectedRows, expectedColumns, board);
        assertEquals(TestString, gb.toString());
    }


    @Test
    public void PlaceToken_RightSide() {
        int expectedRows = 4;
        int expectedColumns = 4;
        int expectedWins = 3;

        char[][] board = testClearBoard(expectedRows, expectedColumns);
        IGameBoard gb = factory(expectedColumns, expectedRows, expectedWins);

        board[0][1] = 'O';
        gb.placeToken('O',1);

        board[0][2] = 'X';
        gb.placeToken('X',2);

        board[0][3] = 'X';
        gb.placeToken('X',3);

        String TestString = testToString(expectedRows, expectedColumns, board);
        assertEquals(TestString, gb.toString());
    }
    private IGameBoard factory(int r, int c, int win) {
        return new GameBoard(r, c, win);
    }

    private String testToString(int r, int c, char[][] b) {
        String str = "";
        int maxNum = 9;

        str += "| ";

        //Formats the numbers at the top of the grid
        for (int j = 0; j < c; j++) {
            if (j >= maxNum)
                str += j + "|";
            else
                str += j + "| ";
        }
        str += "\n";

        //Formats the lines to correlate with the number at the top
        for (int printLine = (r - 1); printLine >= 0; printLine--) {
            str += "|";

            for (int i = 0; i < c; i++) {
                str += b[printLine][i] + " |";
            }
            str += "\n";
        }
        return str;
    }

    private char[][] testClearBoard(int row, int col) {
        char[][] clearBoard = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                clearBoard[i][j] = ' ';
            }
        }
        return clearBoard;
    }
}




