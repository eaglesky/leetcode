/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;

public class SerializeDeserializeBST {

    private static void serializeRecur(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }
        String str = String.valueOf(root.val);
        sb.append(str);
        sb.append("#");
        serializeRecur(root.left, sb);
        serializeRecur(root.right, sb);
    }
    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeRecur(root, sb);
        return sb.toString();
    }

    private static TreeNode deserializeRecur(Integer[] values, int[] start, int minValue, int maxValue) {
        if (start[0] >= values.length || start[0] < 0
            || values[start[0]] < minValue || values[start[0]] > maxValue) {
                return null;
        }
        int curValue = values[start[0]++];
        TreeNode curNode = new TreeNode(curValue);
        curNode.left = deserializeRecur(values, start, minValue, curValue - 1);
        curNode.right = deserializeRecur(values, start, curValue + 1, maxValue);
        return curNode;
    }
    
    // Decodes your encoded data to tree.
    // O(n) time and O(n) space
    public TreeNode deserialize(String data) {
        List<Integer> valueList = new ArrayList<>();
        int curValue = 0;
        int curSign = 1;
        for (int i = 0; i < data.length(); ++i) {
            char c = data.charAt(i);
            if (c == '-') {
                curSign = -1;
            } else if (c == '#') {
                valueList.add(curValue * curSign);
                curValue = 0;
                curSign = 1;
            } else if (Character.isDigit(c)) {
                curValue = 10 * curValue + (c - '0');
            }
        }
        Integer[] values = valueList.toArray(new Integer[0]);
        return deserializeRecur(values, new int[]{0}, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
    	SerializeDeserializeBST codec = new SerializeDeserializeBST();
    	Integer[][] tests = {
    		{2, 1, 3}
    	};
    	for (Integer[] test : tests) {
    		TreeNode root = TreeNode.createFromArray(test);
    		String serialized = codec.serialize(root);
    		System.out.println(serialized);
    		TreeNode deserialized = codec.deserialize(serialized);
    		List<Integer> result = TreeNode.printLevels(deserialized);
    	}
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));