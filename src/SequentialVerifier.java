import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SequentialVerifier implements Verifier {

  @Override
  public void verify(int[][] board) {
    List<String> errors = new ArrayList<>();

    for (int row = 0; row < 9; row++) {
      if (!isValidUnit(board[row])) {
        errors.add("ROW " + (row + 1) + " is invalid");
      }
    }

    for (int col = 0; col < 9; col++) {
      int[] columnData = new int[9];
      for (int row = 0; row < 9; row++) {
        columnData[row] = board[row][col];
      }
      if (!isValidUnit(columnData)) {
        errors.add("COL " + (col + 1) + " is invalid");
      }
    }

    for (int boxRow = 0; boxRow < 3; boxRow++) {
      for (int boxCol = 0; boxCol < 3; boxCol++) {
        int[] boxData = new int[9];
        int index = 0;

        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            int currentRow = boxRow * 3 + i;
            int currentCol = boxCol * 3 + j;
            boxData[index++] = board[currentRow][currentCol];
          }
        }

        if (!isValidUnit(boxData)) {
          int boxNumber = (boxRow * 3) + boxCol + 1;
          errors.add("BOX " + boxNumber + " is invalid");
        }
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