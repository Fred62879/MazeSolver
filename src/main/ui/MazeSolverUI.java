package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MazeSolverUI extends JFrame implements ActionListener {

    private int row;
    private int col;
    private String entry;
    private List<String> storeMazes;
    private JTextField rowf;
    private JTextField colf;
    private JTextField entryf;
    private Container container;
    private JComponent displayPane;
    private JRadioButton retrieve;
    private JRadioButton input;
    private JRadioButton DFS;
    private JRadioButton BFS;
    private JTable table;

    private String mazeinfo;
    private Maze mz;
    private HashMap<Maze, List<String>> solved;
    private MazeSolver ms;
    private MazeDisplayer md;
    private List<String> mazeDemo;


    public MazeSolverUI() {
        super("Maze Solver");
        storeMazes = new ArrayList<>();
        mz = new Maze();
        solved = new HashMap<>();
        md = new MazeDisplayer();
        mazeDemo = new ArrayList<>();
    }

    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(300,400));
        JLabel label1 = new JLabel("Please enter your maze according to instructions below.", SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(300, 100));
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        // UIConstruction uic = new UIConstruction(container);
        container.add(choicePanel());//, BorderLayout.NORTH);
        container.add(inputPanel());//, BorderLayout.CENTER);
        container.add(solvePanel());//, BorderLayout.SOUTH);
        container.add(displayPanel());//, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Confirm" && retrieve.isSelected()) {
            try {
                displayStorage();
            } catch (IOException ex) {
                System.out.println("Storage file not found!");
            }
        } else if (e.getActionCommand() == "Submit") {
            readIn();
            initializeMaze();
        } else if (e.getActionCommand() == "Load") {
            mazeinfo = storeMazes.get(table.getSelectedRow());
            initializeMaze();
        } else if (e.getActionCommand() == "Solve") {
            uiSolve();
        }
    }

    private void uiSolve() {
        if (solved.containsKey(mz)) {
            mazeDemo = solved.get(mz);
        } else {
            if (DFS.isSelected()) {
                ms = new MazeSolverDFS(mz.getWholeMatrix());
            } else {
                ms = new MazeSolverBFS(mz.getWholeMatrix());
            }
            ms.solve(0, 0);
            System.out.println(ms.getPath().size());
            if (ms.getPath().size() == 0) {
                displayInstruction("Maze Unsolvable!");
            } else {
                md.load(mz.getWholeMatrix(), ms.getPath());
                md.displayPath();
                mazeDemo = md.getDemoPath();
                solved.put(mz, mazeDemo);
                displayMazeDemo();
            }
        }
    }

    private void initializeMaze() {
        try {
            mz.load(mazeinfo);
            System.out.println(mazeinfo);
        } catch (Exception ex) {
            displayInstruction("Maze invalid, please re-enter maze!");
        }
    }

    private void readIn() {
        row = Integer.parseInt(rowf.getText().trim());
        col = Integer.parseInt(colf.getText().trim());
        entry = entryf.getText().trim();
        mazeinfo = row + " " + col + " " + entry;
    }

    private void tablizeStorage() throws IOException {
        String [] header = {"Maze storage"};
        String[][] data = new String[storeMazes.size()][1];
        for (int i = 0; i < storeMazes.size(); i++) {
            data[i][0] = storeMazes.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(data, header);

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(450,63));
        table.setFillsViewportHeight(true);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setBounds(10, 0, 457, 103);
    }

    private void displayStorage() throws IOException {
        storeMazes = Files.readAllLines(Paths.get("inputfile.txt"));
        container.remove(displayPane);
        if (storeMazes.isEmpty()) {
            displayPane.add(new JLabel("No storage found!"));
        } else {
            tablizeStorage();
            displayPane = new JPanel();
            displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.PAGE_AXIS));
            JScrollPane scroll = new JScrollPane(table);
            JButton load = new JButton("Load");
            load.addActionListener(this);
            displayPane.add(load);
            displayPane.add(scroll);
        }
        reconstruct();
    }

    private void displayInstruction(String instruction) {
        container.remove(displayPane);
        displayPane = new JPanel();
        displayPane.add(new JLabel(instruction));
        reconstruct();
    }

    private void displayMazeDemo() {
        container.remove(displayPane);
        displayPane = new JPanel();
        displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.PAGE_AXIS));
        for (String str : mazeDemo) {
            displayPane.add(new JLabel(str));
            System.out.println(str);
        }
        reconstruct();
    }

    private void reconstruct() {
        container.add(displayPane);
        revalidate();
        repaint();
    }

    public JPanel choicePanel() {
        JPanel choicePane = new JPanel();

        retrieve = new JRadioButton("Retrieve");
        retrieve.setMnemonic(KeyEvent.VK_C);
        choicePane.add(retrieve);

        input = new JRadioButton("Enter new");
        input.setMnemonic(KeyEvent.VK_G);
        choicePane.add(input);

        JButton confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        choicePane.add(confirm);

        ButtonGroup group = new ButtonGroup();
        group.add(retrieve);
        group.add(input);

        return choicePane;
    }

    public JPanel inputPanel() {
        JPanel inputPane = new JPanel();
        inputPane.setLayout(new GridLayout(3, 2));

        rowf = new JTextField(10);
        colf = new JTextField(10);
        entryf = new JTextField(10);

        inputPane.add(new Label("Row"));
        inputPane.add(rowf);
        rowf.addActionListener(this);
        inputPane.add(new Label("Col"));
        inputPane.add(colf);
        colf.addActionListener(this);
        inputPane.add(new Label("Maze entry"));
        inputPane.add(entryf);//, BorderLayout.EAST);
        entryf.addActionListener(this);

        return inputPane;
    }

    public JPanel solvePanel() {
        JPanel solvePane = new JPanel();
        solvePane.setLayout(new BoxLayout(solvePane, BoxLayout.PAGE_AXIS));

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        DFS = new JRadioButton("DFS");
        BFS = new JRadioButton("BFS");
        ButtonGroup group = new ButtonGroup();
        group.add(DFS);
        group.add(BFS);

        solvePane.add(submitButton);
        solvePane.add(solveButton);
        solvePane.add(new JLabel("Select algr to solve maze!"));
        solvePane.add(DFS);
        solvePane.add(BFS);

        return solvePane;
    }

    public JComponent displayPanel() {
        return displayPane = new JPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MazeSolverUI().initialize();
            }
        });
    }
}