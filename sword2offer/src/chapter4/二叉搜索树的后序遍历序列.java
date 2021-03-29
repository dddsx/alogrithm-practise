package chapter4;

public class 二叉搜索树的后序遍历序列 {

    static class Solution {
        public boolean verifyPostorder(int[] postorder) {
            if (postorder == null || postorder.length == 0) {
                return false;
            }
            int length = postorder.length;

            int root = postorder[length - 1];
            // 在二叉搜索树中左子树节点的值小于根节点的值
            int i = 0;
            for (; i < length - 1; i++) {
                if (postorder[i] > root)
                    break;
            }

            // 在二叉搜索树中右子树节点的值大于根节点的值
            int j = i;
            for (; j < length - 1; j++) {
                if (postorder[j] < root)
                    return false;
            }

            // 判断左子树是不是二叉搜索树
            boolean left = true;
            if (i > 0) {
                int[] leftorder = new int[i];
                System.arraycopy(postorder, 0, leftorder, 0, i);
                left = verifyPostorder(leftorder);
            }

            // 判断右子树是不是二叉搜索树
            boolean right = true;
            if (i < length - 1) {
                int[] rightorder = new int[length - i - 1];
                System.arraycopy(postorder, i, rightorder, 0, length - i - 1);
                right = verifyPostorder(rightorder);
            }

            return left && right;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().verifyPostorder(new int[]{4, 8, 6, 12, 16, 14, 10}));
    }
}
