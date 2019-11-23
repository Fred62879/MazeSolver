package demos;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Interactions {

    public Interactions() { }

    public int readInt(Scanner s) {
        int res = 0;
        try {
            res = s.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please give integers only!");
            return -1;
        }
        return res;
    }

    public int readChar(Scanner s) {
        char res = '#';
        try {
            res = s.next().charAt(0);
        } catch (InputMismatchException ex) {
            System.out.println("Error: Please give integers only!");
        }
        return res;
    }


    // ** Prompts **
    public boolean yesNoResponse(String prmpt) {
        System.out.println(prmpt + " (y/n)");
        return readChar(new Scanner(System.in)) == 'y';
    }

    public boolean saveResponse() throws IOException {
        return yesNoResponse("Save your maze to local storage?");
    }

    public boolean solveNewMaze() {
        return yesNoResponse("Would you like to solve a new Maze?");
    }

    public boolean removeStorage() {
        return yesNoResponse("Would you like to remove mem for the Maze just solved?");
    }

    public int choose() {
        System.out.println("Which method would you like to use for maze solving? (1-DFS/2-BFS)");
        int choice = readInt(new Scanner(System.in));
        while (choice != 1 && choice != 2) {
            System.out.println("Please select from these two methods only!");
            choice = readInt(new Scanner(System.in));
        }
        return choice;
    }
}
