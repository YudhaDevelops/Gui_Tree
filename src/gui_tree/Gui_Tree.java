package gui_tree;
public class Gui_Tree {
    public static void main(String[] args) {
        Graphic_Tree tree = new Graphic_Tree();
//        tree.add(1);
//        tree.add(2);
//        tree.add(7);
//        tree.add(3);
//        tree.add(30);
//        tree.add(5);
//        tree.add(4);
        
        tree.add(40);
        tree.add(30);
        tree.add(35);
        tree.add(37);
        tree.add(25);
        tree.add(27);
        tree.add(39);
        tree.add(36);
        tree.add(38);
        
//        tree.delete(37);
//        tree.delete(30);
        tree.getReady(tree.root);
        tree.drawTree();
    }
    
}
