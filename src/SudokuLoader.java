import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SudokuLoader {


    public int[][] loadBoard(String filePath) {
        int[][] board = new int[9][9];
        
        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);
            
            for (int row = 0; row < 9; row++) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    
                    for (int col = 0; col < 9; col++) {
                        board[row][col] = Integer.parseInt(values[col].trim());
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found! " + e.getMessage());
            return null; 
        }
        
        return board;
    }
}