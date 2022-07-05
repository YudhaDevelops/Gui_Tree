package gui_tree;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphic_Tree extends Tree {
    static TreeNode[] tab = new TreeNode[100];
    static int i = 0;
    JFrame fenetre = null;
    JPanel pan1 = null;

    public Graphic_Tree() {
        fenetre = new JFrame();
        fenetre.setTitle("GUI Binary Search Tree");
        fenetre.setSize(1500, 700);
        fenetre.setResizable(false);
    }

    public void getReady(TreeNode node) {
        tab[i] = node;
        i++;
        if (node.cabangKiri != null) getReady(node.cabangKiri);
        if (node.cabangKanan != null) getReady(node.cabangKanan);
    }

    public void drawTree() {
        fenetre.setContentPane(new drawingTree(tab, howMany));
        fenetre.setVisible(true);
    }
}
