package gui_tree;

public class Tree {

    TreeNode root;
    TreeNode temp;
    static int howMany = 0;
    static int w = 120, h = 80;

    public Tree() {
        this.root = null;
    }

    public void add(int data) {
        TreeNode node = new TreeNode(data);

        if (this.root == null) {
            this.root = node;
            this.root.x = 500;
            this.root.y = 10;
            howMany++;
        } else {
            boolean ok = true;
            temp = root;
            while (ok) {
                if (temp.data > data) {
                    if (temp.cabangKiri == null) {
                        temp.cabangKiri = node;
                        temp.cabangKiri.x = temp.x - w;
                        temp.cabangKiri.y = temp.y + h;
                        node.setParent(temp);
                        howMany++;
                        w -= 5;
                        ok = false;
                    } else {
                        temp = temp.cabangKiri;
                    }
                } else {
                    if (temp.cabangKanan == null) {
                        temp.cabangKanan = node;
                        temp.cabangKanan.x = temp.x + w;
                        temp.cabangKanan.y = temp.y + h;
                        node.setParent(temp);
                        howMany++;
                        w -= 5;
                        ok = false;
                    } else {
                        temp = temp.cabangKanan;
                    }
                }
            }
        }
    }

    public boolean isLeaf(TreeNode data) {
        boolean cek = false;
        TreeNode ptr = data;
        if (ptr.cabangKiri == null && ptr.cabangKanan == null) {
            cek = true;
        }
        return cek;
    }

    public boolean delete(int data) {
        boolean cek = false;
        TreeNode node = Search_Node(data);
        if (node != null) {
            if (!isLeaf(node)&& (node.cabangKiri != null && node.cabangKanan != null)) {
                cek = delete_2_anak(node);
            } else if (!isLeaf(node)&& (node.cabangKiri != null || node.cabangKanan != null)) {
                cek = delete_1_anak(node);
            } else {
                cek = delete_0_anak(node);
            }
        }
        return cek;
    }

    public boolean delete_1_anak(TreeNode data) {
        TreeNode parent = data.getParent();
        TreeNode temp = data;

        if (temp != root) {
            if (parent.cabangKiri == temp) {
                if (temp.cabangKiri != null) { //jika cabang kiri temp tidak null
                    parent.setCabangKiri(temp.cabangKiri);
                    temp.cabangKiri.setParent(parent);
                } else {
                    parent.setCabangKiri(temp.cabangKanan);
                    temp.cabangKanan.setParent(parent);
                    pindah_kanan_ke_kiri(temp);

                    TreeNode bantu = temp.cabangKanan.cabangKiri;
                    if (bantu != null) {
                        pindah_naik(bantu);
                    }
                }
                temp.setParent(null);
            } else {
                if (temp.cabangKiri != null) { //jika cabang kanan temp tidak null
                    parent.setCabangKanan(temp.cabangKiri);
                    temp.cabangKiri.setParent(parent);
                    pindah_kiri_ke_kanan(temp);
                } else {
                    parent.setCabangKanan(temp.cabangKanan);
                    temp.cabangKanan.setParent(parent);
                }
                temp.setParent(null);
            }
        } else {
            if (data.cabangKanan != null) {//cabang kanan ada ==> cabang kiri kosong
                TreeNode n = GetSuccessor(temp);
                if (n != null) {
                    root.setData(n.getData());
                } else { //temp = root
                    if (isLeaf(temp.cabangKanan)) {
                        root.setData(temp.cabangKanan.getData());
                        root.setCabangKanan(null);
                    } else {
                        root.setData(temp.cabangKanan.getData());
                        root.setCabangKanan(temp.cabangKanan.cabangKanan);
                        temp.cabangKanan.setParent(root);
                    }
                }
            } else {//cabang kiri ada ==> cabang kanan kosong
                TreeNode n = getPredeccessor(temp);
                if (n != null) {
                    root.setData(n.getData());
                } else { // temp = root
                    if (isLeaf(temp.cabangKiri)) {
                        root.setData(temp.cabangKiri.getData());
                        root.setCabangKiri(null);
                    } else {
                        root.setData(temp.cabangKiri.getData());
                        root.setCabangKiri(temp.cabangKiri.cabangKiri);
                        temp.cabangKiri.setParent(root);
                    }
                }
            }
        }
        howMany -= 1;
        return true;
    }

    public boolean delete_0_anak(TreeNode data) {
        TreeNode parent = data.getParent();
        if (parent.cabangKiri == data) {
            parent.setCabangKiri(null);
            data.setParent(null);
        } else {
            parent.setCabangKanan(null);
            data.setParent(null);
        }
        howMany -= 1;
        return true;
    }

    public TreeNode Search_Node(int data) {
        TreeNode pointer = root;
        while (pointer.getData() != data) {
            if (data <= pointer.getData()) {
                pointer = pointer.cabangKiri;
            } else {
                pointer = pointer.cabangKanan;
            }
            if (pointer == null) {
                return null;
            }
        }
        return pointer;
    }

    //pertemua ke 6
    public boolean delete_2_anak(TreeNode node) {
        boolean cek = false;
        TreeNode parent = node.getParent();
        TreeNode temp = node;
        if (node != root) {
            if (parent.cabangKanan == temp) {
                if (temp.cabangKanan != null) {
                    parent.setCabangKanan(temp.cabangKanan);                    // cabang kanan parent ambil cabang kanan temp
                    temp.cabangKanan.setParent(parent);                      //cabang kanan temp ambil parent 
//
//                    TreeNode bantu = node.cabangKanan; //bantu 39
//                    System.out.println(bantu.getData());
//                    if (bantu.cabangKiri != null) {
//                        bantu.setParent(temp);
//                        temp.setCabangKanan(bantu);
//                        pindah_kiri_ke_kanan(bantu);
//                        
//                    } else if (bantu.cabangKanan != null) {
//                        bantu.setCabangKiri(bantu.cabangKanan);
//                        bantu.cabangKanan.setParent(bantu);
//                        pindah_kanan_ke_kiri(bantu);
//                    } else {
//
//                    }
                    temp.cabangKanan.setCabangKiri(temp.cabangKiri);
                    temp.cabangKiri.setParent(temp.cabangKanan);

                } else {
                    parent.setCabangKiri(temp.cabangKiri);
                    temp.cabangKiri.setParent(parent);
                    temp.cabangKiri.setCabangKanan(temp.cabangKanan);
                    temp.cabangKanan.setParent(temp.cabangKiri);
                }
                temp.setParent(null);
                cek = true;
            } else if (parent.cabangKiri == temp) {
                if (isLeaf(temp.cabangKanan)) {
                    TreeNode GetSuccessor = GetSuccessor(temp);
                    temp.setData(GetSuccessor.getData());
                    temp.setCabangKanan(null);
                } else {
                    TreeNode GetSuccessor = GetSuccessor(temp);
                    
                    if (GetSuccessor == null) {
                        parent.setCabangKiri(temp.cabangKanan);
                        temp.cabangKanan.setParent(parent);
                        temp.cabangKanan.setCabangKiri(temp.cabangKiri);
                        temp.cabangKiri.setParent(temp.cabangKanan);
                        pindah_kanan_ke_kiri(temp);
                        do {                         
                            temp = temp.cabangKanan;
                            pindah_kanan_ke_kiri(temp);
                        } while (temp.cabangKanan != null);
                    }
                    else{
                        temp.setData(GetSuccessor.getData());
                    }
                }
                cek = true;
            }
        } else {
            TreeNode getPredeccessor = getPredeccessor(temp);
            root.setData(getPredeccessor.getData());
            cek = true;
        }
        howMany -= 1;
        return cek;
    }

    public TreeNode GetSuccessor(TreeNode node) {
        TreeNode Current, Successor, SuccessorParent;
        Successor = node;
        SuccessorParent = node;
        Current = node.cabangKanan;
        while (Current != null) {
            SuccessorParent = Successor;
            Successor = Current;
            Current = Current.cabangKiri;
        }

        if (Successor != node.cabangKanan) {
            SuccessorParent.setCabangKiri(Successor.cabangKanan);
            Successor.setCabangKanan(node.cabangKanan);
        } else {
            Successor = null;
        }
        return Successor;
    }

    public TreeNode getPredeccessor(TreeNode node) {
        TreeNode Current, Predeccessor, PredeccessorParent;
        Predeccessor = node;
        PredeccessorParent = node;
        Current = node.cabangKiri;
        while (Current != null) {
            PredeccessorParent = Predeccessor;
            Predeccessor = Current;
            Current = Current.cabangKanan;
        }
        if (Predeccessor != node.cabangKiri) {
            PredeccessorParent.setCabangKanan(Predeccessor.cabangKiri);
            Predeccessor.setCabangKiri(node.cabangKiri);
        } else {
            Predeccessor = null;
        }
        return Predeccessor;
    }

    public void pindah_kiri_ke_kanan(TreeNode node) {
        node.cabangKiri.x = node.x;
        node.cabangKiri.y = node.y;
    }

    public void pindah_kanan_ke_kiri(TreeNode node) {
        node.cabangKanan.x = node.x;
        node.cabangKanan.y = node.y;
    }

    public void pindah_naik(TreeNode node) {
        node.x = node.parent.x - w;
        node.y = node.parent.y + h;
    }
}
