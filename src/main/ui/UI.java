package ui;

import javax.swing.*;
import java.awt.*;

public class UI {

    public static JTextField textfield1, textfield2, textfield3;

    public UI() {

    }

    public static void createWindow() {
        JFrame frame = new JFrame("Maze Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label1 = new JLabel("Hi there, this is a maze solver!", SwingConstants.CENTER);
        label1.setPreferredSize(new Dimension(300, 100));
        frame.getContentPane().add(label1, BorderLayout.CENTER);

        textfield1 = new JTextField("Text field 1",10);
        textfield2 = new JTextField("Text field 2",10);
        textfield3 = new JTextField("Text field 3",10);
        frame.getContentPane().add(textfield1);
        frame.getContentPane().add(textfield2);
        frame.getContentPane().add(textfield3);

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
        JTextField textfield= new JTextField();
        textfield.setBounds(110, 50, 130, 30);

        frame.add(label3);
        frame.add(textfield);
        frame.add(label2);
        frame.add(b);

        frame.getContentPane().setLayout(new FlowLayout());

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        createWindow();
    }
}
