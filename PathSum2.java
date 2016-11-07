import java.util.*;


public class PathSum2 {
    
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    private static void pathSumRecursive(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
        if (root != null) {
            path.add(root.val);
            if (root.left == null && root.right == null && root.val == sum) {
                result.add(new ArrayList<Integer>(path));
            }       
            pathSumRecursive(root.left, sum - root.val, path, result);
            pathSumRecursive(root.right, sum - root.val, path, result);
            path.remove(path.size()-1);
        }
        
    }
    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumRecursive(root, sum, path, result);
        return result;
    }

    public static void main(String[] args) {

    }
}