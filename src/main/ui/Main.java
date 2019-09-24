package ui;

import model.ToDoList;

public class Main {

    private ToDoList tdl;

    // EFFECTS: interacts with the user and constructs a maze
    public Main() {
        tdl = new ToDoList();
        tdl.run();
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}
