package gui_tree;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class drawingTree extends JPanel {

    static TreeNode[] copietab = new TreeNode[100];
    int n = 0;

    public drawingTree(TreeNode[] node, int n) {
        copietab = node.clone();
        this.n = n;
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < n; i++) 
        {
            
            g.drawOval(copietab[i].x, copietab[i].y, 40, 40);
            String a = String.valueOf(copietab[i].data);
            g.drawString(a, copietab[i].x + 13, copietab[i].y + 25);
            if (copietab[i].cabangKanan != null) {
                g.setColor(Color.red);
                g.drawLine(copietab[i].x + 20, copietab[i].y + 40, copietab[i].x + 100, copietab[i].y + 90);
            }
            if (copietab[i].cabangKiri != null) {
                g.setColor(Color.green);
                g.drawLine(copietab[i].x + 15, copietab[i].y + 40, copietab[i].x -50, copietab[i].y + 90);
            }
            g.setColor(Color.black);
//            g.drawString("ha", 1, 2);
        }
    }

}
