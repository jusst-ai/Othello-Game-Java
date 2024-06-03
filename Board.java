import java.util.ArrayList;
import java.util.Scanner;



class Board{
    Chip[][] board;

    Board(){
        board = new Chip[8][8];
        board[3][3] = new Chip(false);
        board[4][4] = new Chip(false);
        board[3][4] = new Chip(true);
        board[4][3] = new Chip(true);
    }

    Chip[][] getBoard(){
        return board;
    }



    
    public String toString(){
        StringBuilder boardString = new StringBuilder();
        boardString.append("  | A | B | C | D | E | F | G | H |\n");
        boardString.append("    _   _   _   _   _   _   _   _\n");
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j++){
                if (j == 0){
                    boardString.append(String.format("%d |", i+1));
                }
                if (board[i][j] == null){
                    boardString.append("   |");
                }
                else{
                    boardString.append(String.format(" %s |", board[i][j]));
                }
                
                if (j == 7){
                    boardString.append("\n");
                    boardString.append("    _   _   _   _   _   _   _   _\n");
                }
                
            }
        }
        return boardString.toString();
    }

    ArrayList<String> possibleMoves(boolean black){
        ArrayList<String> possibleMoves =  new ArrayList<>();
        //System.out.println(Arrays.deepToString(board));
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j ++){
                // Looks for the specified chip
                
                if (board[i][j] != null && board[i][j].getBlack() == black){
                    // Adds the possible moves in the downward direction to the ArrayList.

                    possibleMoves.add(possibleMovesDown(black, j, i));
                    possibleMoves.add(possibleMovesUp(black, j, i));
                    possibleMoves.add(possibleMovesLeft(black, j, i));
                    possibleMoves.add(possibleMovesRight(black, j, i));
                    possibleMoves.add(possibleMovesDownLeft(black, j, i));
                    possibleMoves.add(possibleMovesDownRight(black, j, i));
                    possibleMoves.add(possibleMovesUpLeft(black, j, i));
                    possibleMoves.add(possibleMovesUpRight(black, j, i));
                }
            }
        }
        // remove empty spaces from possible moves
        for(int i = 0; i < possibleMoves.size(); i ++){
            if("".equals(possibleMoves.get(i))){
                possibleMoves.remove(i);
                i -= 1;
            }
        }
        
        return possibleMoves;
    }

    private String possibleMovesDown(boolean black, int column, int row){
        row += 1;
        // If the below chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves downwards, since you cannot convert at least one chip
        if ((row > 7 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (row + 1 <= 7){
            row += 1;
            // if the spot below the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot below the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot below the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesUp(boolean black, int column, int row){
        row -= 1;
        // If the above chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves upwards, since you cannot convert at least one chip
        if ((row < 0 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (row - 1 >= 0){
            row -= 1;
            // if the spot above the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot above the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot above the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesLeft(boolean black, int column, int row){
        column -= 1;
        // If the left chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves to the left, since you cannot convert at least one chip
        if ((column < 0 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column - 1 >= 0){
            column -= 1;
            // if the spot to the left of the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot above the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
        }
        return "";
    }

    private String possibleMovesRight(boolean black, int column, int row){
        column += 1;
        // If the below chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves downwards, since you cannot convert at least one chip
        if ((column > 7 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column + 1 <= 7){
            column += 1;
            // if the spot below the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot below the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot below the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesUpRight(boolean black, int column, int row){
        column += 1;
        row -= 1;
        // If the diagonal chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves diagonally, since you cannot convert at least one chip
        if ((column > 7 || row < 0 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column + 1 <= 7 && row - 1 >= 0){
            column += 1;
            row -= 1;
            //System.out.println(Integer.toString(column) + Integer.toString(row));
            // if the spot diagonal to the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot diagonal to the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot diagonal to the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesUpLeft(boolean black, int column, int row){
        column -= 1;
        row -= 1;
        // If the diagonal chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves diagonally, since you cannot convert at least one chip
        if ((column < 0 || row < 0 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column - 1 >= 0 && row - 1 >= 0){
            column -= 1;
            row -= 1;
            // if the spot diagonal to the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot diagonal to the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot diagonal to the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesDownRight(boolean black, int column, int row){
        column += 1;
        row += 1;
        // If the diagonal chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves diagonally, since you cannot convert at least one chip
        if ((column > 7 || row > 7 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column + 1 <= 7 && row + 1 <= 7){
            column += 1;
            row += 1;
            // if the spot diagonal to the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot diagonal to the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot diagonal to the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    private String possibleMovesDownLeft(boolean black, int column, int row){
        column -= 1;
        row += 1;
        // If the diagonal chip is empty or not the opposite colour, return an empty string
        // this is because there are no possible moves diagonally, since you cannot convert at least one chip
        if ((column < 0 || row > 7 || board[row][column] == null || board[row][column].getBlack() == black)){
            return "";
        }
        while (column - 1 >= 0 && row + 1 <= 7){
            column -= 1;
            row += 1;
            // if the spot diagonal to the opposite coloured chip is null, return it as a possible move
            if (board[row][column] == null){
                return String.format("%d%d", column, row);
            }
            // if the spot diagonal to the opposite coloured chip is not an opposite coloured chip, return an empty string
            else if (board[row][column].getBlack() == black){
                return "";
            }
            // if the spot diagonal to the opposite coloured chip is another opposite coloured chip, keep looping.
        }
        return "";
    }

    void place(int row, int col, boolean black) throws IllegalArgumentException{
        ArrayList<String> playableMoves = possibleMoves(black);
        String userInput = String.format("%d%d", col, row);
        if (playableMoves.contains(userInput)){
            
            board[row][col] = new Chip(black);
        }
        else{
            throw new IllegalArgumentException("Invalid Coordinates");
        }
    }

    boolean isBoardFull(){
        for (int i = 0; i < board.length; i ++){
            for (int j = 0; j < board[0].length; j ++){
                if (board[i][j] == null){
                    return false;
                }
            }
        }
        return true;
    }

    static int getUserColumn(String userInput){
        String letters = "ABCDEFGH";
        if(userInput.length() < 2 || !userInput.substring(0, 1).matches("[ABCDEFGHabcdefgh]")){
            return 10;
            //forces invalid input message instead of causing exception
        }
        return Integer.valueOf(letters.indexOf(userInput.substring(0, 1).toUpperCase()));
    }

    static int getUserRow(String userInput){
        if(userInput.length() < 2 || !userInput.substring(1, 2).matches("[0-8]")){
            return 10;
            //forces invalid input message instead of causing exception
        }
        
        return Integer.valueOf(userInput.substring(1, 2)) - 1;
    }

    String convertToUserInput(String coordinate){
        String letters = "ABCDEFGH";
        return String.format("%s%d", 
letters.charAt(Integer.valueOf(coordinate.substring(0, 1))), Integer.valueOf(coordinate.substring(1, 2)) + 1);
    }

    void displayPossibleMoves(boolean black){
        String display = "";
        System.out.println("Possible moves:");
        for(String move : possibleMoves(black)){
            if(! display.contains(convertToUserInput(move))){
                display += String.format("%s ", convertToUserInput(move));
            }
        }
        System.out.println(display + "\n");
    }

    String possibleMovesAi(){
        if(possibleMoves(false).size() == 0){
            return "";
        }

        int indexLength = possibleMoves(false).size();

        int randomSpot = (int)(Math.random() * (indexLength));

        while (possibleMoves(false).get(randomSpot) == ""){
            randomSpot = (int)(Math.random() * (indexLength));
        }

        
        return possibleMoves(false).get(randomSpot);
        
    }

    void switchTeam(int row, int col, boolean black){
        ArrayList<String> convertChips = new ArrayList<>();
        convertChips.addAll(switchTeamDown(row, col, black));
        convertChips.addAll(switchTeamUp(row, col, black));
        convertChips.addAll(switchTeamLeft(row, col, black));
        convertChips.addAll(switchTeamRight(row, col, black));
        convertChips.addAll(switchTeamDownLeft(row, col, black));
        convertChips.addAll(switchTeamDownRight(row, col, black));
        convertChips.addAll(switchTeamUpLeft(row, col, black));
        convertChips.addAll(switchTeamUpRight(row, col, black));

        convertArrayList(convertChips, black);
        //System.out.println(convertChips);
    }

    private ArrayList<String> switchTeamDown(int row, int col, boolean black){
        row += 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((row > 7 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (row + 1 <= 7) {
            row += 1;
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        
        } return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamUp(int row, int col, boolean black){
        row -= 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((row < 0 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (row - 1 >= 0) {
            row -= 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    
    private ArrayList<String> switchTeamLeft(int row, int col, boolean black){
        col -= 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col < 0 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col - 1 >= 0) {
            col -= 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamRight(int row, int col, boolean black){
        col += 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col > 7 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col + 1 <= 7) {
            col += 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamDownRight(int row, int col, boolean black){
        col += 1;
        row += 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col > 7 || row > 7 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col + 1 <= 7 && row + 1 <= 7) {
            col += 1;
            row += 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamDownLeft(int row, int col, boolean black){
        col -= 1;
        row += 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col < 0 || row > 7 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col - 1 >= 0 && row + 1 <= 7) {
            col -= 1;
            row += 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamUpRight(int row, int col, boolean black){
        col += 1;
        row -= 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col > 7 || row < 0 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col + 1 <= 7 && row - 1 >= 0) {
            col += 1;
            row -= 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    private ArrayList<String> switchTeamUpLeft(int row, int col, boolean black){
        col -= 1;
        row -= 1;
        ArrayList<String> allPiecesUp = new ArrayList<>();
        if ((col < 0 || row < 0 || board[row][col] == null || board[row][col].getBlack() == black)){
            return allPiecesUp;
        }
        else{
            allPiecesUp.add(Integer.toString(col) + Integer.toString(row));
        }
        while (col - 1 >= 0 && row - 1 >= 0) {
            col -= 1;
            row -= 1;
            
            if (board[row][col] == null){
                return new ArrayList<String>();
            }
            
            else if (board[row][col].getBlack() != black){
                String coordinate = Integer.toString(col) + Integer.toString(row);
                allPiecesUp.add(coordinate);
            }

            else{
                return allPiecesUp;
            }
        }
        return new ArrayList<String>();
    }

    

    void convertArrayList(ArrayList<String> chipsToConvert, boolean black){
        for (String coordinate : chipsToConvert){
            int col = Integer.valueOf(coordinate.substring(0, 1));
            int row = Integer.valueOf(coordinate.substring(1, 2));
            board[row][col].setBlack(black);
        }
    }

    static void wait(int ms){
        try{
            Thread.sleep(ms);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    static boolean isValidInput(String userInput, ArrayList<String> possibleMoves){
        if(possibleMoves.contains(String.format("%d%d", getUserColumn(userInput), getUserRow(userInput)))){
            return true;
        }
        return false;
    }

    
    static void playerTurn(boolean black, Board board, Scanner input){
        
        if(board.possibleMoves(black).isEmpty()){
            System.out.println("No possible moves.\n");
            wait(3000);
        }
        else{
            board.displayPossibleMoves(black);
            System.out.println("Enter position:");
            
            String userInput = input.nextLine();

            while (! isValidInput(userInput, board.possibleMoves(black))){
                System.out.println("Invalid position");
                System.out.println("Enter position:");
                userInput = input.nextLine();
            }
        
            int col = getUserColumn(userInput);
            int row = getUserRow(userInput);

            board.place(row, col, black);
            board.switchTeam(row, col, black);
            System.out.println("");
            System.out.println(board);
        }
    }

    static void conclusion(Chip[][] board){
        System.out.println("Game Over");
        int totalBlack = 0;
        int totalWhite = 0;
        for (int i = 0; i < 8; i ++){
            for (int j = 0; j < 8; j ++){
                //if spot on board is empty, do nothing
                if(board[i][j] == null){
                    totalBlack += 0;
                }
                else if(board[i][j].getBlack()){
                    totalBlack += 1;
                }
                else{
                    totalWhite += 1;
                }
            }
        }
        System.out.printf("Black has %d chips\n", totalBlack);
        System.out.printf("White has %d chips\n\n", totalWhite);

        if(totalBlack > totalWhite){
            System.out.println("Black wins!");
        }
        else if(totalBlack < totalWhite){
            System.out.println("White wins!");
        }
        else{
            System.out.println("Draw!");
        }
    }
    
    private static void runOnePlayer(Scanner input){
        Board board = new Board();
        System.out.println(board);

        while (!(board.isBoardFull() || (board.possibleMoves(false).isEmpty() 
        && board.possibleMoves(true).isEmpty()))){
            System.out.println("Your Turn (Black)\n");
            playerTurn(true, board, input);

            System.out.println("Computer Turn (White)\n");
            String aiMove = board.possibleMovesAi();
            wait(2000);
            if(aiMove.equals("")){
                System.out.println("Computer cannot move.\n");
                wait(3000);
            }
            else{
                board.place(Integer.valueOf(aiMove.substring(1, 2)), 
                    Integer.valueOf(aiMove.substring(0, 1)), false);
                board.switchTeam(Integer.valueOf(aiMove.substring(1, 2)),  
                    Integer.valueOf(aiMove.substring(0, 1)), false);
                
                System.out.println(board); 
                wait(2000);
            }
        }
        conclusion(board.getBoard());
    }

    private static void runTwoPlayer(Scanner input){
        Board board = new Board();
        System.out.println(board);

        while (!(board.isBoardFull() || (board.possibleMoves(false).isEmpty() 
        && board.possibleMoves(true).isEmpty()))){
            System.out.println("Player One Turn (Black)\n");
            playerTurn(true, board, input);
            
            System.out.println("Player Two Turn (White)\n");
            playerTurn(false, board, input);
        }
        conclusion(board.getBoard());
    }
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.println("Othello\nOne Player or Two Player?\n1. One Player\n2. Two Player");
        String userInput = input.nextLine();
        while (!(userInput.strip().equals("1") || userInput.strip().equals("2"))){
            System.out.println("Invalid option");
            userInput = input.nextLine();
        }
        if(userInput.strip().equals("1")){
            System.out.println("");
            runOnePlayer(input);
        }
        else{
            runTwoPlayer(input);
        }
        

        
        
        //System.out.println(board);
        //System.out.println(board.getBoard()[0][0]);
        //board.place(5, 4, false);
        //System.out.println(board);
        //System.out.println(board.possibleMoves(true));
        //System.out.println(board.possibleMoves(true));
        //board.place(5, 4, true);
       // System.out.println(board);
       // System.out.println(board.possibleMoves(true).contains("32"));
        //System.out.println(board.possibleMoves(false));

        
    }
}