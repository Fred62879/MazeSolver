package ui;

import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

    private Container container;
    private GridBagConstraints constraints;
    // input panel
    private JPanel inputPane;
    private JTextField rowf;
    private JTextField colf;
    private JTextField entryf;
    private JButton submitButton;
    // solve panel
    private JPanel solvePane;
    private JLabel solveInstruction;
    private JRadioButton dfs;
    private JRadioButton bfs;
    private JButton solveButton;
    // display panel
    private JPanel displayPane;
    private JRadioButton retrieve;
    private JRadioButton input;
    private JTable table;

    private JPanel test;

    // Maze operations
    private Maze mz;
    private MazeSolver ms;
    private MazeDisplayer md;
    private String mazeinfo;
    private HashMap<Maze, List<String>> solved;
    private List<String> storeMazes;
    private List<String> solveDemo;

    public MazeSolverUI() {
        super("Maze Solver");
        mz = null;
        md = new MazeDisplayer();
        solved = new HashMap<>();
        storeMazes = null;
        solveDemo = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: initialize user interface
    public void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(300,400));
        JLabel label1 = new JLabel("Please enter your maze according to instructions below.", SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(300, 100));
        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        constraints = new GridBagConstraints();

        container.add(choicePanel());//, BorderLayout.NORTH);
        container.add(inputPanel());//, BorderLayout.CENTER);
        container.add(solvePanel());//, BorderLayout.SOUTH);
        container.add(displayPanel());//, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    // EFFECTS: respond to user action
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "Confirm" && retrieve.isSelected()) {
            displayStorage();
        } else if (e.getActionCommand() == "Submit") {
            submitOpertion();
        } else if (e.getActionCommand() == "Load") {
            loadOperation(table.getSelectedRow());
        } else if (e.getActionCommand() == "Solve") {
            solveOperation();
        }
    }

    // MODIFIES: this
    // EFFECTS: create panel for maze selection choice: load or input
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
        choicePane.setBorder(BorderFactory.createLineBorder(Color.black));
        return choicePane;
    }


    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add row label
    private void rowLabel() {
        JLabel lb = new JLabel("Row");
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPane.add(lb, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add column label
    private void colLabel() {
        JLabel lb = new JLabel("Column");
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 2;
        constraints.gridy = 0;
        inputPane.add(lb, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add row field
    private void rowField() {
        constraints.gridx = 1;
        constraints.gridy = 0;
        inputPane.add(rowf, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add column field
    private void colField() {
        constraints.gridx = 3;
        constraints.gridy = 0;
        inputPane.add(colf, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add entry field
    private void entryField() {
        constraints.ipady = 40; // make this component tall
        constraints.weightx = 0.0;
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPane.add(entryf, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add submit button
    private void submitButton() {
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        constraints.ipady = 0; // reset
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 2;
        inputPane.add(submitButton, constraints);
    }

    // MODIFIES: this
    // EFFECTS: create panel for maze input
    public JPanel inputPanel() {
        inputPane = new JPanel();
        inputPane.setLayout(new GridBagLayout());
        // create field
        rowf = new JTextField(10);
        rowf.addActionListener(this);
        colf = new JTextField(10);
        colf.addActionListener(this);
        entryf = new JTextField(10);
        entryf.addActionListener(this);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        // set panel
        rowLabel();
        colLabel();
        rowField();
        colField();
        entryField();
        submitButton();
        inputPane.setBorder(BorderFactory.createLineBorder(Color.black));

        return inputPane;
    }


    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add dfs button
    private void dfsButton() {
        constraints.weightx = 0.5;
        constraints.gridwidth = 1;
        constraints.gridx = 0;
        constraints.gridy = 1;
        solvePane.add(dfs, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and add bfs button
    private void bfsButon() {
        constraints.gridx = 1;
        constraints.gridy = 1;
        solvePane.add(bfs, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and create and add solving instruction
    private void solveLabel() {
        solveInstruction = new JLabel("<html>Select algorithms to solve maze!</html>", SwingConstants.CENTER);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        solvePane.add(solveInstruction, constraints);
    }

    // MODIFIES: this
    // EFFECTS: change baglayout constraints and create and add solving button
    private void solveButton() {
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this);
        constraints.weightx = 0;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 0;
        solvePane.add(solveButton, constraints);
    }

    // MODIFIES: this
    // EFFECTS: create maze solving panel
    public JPanel solvePanel() {
        solvePane = new JPanel();
        solvePane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // create panel components
        dfs = new JRadioButton("DFS");
        bfs = new JRadioButton("BFS");
        ButtonGroup group = new ButtonGroup();
        group.add(dfs);
        group.add(bfs);
        // set panel
        dfsButton();
        bfsButon();
        solveLabel();
        solveButton();
        solvePane.setBorder(BorderFactory.createLineBorder(Color.black));

        return solvePane;
    }


    // MODIFIES: this
    // EFFECTS: load maze storage data to table
    private void tablizeStorage(List<String> list) {
        String [] header = {"Maze storage"};
        String[][] data = new String[list.size()][1];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);
        }
        DefaultTableModel model = new DefaultTableModel(data, header);

        table = new JTable(model) {
            @Override
            public Class<?> getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }
        };
//        table.setPreferredScrollableViewportSize(new Dimension(450,63));
//        table.setFillsViewportHeight(true);
//
//        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
//        table.setBounds(10, 0, 457, 103);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
    }

    // MODIFIES: this
    // EFFECTS: create new display panel to show stored mazes
    private void displayPaneForStorage() {
        displayPane = new JPanel();
        displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.PAGE_AXIS));
        JScrollPane scroll = new JScrollPane(table);
        JButton load = new JButton("Load");
        load.setAlignmentX(Component.CENTER_ALIGNMENT);
        load.addActionListener(this);
        displayPane.add(scroll);
        displayPane.add(load);
    }

    // MODIFIES: this
    // EFFECTS: load maze storage data to table
    private void displayStorage() {
        try {
            storeMazes = Files.readAllLines(Paths.get("inputfile.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        container.remove(displayPane);
        if (storeMazes.isEmpty()) {
            displayPane.add(new JLabel("No storage found!"));
        } else {
            tablizeStorage(storeMazes);
            displayPaneForStorage();
        }
        displayPane.setBorder(BorderFactory.createLineBorder(Color.black));
        reconstruct();
    }

    // MODIFIES: this
    // EFFECTS: display given instructions on display panel
    private void displayInstruction(String instruction) {
        container.remove(displayPane);
        displayPane = new JPanel();
        displayPane.add(new JLabel(instruction));
        reconstruct();
    }

    // MODIFIES: this
    // EFFECTS: display given maze on display panel
    private void displayMaze(List<String> demo) {
        container.remove(displayPane);
        displayPane = new JPanel();
        displayPane.setLayout(new BoxLayout(displayPane, BoxLayout.PAGE_AXIS));
//        for (String str : demo) {
//            displayPane.add(new JLabel(str));
//            System.out.println(str);
//        }
        tablizeStorage(demo);
        displayPane.add(table);
        reconstruct();
    }

    // MODIFIES: this
    // EFFECTS: refresh ui for new display panel
    private void reconstruct() {
        container.add(displayPane);
        revalidate();
        repaint();
    }

    // MODIFIES: this
    // EFFECTS: create maze solving panel
    public JComponent displayPanel() {
        displayPane = new JPanel();
        displayPane.setBorder(BorderFactory.createLineBorder(Color.black));
        return displayPane;
    }


    // ** Maze Operations **

    // MODIFIES: this
    // EFECTS: load maze storage
    public void loadStoreMaze(List<String> storeMazes) {
        this.storeMazes = storeMazes;
    }

    // REQUIRES: maze is initialized and valid
    // MODIFIES: this
    // EFECTS: load maze to maze displayer
    private void displayMazeHelper() {
        md.load(mz.getWholeMatrix());
        md.displayMaze();
        displayMaze(md.getDemoMaze());
    }

    // MODIFIES: this
    // EFECTS: initialize maze and print out warning if maze invalid
    private boolean initializeMaze() {
        try {
            mz = new Maze();
            mz.load(mazeinfo);
            if (!mz.isValid()) {
                displayInstruction("Maze invalid, please re-enter maze!");
                return false;
            }
        } catch (Exception ex) {
            displayInstruction("Maze invalid, please re-enter maze!");
            return false;
        }
        return true;
    }

    // MODIFIES: this
    // EFECTS: read user input about maze and convert to string
    private boolean readIn() {
        int row = 0;
        int col = 0;
        String entry = "";
        try {
            row = Integer.parseInt(rowf.getText().trim());
            col = Integer.parseInt(colf.getText().trim());
            entry = entryf.getText().trim();
        } catch (Exception ex) {
            displayInstruction("Please give valid maze info before submit!");
            return false;
        }
        mazeinfo = row + " " + col + " " + entry;
        return true;
    }

    // MODIFIES: this
    // EFECTS: respond to user submit action
    public void submitOpertion() {
        if (!readIn()) {
            return;
        }
        if (initializeMaze()) {
            displayMazeHelper();
        }
    }

    // MODIFIES: this
    // EFECTS: respond to user load action
    public void loadOperation(int selRow) {
        if (selRow < 0) {
            displayInstruction("Please select maze before loading!");
        }
        mazeinfo = storeMazes.get(selRow);
        if (initializeMaze()) {
            displayMazeHelper();
        }
    }

    // REQUIRES: mz is initialized and path is solved
    // MODIFIES: this
    // EFFECTS: display path on maze
    private void solveHelper() {
        md.load(mz.getWholeMatrix());
        md.setPath(ms.getPath());
        md.displayPath();
        solveDemo = md.getDemoPath();
        solved.put(mz, solveDemo);
        displayMaze(solveDemo);
    }

    // MODIFIES: this
    // EFFECTS: solve current maze based on user choice of algr and display path demo
    private void solveRunTime() {
        if (dfs.isSelected()) {
            ms = new MazeSolverDFS(mz.getWholeMatrix());
        } else if (bfs.isSelected()) {
            ms = new MazeSolverBFS(mz.getWholeMatrix());
        } else {
            displayInstruction("Please select algorithm before solving!");
            return;
        }
        ms.solve(0, 0);
        if (ms.getPath().size() == 0) {
            displayInstruction("Maze Unsolvable!");
        } else {
            solveHelper();
        }
    }

    // MODIFIES: this
    // EFECTS: respond to user solve request
    public void solveOperation() {
        if (mz == null) {
            displayInstruction("Please enter or load maze before solving!");
            return;
        }
        if (solved.containsKey(mz)) {
            solveDemo = solved.get(mz);
            displayMaze(solveDemo);
        } else {
            solveRunTime();
        }
    }
}
