package ui;

import javax.swing.*;
import java.awt.*;

public class UI {

    public static JTextField textfield1, textfield2, textfield3;

    public UI() {

    }

    public static void createWindow() {
        /*
        JFrame frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("Please enter your maze according to instructions below.", SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(label1, BorderLayout.NORTH);

        textfield1 = new JTextField("Number of rows",1);
        textfield2 = new JTextField("Number of columns",1);
        textfield3 = new JTextField("Maze entries",1);
        frame.getContentPane().add(textfield1, BorderLayout.WEST);
        frame.getContentPane().add(textfield2, BorderLayout.CENTER);
        frame.getContentPane().add(textfield3, BorderLayout.EAST);

        frame.setLayout(new GridLayout(2, 3));

        /*
        JButton b = new JButton("Enter");
        b.setBounds(100,100,140, 40);
        //enter name label2
        JLabel label2 = new JLabel();
        label2.setText("Enter Name :");
        label2.setBounds(10, 10, 100, 100);
        //empty label2 which will show event after button clicked
        JLabel label3 = new JLabel();
        label3.setBounds(10, 110, 200, 100);
        //textfield to enter name
        JTextField textfield = new JTextField();
        textfield.setBounds(110, 50, 130, 30);

        frame.getContentPane().add(label3);
        frame.getContentPane().add(textfield);
        frame.getContentPane().add(label2);
        frame.getContentPane().add(b);

        //frame.getContentPane().setLayout(new FlowLayout());

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

         */
        //JScrollPane listScroller = new JScrollPane(list);
        // listScroller.setPreferredSize(new Dimension(250, 80));
        // listScroller.setAlignmentX(LEFT_ALIGNMENT);

        //Lay out the label and scroll pane from top to bottom.
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
        JLabel label = new JLabel("a");

        listPane.add(label);
        listPane.add(Box.createRigidArea(new Dimension(0,5)));
        //listPane.add(listScroller);
        listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

//Lay out the buttons from left to right.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));

        //Put everything together, using the content pane's BorderLayout.
        Container contentPane = new Container();//getContentPane();
        contentPane.add(listPane, BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
    }

    public static void main(String[] args) {
        createWindow();
    }
}
