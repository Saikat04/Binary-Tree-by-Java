/*
 * Diameater of a tree
 */
import java.util.*;

public class diameaterOfATree {

    public static class Node{
        int data;
        Node left;
        Node right;
        Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);
            
            return newNode;
        }
    }
    public static int treeLevels(Node root){
        if(root == null){
            return 0;
        }
        int leftHeight = treeLevels(root.left);
        int rightHeight = treeLevels(root.right);
        int heightOfTree = Math.max(leftHeight,rightHeight)+1;
        return heightOfTree;
    }


    // O(N^2)
    public static int diameater(Node root){
        if(root == null){
            return 0;
        }
        int dim1 = diameater(root.left);
        int dim2 = diameater(root.right);
        int dim3 = treeLevels(root.left) + treeLevels(root.right) + 1;
        return Math.max(dim1,Math.max(dim2,dim3));
    }

    //O(N)
    static class TreeInfo {
        int ht;
        int diam;
        TreeInfo(int ht, int diam){
            this.ht = ht;
            this.diam = diam;
        }
    }

    public static TreeInfo diameter2(Node root) {
        if(root == null){
            return new TreeInfo(0, 0);
        }
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);
        int myHeight = Math.max(left.ht, right.ht) + 1;
        
        int diam1 = left.diam;
        int diam2 = right.diam;
        int diam3 = left.ht + right.ht + 1;
        int myDiam = Math.max(diam1, Math.max(diam2 , diam3));
        
        TreeInfo myInfo = new TreeInfo(myHeight , myDiam);
        return myInfo;
    }

    
    public static void main(String arg[]) {
        int nodes[] = {1, 2, 4,-1, -1 , 5, -1, -1, 3, -1, 6, -1, -1};
        
        BinaryTree tree = new BinaryTree();
        Node root = tree.buildTree(nodes);   
        TreeInfo count = diameter2(root);
        System.out.println(count.diam);

    }
}
