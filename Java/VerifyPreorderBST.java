class VerifyPreorderBST {
    
    //O(n) time and O(n) space
    //At the begining of each iteration, assuming that the previous
    //elements form a valid preorder sequence of BST. Then the stack
    //contains the ancestors of current element such that the current
    //element is in the left subtree of each one of them. For each
    //current element, check and see if it is in the left subtree
    //of top element in stack or not, by comparing the two. In this
    //way we can infer where the current element is if the sequence is
    //a valid one of a BST, and also do the preorder traversal at the 
    //same time. 
    public boolean verifyPreorder0(int[] preorder) {
        Deque<Integer> stack = new ArrayDeque<>();
        Integer minVal = null;
        for (int i = 0; i < preorder.length;) {
            int num = preorder[i];
            if ((stack.isEmpty() || num < stack.peek())
                && (minVal == null || num > minVal)) {
                stack.push(num);
                ++i;
            } else if (num > stack.peek()) {
                minVal = stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }
    
    
    //Slight change of above using input as stack
    //O(n) time and O(1) space
    public boolean verifyPreorder(int[] preorder) {
        int top = 0;
        Integer minVal = null;
        for (int i = 0; i < preorder.length;) {
            int num = preorder[i];
            if ((top == 0 || num < preorder[top - 1])
                && (minVal == null || num > minVal)) {
                preorder[top++] = num;
                ++i;
            } else if (num > preorder[top - 1]) {
                minVal = preorder[--top];
            } else {
                return false;
            }
        }
        return true;
    }
    
    //The recursive solution is very similar to the one in Deserialize BST!
}