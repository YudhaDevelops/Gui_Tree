package gui_v3;

public class TestBinSearchTree {

    public static void main(String[] args) {
        BinSearchTree<Integer, String> bst
                = new BinSearchTree<Integer, String>();
        bst.add(new Integer(12), "Jane");
        bst.add(new Integer(5), "John");
        bst.add(new Integer(15), "Bill");
        bst.add(new Integer(7), "Eric");
        bst.add(new Integer(3), "Bruce");
        bst.printInOrder();
    }

}
