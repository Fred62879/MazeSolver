package ui;

import model.Maze;

import java.util.Scanner;

public class Main {

    Maze mz;
    int[][] matrix;

    public Main() {
        greeting();
        askForInput();
        readIn();
        mz = new Maze(matrix);
        show();
    }

    public void askForInput() {
        System.out.println("Please input your maze below (in the order of:");
        System.out.println("row number, column number, and maze abstraction):");
    }

    public void greeting() {
        System.out.println("Hi there, this is a Maze solver!");
    }

    public void show() {
        System.out.println();
        System.out.println("This is how your maze looks like:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public void readIn() {
        Scanner s = new Scanner(System.in);
        int r = s.nextInt();
        int c = s.nextInt();
        this.matrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] = s.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();

    }
}
