/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
 

return its vertical order traversal as:

[
  [9],
  [3,15],
  [20],
  [7]
]
 

Given binary tree [3,9,20,4,5,2,7],

    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
 

return its vertical order traversal as:

[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
*/
import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
	static class MyTreeNode {
		final TreeNode node;
		final int col;
		MyTreeNode(TreeNode node, int col) {
			this.node = node;
			this.col = col;
		}
	}

	//Using level order traversal to do vertical order traversal.
	//O(n) time and O(n) space
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		Map<Integer, List<Integer>> colToNodes = new HashMap<>();
		int minCol = 0;
		int maxCol = 0;
		Deque<MyTreeNode> q = new ArrayDeque<>();
		q.offer(new MyTreeNode(root, 0));
		while (!q.isEmpty()) {
			MyTreeNode curNodeCol = q.poll();
			minCol = Math.min(minCol, curNodeCol.col);
			maxCol = Math.max(maxCol, curNodeCol.col);
			List<Integer> nodes = colToNodes.get(curNodeCol.col);
			if (nodes == null) {
				nodes = new ArrayList<>();
				colToNodes.put(curNodeCol.col, nodes);
			}
			nodes.add(curNodeCol.node.val);			
			TreeNode leftNode = curNodeCol.node.left;
			TreeNode rightNode = curNodeCol.node.right;
			if (leftNode != null) {
				q.offer(new MyTreeNode(leftNode, curNodeCol.col - 1));
			}
			if (rightNode != null) {
				q.offer(new MyTreeNode(rightNode, curNodeCol.col + 1));
			}
		}
		for (int i = minCol; i <= maxCol; ++i) {
			result.add(colToNodes.get(i));
		}
		return result;
	}

	public static void main(String[] args) {
		BinaryTreeVerticalOrderTraversal solution = new BinaryTreeVerticalOrderTraversal();
		Integer[][] testArrays = new Integer[][] {
			{3,9,20,null,null,15,7}, //[[9], [3, 15], [20], [7]]
			{3,9,20,4,5,2,7}, //[[4], [9], [3, 5, 2], [20], [7]]
			{3,4,6,null,7,null,8,null,9,12,null},//[[4], [3, 7], [6, 9, 12], [8]]
			{3,4,8,5,6,null,null,null,null,null,7},//[[5], [4], [3, 6], [8, 7]]
			{0}//[[0]]
		};
		for (Integer[] test : testArrays) {
			TreeNode root = TreeNode.createFromArray(test);
			TreeNode.printLevels(root);
			List<List<Integer>> result = solution.verticalOrder(root);
			System.out.println(result);
		}
	}
}