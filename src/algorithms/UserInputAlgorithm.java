package algorithms;

import java.util.Scanner;

public class UserInputAlgorithm implements Algorithm {

    private Scanner in;

    public UserInputAlgorithm() {
        this.in = new Scanner(System.in);
    }

    @Override
    public String makeMove(String[][] grid) {
        return in.nextLine();
    }
}
