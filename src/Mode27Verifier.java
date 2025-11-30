import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class Mode27Verifier implements Verifier {

    @Override
    public void verify(int[][] board) {
        List<String> errors = new Vector<>();
        
        List<Thread> allThreads = new ArrayList<>();

        
        for (int i = 0; i < 9; i++) {
            final int currentRow = i;
            Thread t = new Thread(() -> {
                if (!isValidUnit(board[currentRow])) {
                    errors.add("ROW " + (currentRow + 1) + " is invalid");
                }
            });
            allThreads.add(t);
        }

        
        for (int i = 0; i < 9; i++) {
            final int currentCol = i;
            Thread t = new Thread(() -> {
                int[] columnData = new int[9];
                for (int row = 0; row < 9; row++) {
                    columnData[row] = board[row][currentCol];
                }
                if (!isValidUnit(columnData)) {
                    errors.add("COL " + (currentCol + 1) + " is invalid");
                }
            });
            allThreads.add(t);
        }

        
        for (int i = 0; i < 9; i++) {
            final int currentBoxIndex = i;
            Thread t = new Thread(() -> {
                int boxRowStart = (currentBoxIndex / 3);  
                int boxColStart = (currentBoxIndex % 3);
                
                int[] boxData = new int[9];
                int index = 0;
                
                
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        int actualRow = boxRowStart * 3 + r;
                        int actualCol = boxColStart * 3 + c;
                        boxData[index++] = board[actualRow][actualCol];
                    }
                }
                
                if (!isValidUnit(boxData)) {
                    errors.add("BOX " + (currentBoxIndex + 1) + " is invalid");
                }
            });
            allThreads.add(t);
        }

        for (Thread t : allThreads) {
            t.start();
        }

        for (Thread t : allThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
        if (errors.isEmpty()) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
    private boolean isValidUnit(int[] unit) {
        Set<Integer> seen = new HashSet<>();
        for (int num : unit) {
            if (num < 1 || num > 9 || seen.contains(num)) {
                return false;
            }
            seen.add(num);
        }
        return true;
    }
}