import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Correspondence: row = getNumRow()
 *                 column = getNumCol()
 *                 win = getNumWin()
 *                 boardMap = new HashMap<Character>
 *
 * @invariants
 * MIN <= row <= ROWNUM
 * MIN <= column <= COLNUM
 * MIN <= win <= WINNUM
 * boardMap = must have char passed in not numbers
 */
public class GameBoardMem implements IGameBoard {
    private int row;
    private int column;
    private int win;
    private Map<Integer, List<Character>> boardMap;

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
    *       boardMap.List == blank;
    */
    public GameBoardMem(int col, int rows, int wins) {
        row = rows;
        column = col;
        win = wins;
        boardMap = new HashMap<>();

        for(int i = 0; i < column; i++){
            List<Character> blankList =  new ArrayList<Character>();
            boardMap.put(i,blankList);
        }
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

    public void placeToken(char p, int c){
        boardMap.get(c).add(p);
    }

    public char whatsAtPos(int r, int c) {
        int size = boardMap.get(c).size();

        if (r < size) {
                return boardMap.get(c).get(r);
        }
        else
            return ' ';
    }

    /**
     * @return the current board game
     *
     * @post
     * The board game will represent a 2D map
     */
    public String toString(){
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
                str += whatsAtPos(printLine,i)+ " |";
            }
            str+= "\n";
        }
        return str;
    }
}
