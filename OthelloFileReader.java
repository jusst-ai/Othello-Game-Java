import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

class OthelloFileReader {

    static ArrayList<String> readMoves(String filename){
        ArrayList<String> moves = new ArrayList<>();

    try{
        File file = new File(filename);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()){
            String nextline = reader.nextLine();
            if (!nextline.equals("")){
                moves.add(nextline);
            }
                
            
            
        }

        reader.close();
        return moves;

    }
    catch (FileNotFoundException e){
        System.out.println(e.getMessage());
    }

    

    return moves;

    }

    private static void run(String filename){
        ArrayList<String> moves = readMoves(filename);
      
     
      Board board = new Board();
      int i = 0;
    
      while (i + 1 < moves.size()){
        
        String blackMove = moves.get(i);
        i += 1;
        String whiteMove = moves.get(i);
        i += 1;
        //System.out.println(i);
        int blackCol = Board.getUserColumn(blackMove);
        //System.out.println(blackCol);
        int blackRow = Board.getUserRow(blackMove);

        //System.out.println(blackMove);

        int whiteCol = Board.getUserColumn(whiteMove);
        int whiteRow = Board.getUserRow(whiteMove);

        board.place(blackRow, blackCol, true);
        board.switchTeam(blackRow, blackCol, true);
        System.out.println(board);

        /*System.out.println(whiteMove);
        System.out.println(whiteCol);
        System.out.println(whiteRow);
        
        System.out.println(board.possibleMoves(false));*/
        board.place(whiteRow, whiteCol, false);
        board.switchTeam(whiteRow, whiteCol, false);
        System.out.println(board);
      }
    }

    public static void main(String[] args) {
      run("test1.txt");
      System.out.println("\n\n\n\n");
    
      run("test.txt");
      System.out.println("\n\n\n\n");
      //System.out.println("Test Full Othello Game");
      run("test2.txt"); // This will run a full othello game.

      System.out.println("Complete");

    }


}
