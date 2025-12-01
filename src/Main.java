import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filePath;
        int mode;

        if (args.length == 2) {
            filePath = args[0];
            mode = Integer.parseInt(args[1]);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Running in Interactive Mode...");

            System.out.print("Enter CSV file path: ");
            filePath = scanner.nextLine();

            System.out.print("Enter Mode (0, 3, 27): ");
            mode = scanner.nextInt();
        }

        System.out.println("Processing file: " + filePath + " with Mode: " + mode);

        SudokuLoader loader = new SudokuLoader();
        int[][] board = loader.loadBoard(filePath);

        if (board != null) {
            Verifier verifier = VerifierFactory.getVerifier(mode);
            if (verifier != null) {
                verifier.verify(board);
            }
        }
    }
}