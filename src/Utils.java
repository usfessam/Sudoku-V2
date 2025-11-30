import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static void analyzeSegment(int[] unit, String type, int index, List<String> errors) {
        List<Integer>[] positions = new ArrayList[10];
        
        for (int i = 0; i <= 9; i++) {
            positions[i] = new ArrayList<>();
        }

        for (int i = 0; i < 9; i++) {
            int num = unit[i];
            if (num >= 1 && num <= 9) {
                positions[num].add(i + 1);
            }
        }

        for (int num = 1; num <= 9; num++) {
            if (positions[num].size() > 1) {
                String errorMsg = String.format("%s %d, #%d, %s", 
                                              type, (index + 1), num, positions[num].toString());
                errors.add(errorMsg);
            }
        }
    }
}