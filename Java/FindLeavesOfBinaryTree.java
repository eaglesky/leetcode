/*
Given a binary tree, find all leaves and then remove those leaves. Then repeat 
the previous steps until the tree is empty.

Example:
Given binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Returns [4, 5, 3], [2], [1].

Explanation:
1. Remove the leaves [4, 5, 3] from the tree

          1
         / 
        2          
2. Remove the leaf [2] from the tree

          1          
3. Remove the leaf [1] from the tree

          []         
Returns [4, 5, 3], [2], [1].
*/
import java.util.*;

public class FindLeavesOfBinaryTree {

	private static int disToLeaf(TreeNode node, List<List<Integer>> disNodes) {

		int curDis = 0;
		if (node.left != null && node.right != null) {
			curDis = Math.max(disToLeaf(node.left, disNodes),
							  disToLeaf(node.right, disNodes)) + 1;
		} else if (node.left != null) {
			curDis = disToLeaf(node.left, disNodes) + 1;
		} else if (node.right != null) {
			curDis = disToLeaf(node.right, disNodes) + 1;
		}
		List<Integer> curDisNodes = null;
		if (curDis >= disNodes.size()) {
			curDisNodes = new ArrayList<Integer>();
			disNodes.add(curDisNodes);
		} else {
			curDisNodes = disNodes.get(curDis);
		}
		curDisNodes.add(node.val);
		return curDis;
	}

	public List<List<Integer>> findLeaves(TreeNode root) {
		List<List<Integer>> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		disToLeaf(root, result);
		return result;
	}

	//Best implentation:
	//The order must be correct since two nodes of same height must belong
	//to two different branches of a certain subtree, and when we do postorder
	//traversal on that subtree, left one is always visited before the right one.
	private int getHeightAndLeaves(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return -1;
        }
        int leftHeight = getHeightAndLeaves(root.left, result);
        int rightHeight = getHeightAndLeaves(root.right, result);
        int height = Math.max(leftHeight, rightHeight) + 1;
        if (result.size() <= height) {
            result.add(new ArrayList<>());
        }
        result.get(height).add(root.val);
        return height;
    }
    
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        getHeightAndLeaves(root, result);
        return result;
    }

    //We can also solve it iteratively, but would require a map of node to height.

    
	public static void main(String[] args) {
		Integer[][] tests = new Integer[][]{
			{1, 2, 3, 4, 5}, //Result = [[4, 5, 3], [2], [1]]
			{1, 2, 3, null, 4, null, 6, null, 5, 7, 8, null, null, null, null, 9},
			//[[5, 7, 9], [4, 8], [2, 6], [3], [1]]

			{1},
			{}
		};
		FindLeavesOfBinaryTree solution = new FindLeavesOfBinaryTree();

		for (Integer[] test : tests) {
			TreeNode root = TreeNode.createFromArray(test);
			TreeNode.printLevels(root);
			List<List<Integer>> disNodes = solution.findLeaves(root);
			System.out.println(disNodes);
		}
	}
}