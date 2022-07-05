package gui_v3;

import java.awt.Graphics;
import java.awt.Point;

public class BinSearchTree<K extends Comparable<K>, V> {

    class TreeNode {

        K key;
        V value;
        TreeNode left;
        TreeNode right;
        int x;
        int y;
        int width;
        String childType;

        public TreeNode(K k, V val) {
            key = k;
            value = val;
            left = null;
            right = null;
            x = 0;
            y = 0;
            width = 0;
            childType = "";
        }

        public String toString() {
            String temp = key + ":" + value + ":"
                    + x + ":" + y + ":" + childType;
            return temp;
        }
    }
    private TreeNode root;
    private String childType;
    private int parentX;
    private int parentY;

    public BinSearchTree() {
        root = null;
        childType = "";
        parentX = 0;
        parentY = 0;
    }

    public void add(K key, V val) {
        root = i_add(root, key, val);
    }

    private TreeNode i_add(TreeNode nd, K key, V val) {
        if (nd == null) {
            return new TreeNode(key, val);
        }
        int cmp = key.compareTo(nd.key);
        if (cmp == 0) {
            nd.value = val; //replace
        } else if (cmp > 0) {
            nd.right = i_add(nd.right, key, val);
        } else {
            nd.left = i_add(nd.left, key, val);
        }
        return nd;
    }

    public void printInOrder() {
        i_printInOrder(root);
    }

    private void i_printInOrder(TreeNode nd) {
        if (nd == null) {
            return;
        }
        i_printInOrder(nd.left);
        System.out.println(nd);
        i_printInOrder(nd.right);
    }

    public int height() {
        return i_height(root);
    }

    private int i_height(TreeNode nd) {
        if (nd == null) {
            return 0;
        }
        int leftHt = i_height(nd.left);
        int rightHt = i_height(nd.right);
        if (leftHt > rightHt) {
            return leftHt + 1;
        } else {
            return rightHt + 1;
        }
    }

    public void draw(BSTDemo panel, Graphics g) {
        if (root != null) {
            calcCoords(new Point(400, 50));
            printByLevel(panel, g);
        }
    }

    public void calcCoords(Point startPt) {
        int height = height();
        root.x = startPt.x;
        root.y = startPt.y;
        for (int depth = 2; depth <= height; depth++) {
            int spacing = (int) Math.pow(2, (height - depth)) * 40;
            i_calcCoords(root, depth, spacing);
        }
    }

    public void i_calcCoords(TreeNode nd, int depth, int width) {
        if (nd == null) {
            return;
        }
        if (depth == 1) {
            if (childType == "left") {
                nd.x = parentX - width / 2;
                nd.childType = "left";
            } else if (childType == "right") {
                nd.x = parentX + width / 2;
                nd.childType = "right";
            }
            nd.y = parentY + 50;
            nd.width = width;
        } else {
            parentX = nd.x;
            parentY = nd.y;
            childType = "left";
            i_calcCoords(nd.left, depth - 1, width);
            childType = "right";
            i_calcCoords(nd.right, depth - 1, width);
        }
    }

    public void drawNode(BSTDemo panel, Graphics g, TreeNode nd) {
        //g.drawRect(nd.x,nd.y,30,30);
        panel.drawRectangle(g, new Point(nd.x, nd.y), 30, 30);
        String key = "" + nd.key;
        int x = nd.x + (4 - key.length()) * 4;
        g.drawString("" + nd.key, x, nd.y + 20);
        if (nd != root) {
            int x2 = 0;
            int y2 = 0;
            if (nd.childType == "left") {
                x2 = nd.x + nd.width / 2;
            } else if (nd.childType == "right") {
                x2 = nd.x - nd.width / 2;
            }
            y2 = nd.y - 20;
            panel.drawLine(g, new Point(nd.x + 15, nd.y), new Point(x2 + 15, y2));
        }
    }

    public void printByLevel(BSTDemo panel, Graphics g) {
        int height = height();
        for (int depth = 1; depth <= height; depth++) {
            i_printByLevel(root, depth, panel, g);
            System.out.println("");
        }
    }

    private void i_printByLevel(TreeNode nd, int depth, BSTDemo panel, Graphics g) {
        if (nd == null) {
            return;
        }
        if (depth == 1) {
            System.out.print(nd + " ");
            drawNode(panel, g, nd);
        } else {
            i_printByLevel(nd.left, depth - 1, panel, g);
            i_printByLevel(nd.right, depth - 1, panel, g);
        }
    }

    public V find(K key) {
        return i_find(root, key);
    }

    private V i_find(TreeNode nd, K key) {
        if (nd == null) {
            return null;
        }
        int cmp = key.compareTo(nd.key);
        if (cmp < 0) {
            return i_find(nd.left, key);
        } else if (cmp > 0) {
            return i_find(nd.right, key);
        } else {
            return nd.value;
        }
    }

    public TreeNode deleteMin() {
        return i_deleteMin(root);
    }

    private TreeNode i_deleteMin(TreeNode nd) {
        if (nd.left == null) {
            return nd.right;
        }
        nd.left = i_deleteMin(nd.left);
        return nd;
    }

    public TreeNode minimum() {
        return i_minimum(root);
    }

    private TreeNode i_minimum(TreeNode nd) {
        if (nd.left == null) {
            return nd;
        }
        return i_minimum(nd.left);
    }

    public void delete(K key) {
        root = i_delete(root, key);
    }

    private TreeNode i_delete(TreeNode nd, K key) {
        if (nd == null) {
            return null;
        }
        int cmp = key.compareTo(nd.key);
        if (cmp < 0) {
            nd.left = i_delete(nd.left, key);
        } else if (cmp > 0) {
            nd.right = i_delete(nd.right, key);
        } else {
            if (nd.right == null) {
                return nd.left;
            }
            if (nd.left == null) {
                return nd.right;
            }
            TreeNode old = nd;
            nd = i_minimum(old.right);
            nd.right = i_deleteMin(old.right);
            nd.left = old.left;
        }
        return nd;
    }
}
