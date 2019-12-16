import java.util.*;

public class Connect4Game{
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        char again = ' '; //If the player type yes to play again the outer do while will restart the game
        do {
            int numOfPlayer = getNumPlayer();
            List<Character> playerToken = getTokenPlayer(numOfPlayer);

            //Ask the user to get the number of columns, rows, and wins
            int numOfRows = getRow();
            int numOfCols = getCol();
            int numOfWins = getWin();

            boolean winner = false; //become true when there is a winner
            boolean draw = false; //become true when there is a tie
            boolean stop = false; //To keep asking the user to go again if they enter a invalid input
            boolean repeat = false; //Switches from player X to O
            char token = ' ';
            int nextplayer = 0;

            char typeGame = getTypeGame();

            IGameBoard playerMove;
            if(typeGame == 'F' || typeGame == 'f') {
                  playerMove = new GameBoard(numOfCols, numOfRows, numOfWins);
            }else {
                playerMove  = new GameBoardMem(numOfCols, numOfRows, numOfWins);
            }

            while (!repeat) {
                System.out.println(playerMove.toString());

                //Rotates between the players
                if (nextplayer == numOfPlayer) {
                    nextplayer = 0;
                    token = playerToken.get(nextplayer);
                    nextplayer++;
                }
                else {
                    token = playerToken.get(nextplayer);
                    nextplayer ++;
                }
                do {
                    System.out.println("Player " + token + ", what column do you want to place your marker in?");
                    int x = keyboard.nextInt();

                    //This if statement will run if the column entered is between 0 and 6
                    if (x >= 0 && x <= (numOfCols-1)) {
                        //Checks to see if the column is full, if so it will ask the user to go again
                        if (!playerMove.checkIfFree(x)) {
                            System.out.println("Column is full");
                            stop = true;
                        } else {
                            //Place the token on the board
                            playerMove.placeToken(token, x);

                            //If the token is not a winning token than it will print out the current board
                            //and ask the next player to go
                            if (!playerMove.checkForWin(x) && !playerMove.checkTie()) {
                                winner = false;
                                draw = false;
                                stop = false;
                            }
                            //If the token is a winning token than the player will be aware
                            if (playerMove.checkForWin(x)) {
                                System.out.println(playerMove.toString());
                                System.out.println("Player " + token + " Won!");
                                winner = true;
                            }
                            //If the token is a tie token than the player will be aware
                            else if(playerMove.checkTie()) {
                                System.out.println(playerMove.toString());
                                System.out.println("The game is tied");
                                draw = true;
                            }
                        }
                    }
                    //If the column entered is below 0 this else if statement will run
                    else if (x < 0) {
                        System.out.println("Column cannot be less than 0");
                        stop = true;
                    }
                    //If the column entered is above the chosen column this else if statement will run
                    else if (x > (numOfCols -1)) {
                        System.out.println("Column cannot be greater than " + (numOfCols-1));
                        stop = true;
                    }
                } while (stop);

                //This while loop ask to play again
                while((winner == true || draw == true) && (again != 'N' || again != 'n'  || again != 'Y'  || again != 'y')){
                    System.out.println("Would you like to play again? Y/N");
                    again = keyboard.next().charAt(0);

                    if(again == 'N' || again == 'n'  || again == 'Y'  || again == 'y'){
                        repeat = true;
                        break;
                    }
                }
            }

        }while(again == 'Y' || again == 'y');
    }

    /**
     *@return the number of players, numPlayer
     *
     * @post 2 < numPlayer < 10
     */
    private static int getNumPlayer(){
        boolean introRepeatrow = true;
        int numPlayer = 0;
        int leastNum = 2;
        int mostNum = 10;

        //Keeps asking the user to enter a valid input
        while(introRepeatrow) {
            System.out.println("How many players?");
            Scanner intro = new Scanner(System.in);
            numPlayer = intro.nextInt();

            if (numPlayer < leastNum) {
                System.out.println("Must have at least 2 players.");
            } else if (numPlayer > mostNum) {
                System.out.println("Must be 10 players or fewer");
            }
            else{
                introRepeatrow = false;
            }
        }
        return numPlayer;
    }

    /**
     *
     * @param numOfPlayer number of players
     *
     * @pre 2 < numOfPlayer < 10
     *
     * @return a list of characters to represent the each player
     *
     * @post the list won't have two of the same tokens
     *
     */
    private static List<Character> getTokenPlayer(int numOfPlayer){
        char PlayerToken;
        List<Character> token = new ArrayList<>(numOfPlayer);
        boolean repeat = true;

        for(int i = 0; i < numOfPlayer; i++){
            while(repeat) {
                System.out.println("Enter the character to represent player " + (i + 1));
                Scanner intro = new Scanner(System.in);
                PlayerToken = intro.nextLine().toUpperCase().charAt(0);
                boolean repeatToken = token.contains(PlayerToken);

                if (repeatToken) {
                    System.out.println(PlayerToken + " is already taken as a player token!");
                    repeat = true;
                } else {
                    token.add(PlayerToken);
                    break;
                }
            }
        }
        return token;
    }

    /**
     *
     * @return a char to represent the type of game
     *
     * @post the gameType == F/f || gameType == M/m
     */

    private static char getTypeGame(){
        char gameType = ' ';

        while(gameType != 'F' || gameType != 'f'  || gameType != 'M'  || gameType != 'm'){
            System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
            Scanner intro = new Scanner(System.in);
            gameType = intro.next().charAt(0);

            if(gameType == 'F' || gameType == 'f'  || gameType == 'M'  || gameType == 'm'){
                break;
            }
            if(gameType != 'F' || gameType != 'f'  || gameType != 'M'  || gameType != 'm'){
                System.out.println("Please enter F or M");
            }
        }
        return gameType;
    }

    /**
     *
     * @return number of rows
     *
     * @post 3 <= rows <= 100
     */
    private static int getRow(){
        boolean introRepeatrow = true;
        int numOfRows = 0;
        int leastRow = 3;
        int mostRow = 100;

        //Keeps asking the user to enter a valid input
        while(introRepeatrow) {
            System.out.println("How many rows should be on the board?");
            Scanner intro = new Scanner(System.in);
            numOfRows = intro.nextInt();

            if (numOfRows < leastRow) {
                System.out.println("Must have at least 3 rows.");
            } else if (numOfRows > mostRow) {
                System.out.println("Can have at most 100 rows");
            }
            else{
                introRepeatrow = false;
            }
        }
        return numOfRows;
    }

    /**
     *
     * @return number of columns
     *
     * @post 3 <= columns <= 100
     */
    private static int getCol(){
        boolean introRepeatcol = true;
        int numOfCols = 0;
        int leastCol = 3;
        int mostCol = 100;

        //Keeps asking the user to enter a valid input
        while(introRepeatcol) {
            System.out.println("How many columns should be on the board?");
            Scanner intro = new Scanner(System.in);
            numOfCols = intro.nextInt();

            if (numOfCols < leastCol) {
                System.out.println("Must have at least 3 rows.");
            } else if (numOfCols > mostCol) {
                System.out.println("Can have at most 100 rows");
            }
            else{
                introRepeatcol = false;
            }
        }
        return numOfCols;
    }

    /**
     *
     * @return number of wins
     *
     * @post  3 <= wins <= 25
     */
    private static int getWin(){
        boolean introRepeatwin = true;
        int numOfWins = 0;
        int leastWin = 3;
        int mostWin = 25;
        //Keeps asking the user to enter a valid input
        while(introRepeatwin) {
            System.out.println("How many in a row to win?");
            Scanner intro = new Scanner(System.in);
            numOfWins = intro.nextInt();

            if(numOfWins < leastWin){
                System.out.println("Must have at least 3 in a row to win.");
            }
            else if(numOfWins > mostWin){
                System.out.println("Can have at most 25 in a row to win");
            }
            else{
                introRepeatwin = false;
            }
        }
        return numOfWins;
    }
}
