/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class ConstructBinaryTreeInorderPostorder {
    
    //Without using hashmap
    private static TreeNode buildTreeRecursive0(int[] inorder, int startInorder, int endInorder,
            int[] postorder, int startPostorder, int endPostorder) {
        if (startInorder <= endInorder) {
            TreeNode root = new TreeNode(postorder[endPostorder]);
            int i = startInorder;
            for(; i <= endInorder && inorder[i] != root.val; ++i);
            int newEndPostorderRight = startPostorder + i -1 - startInorder;
            root.left = buildTreeRecursive0(inorder, startInorder, i-1, postorder, 
                    startPostorder, newEndPostorderRight);
            root.right = buildTreeRecursive0(inorder, i+1, endInorder, postorder,
                    newEndPostorderRight + 1, endPostorder-1);
            return root;
        }
        return null;
    }
            
    public TreeNode buildTree0(int[] inorder, int[] postorder) {
        return buildTreeRecursive0(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
    
    //Using Hashmap, O(n) time and O(n) space
    private static TreeNode buildTreeRecursive(int[] inorder, int startInorder, int endInorder,
            int[] postorder, int startPostorder, int endPostorder, Map<Integer, Integer> inorderMap) {
        if (startInorder <= endInorder) {
            TreeNode root = new TreeNode(postorder[endPostorder]);
            int inorderRootId = inorderMap.get(root.val);
            int newEndPostorderRight = startPostorder + inorderRootId -1 - startInorder;
            root.left = buildTreeRecursive(inorder, startInorder, inorderRootId - 1, postorder, 
                    startPostorder, newEndPostorderRight, inorderMap);
            root.right = buildTreeRecursive(inorder, inorderRootId + 1, endInorder, postorder,
                    newEndPostorderRight + 1, endPostorder-1, inorderMap);
            return root;
        }
        return null;
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for(int i = 0; i < inorder.length; ++i) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreeRecursive(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1,
                inorderMap);
    }
}