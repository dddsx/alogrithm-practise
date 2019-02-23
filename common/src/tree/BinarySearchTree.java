package tree;

import java.util.Comparator;

/**
 * 《数据结构与算法分析Java语言描述》P78二叉查找树算法实现
 * @author xiaobu
 * @date 2019-2-17 14:24:28
 */
public class BinarySearchTree<T extends Comparable<? super T>> {
    
    private BinaryNode<T> root;
    
    private Comparator<? super T> cmp;
    
    public BinarySearchTree() {
        root = null;
    }
    
    public BinarySearchTree(Comparator<? super T> c) {
        root = null;
        cmp = c;
    }
    
    private static class BinaryNode<T> {
        
        T element;
        
        BinaryNode<T> left;
        
        BinaryNode<T> right;
        
        BinaryNode(T element) {
            this(element, null, null);
        }
        
        BinaryNode(T element, BinaryNode<T> lt, BinaryNode<T> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
        }
    }
    
    public void makeEmpty() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public boolean contains(T x) {
        return contains(x, root);
    }
    
    public T findMin() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMin(root).element;
    }
    
    public T findMax() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return findMax(root).element;
    }
    
    public void insert(T x) {
        root = insert(x, root);
    }
    
    public void remove(T x) {
        root = remove(x, root);
    }
    
    /**
     * 中序遍历二叉排序树，结果应该是有序的
     */
    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }
    
    private boolean contains(T x, BinaryNode<T> t) {
        if (t == null) {
            return false;
        }
        
        int compareResult = x.compareTo(t.element);
        
        if (compareResult < 0) {
            return contains(x, t.left);
        } else if (compareResult > 0) {
            return contains(x, t.right);
        } else {
            return true;
        }
    }
    
    private BinaryNode<T> findMin(BinaryNode<T> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        }
        return findMin(t.left);
    }
    
    private BinaryNode<T> findMax(BinaryNode<T> t) {
        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }
        return t;
    }
    
    private BinaryNode<T> insert(T x, BinaryNode<T> t) {
        if (t == null) {
            return new BinaryNode<>(x, null, null);
        }
        
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // Duplicate;do nothing
        }
        return t;
    }
    
    private BinaryNode<T> remove(T x, BinaryNode<T> t) {
        if (t == null) {
            return null;
        }
        
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else {
            if (t.left != null && t.right != null) {
                // 效率上有缺陷，因为它沿该树进行两趟搜索以查找和删除右子树中最小的节点，可以写一个removeMin方法解决该问题
                t.element = findMin(t.right).element;
                t.right = remove(t.element, t.right);
            } else {
                t = (t.left != null) ? t.left : t.right;
            }
        }
        return t;
    }
    
    private int myCompare(T lhs, T rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return lhs.compareTo(rhs);
        }
    }
    
    private void printTree(BinaryNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private int height(BinaryNode<T> t) {
        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.left), height(t.right));
        }
    }
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        final int NUMS = 100;
        final int GAP = 37;
        
        System.out.println("Checking... (no more output means success)");
        
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            t.insert(i);
        }
    
        t.printTree();
        
        for (int i = 1; i < NUMS; i += 2) {
            t.remove(i);
        }
    
        t.printTree();
        
        if (t.findMin() != 2 || t.findMax() != NUMS - 2) {
            System.out.println("FindMin or FindMax error!");
        }
        
        for (int i = 2; i < NUMS; i += 2) {
            if (!t.contains(i)) {
                System.out.println("Find error1!");
            }
        }
        
        for (int i = 1; i < NUMS; i += 2) {
            if (t.contains(i)) {
                System.out.println("Find error2!");
            }
        }
    }
}
