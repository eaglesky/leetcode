/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class SerializeDeserializeBinaryTree {

    private static void serializeRecur(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#");
            sb.append(",");
        } else {
            sb.append(String.valueOf(root.val));
            sb.append(",");
            serializeRecur(root.left, sb);
            serializeRecur(root.right, sb);
        }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeRecur(root, sb);
        return sb.toString();
    }

    private static TreeNode deserializeRecur(String[] splitted, int[] start) {
        if (start[0] < 0 || start[0] >= splitted.length) {
            return null;
        } else if (splitted[start[0]].equals("#")) {
            start[0]++;
            return null;
        }
        int val = Integer.parseInt(splitted[start[0]++]);
        TreeNode curNode = new TreeNode(val);
        curNode.left = deserializeRecur(splitted, start);
        curNode.right = deserializeRecur(splitted, start);
        return curNode;
    }
    
    // Decodes your encoded data to tree.
    // Using preorder traversal, O(n) time and O(n) space
    public TreeNode deserialize(String data) {
        String[] splitted = data.split(",");
        return deserializeRecur(splitted, new int[]{0});
    }
    
    // Another solution is just as is mentioned in the description,
    // using level traversal.
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));