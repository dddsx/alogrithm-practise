package tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal_94 {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int x) {
            val = x;
        }
    }
    
    // 递归方式
    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            visit(root, list);
            return list;
        }
        
        public void visit(TreeNode t, List<Integer> list) {
            if (t == null) {
                return;
            }
            visit(t.left, list);
            list.add(t.val);
            visit(t.right, list);
        }
    }
    
    // 非递归方式
    static class Solution2 {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            while (curr != null || !stack.isEmpty()) {
                while (curr != null) {
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                result.add(curr.val);
                curr = curr.right;
            }
            return result;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        List<Integer> result = new Solution().inorderTraversal(root);
        System.out.println(Arrays.toString(result.toArray()));
        result = new Solution2().inorderTraversal(root);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
