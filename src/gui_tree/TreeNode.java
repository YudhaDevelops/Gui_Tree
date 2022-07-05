package gui_tree;

public class TreeNode {

    int data;
    TreeNode cabangKanan;
    TreeNode cabangKiri;
    TreeNode parent;
    int x, y;

    public TreeNode(int data) {
        this.data = data;
    }

    public TreeNode() {
        data = (Integer) null;
        cabangKanan = null;
        cabangKiri = null;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public TreeNode getCabangKanan() {
        return cabangKanan;
    }

    public void setCabangKanan(TreeNode cabangKanan) {
        this.cabangKanan = cabangKanan;
    }

    public TreeNode getCabangKiri() {
        return cabangKiri;
    }

    public void setCabangKiri(TreeNode cabangKiri) {
        this.cabangKiri = cabangKiri;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }
    
    
    
}
