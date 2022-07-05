package gui_v3;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;

public class BSTDemo extends DrawPanel implements ActionListener {

    private static JLabel status;
    private static BSTDemo myPanel;
    private int yStart;
    private BinSearchTree<Integer, String> bst;
    private JTextField inputBox;

    public BSTDemo() {
        super();
        bst = new BinSearchTree<Integer, String>();
        yStart = 50;
        JButton addButton = new JButton("Click to Add");
        addButton.addActionListener(this);
        add(addButton, BorderLayout.NORTH);
        inputBox = new JTextField("", 10);
        add(inputBox, BorderLayout.NORTH);
        JButton findButton = new JButton("Find");
        findButton.addActionListener(this);
        add(findButton, BorderLayout.NORTH);
        JButton delButton = new JButton("Delete");
        delButton.addActionListener(this);
        add(delButton, BorderLayout.NORTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g);
        //g.drawString("Hello",10,yStart);
    }

    public Dimension getPreferredSize() {
        return new Dimension(800, 400);
    }

    public void actionPerformed(ActionEvent ae) {
        String choice = ae.getActionCommand();
        if (choice.equals("Quit")) {
            System.exit(0);
        } else if (choice.equals("Click to Add")) {
            //yStart += 10;
            String[] nodeVals = JOptionPane.showInputDialog(
                    this, "Enter key and value separated by colon "
                    + " (key:value) ", "key:value").split(":");
            int key = Integer.parseInt(nodeVals[0].trim());
            String val = nodeVals[1];
            bst.add(key, val);
            repaint();
            status.setText("Added " + key + " : " + val);
        } else if (choice.equals("Find")) {
            int key = Integer.parseInt(inputBox.getText());
            String val = bst.find(key);
            if (val != null) {
                JOptionPane.showMessageDialog(this,
                        "Found " + val + " at " + key);
                status.setText("Found " + val);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Key not found");
                status.setText("Could not find key");
            }
        } else if (choice.equals("Delete")) {
            int key = Integer.parseInt(inputBox.getText());
            String val = bst.find(key);
            if (val != null) {
                bst.delete(key);
                repaint();
                status.setText("Deleted " + key);
            } else {
                status.setText("No such key to delete");
                JOptionPane.showMessageDialog(this,
                        "Cannot delete, key doesn't exist");
            }
        }
    }

    public void drawTree(Graphics g) {
        bst.draw(myPanel, g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGui();
            }
        });
    }

    public static void createAndShowGui() {
        JFrame myApp = new JFrame("Binary Search Tree");
        myPanel = new BSTDemo();
        myApp.add(myPanel);
        initMenu(myApp);
        int width = (int) myPanel.getPreferredSize().getWidth();
        initStatus(myApp, 600);
        myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myApp.pack();
        myApp.setVisible(true);
    }

    private static void initMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(myPanel);
        file.add(quit);
    }

    private static void initStatus(JFrame frame, int width) {
        status = new JLabel("Program loaded");
        JPanel panel = new JPanel();
        panel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel.setPreferredSize(new Dimension(width, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(status);
        status.setHorizontalAlignment(SwingConstants.LEFT);
        frame.add(panel, BorderLayout.SOUTH);
    }
}
