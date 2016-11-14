import java.util.*;

public class DeleteNodeInBST {
    
    //Return [node with the key, parent node]
    private static TreeNode[] findNodes(TreeNode root, int key) {
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null && cur.val != key) {
            pre = cur;
            if (key < cur.val) {
                cur = cur.left;
            } else if (key > cur.val) {
                cur = cur.right;
            }
        }
        return new TreeNode[]{cur, pre};
    }
    
    private static TreeNode transplant(TreeNode root, TreeNode pre, TreeNode next, boolean isLeftChild) {
        if (pre != null) {
            if (isLeftChild) {
                pre.left = next;
            } else {
                pre.right = next;
            }
            return root;
        } else {
            return next;
        }
    }
    
    public static TreeNode deleteNode(TreeNode root, int key) {
        TreeNode[] nodes = findNodes(root, key);
        if (nodes[0] == null) {
            return root;
        }
        boolean isLeftChild = (nodes[1] != null && nodes[1].left == nodes[0]);
        TreeNode curNode = nodes[0];
        TreeNode preNode = nodes[1];
        if (root == null || curNode == null) {
            return root;
        }
        if (curNode.left == null) {
            return transplant(root, preNode, curNode.right, isLeftChild);
        } else if (curNode.right == null) {
            return transplant(root, preNode, curNode.left, isLeftChild);
        } else {
            TreeNode rightNode = curNode.right;
            TreeNode preRightNode = curNode;
            for(; rightNode.left != null; preRightNode = rightNode, rightNode = rightNode.left);
            if (rightNode == curNode.right) {
                rightNode.left = curNode.left;
                return transplant(root, preNode, rightNode, isLeftChild);
            } else {
                transplant(root, preRightNode, rightNode.right, true);
                rightNode.left = curNode.left;
                rightNode.right = curNode.right;
                return transplant(root, preNode, rightNode, isLeftChild);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] t1 = new Integer[]{5, 3, 6, 2, 4, null, 7};
        TreeNode root = TreeNode.createFromArray(t1);
        TreeNode root2 = deleteNode(root, 3);
        List<Integer> result = TreeNode.printLevels(root2);
    }
}