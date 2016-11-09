/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    
    //Using Hashmap, O(n) time and O(n) space
    private static TreeNode buildTreeRecursive(int[] inorder, int startInorder, int endInorder,
            int[] preorder, int startPreorder, int endPreorder, Map<Integer, Integer> inorderMap) {
        if (startInorder <= endInorder) {
            TreeNode root = new TreeNode(preorder[startPreorder]);
            int inorderRootId = inorderMap.get(root.val);
            int newEndPreorderRight = startPreorder + inorderRootId - startInorder;
            root.left = buildTreeRecursive(inorder, startInorder, inorderRootId - 1, preorder, 
                    startPreorder + 1, newEndPreorderRight, inorderMap);
            root.right = buildTreeRecursive(inorder, inorderRootId + 1, endInorder, preorder,
                    newEndPreorderRight + 1, endPreorder, inorderMap);
            return root;
        }
        return null;
    }
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeRecursive(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1,
                inorderMap);
    }
}