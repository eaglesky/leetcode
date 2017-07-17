/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
*/

public class InorderSuccessorBST {

	//O(h) time and O(1) space
	//Assuming BST has no duplicates!
	public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		if (p == null) {
			return null;
		}
		int key = p.val;
		TreeNode successor = null;
		for(TreeNode cur = root; cur != null;) {
			if (key < cur.val) {
				successor = cur;
				cur = cur.left;
			} else {
				cur = cur.right;
			}
		}
		return successor;
	}

	public static void main (String[] args) {
		Integer[] t1 = new Integer[]{9, 4, 10, 2, 6, null, 13, null, null, 5, null, 11, null};
		TreeNode root = TreeNode.createFromArray(t1);
		TreeNode.printLevels(root);
		TreeNode curNode = root.right.right;
		TreeNode successor = inorderSuccessor(root, curNode);
		System.out.println("successor of " + curNode.val + " is "
			+ ((successor == null) ? null : successor.val));
	}
}