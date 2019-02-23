package tree;

/**
 * 《数据结构与算法分析Java语言描述》P86AVL树算法实现
 * @author xiaobu
 * @date 2019-2-17 17:39:02
 */
public class AvlTree<T extends Comparable<? super T>> {
    
    private AvlNode<T> root;
    
    private static final int ALLOWED_IMBALANCE = 1;
    
    public AvlTree() {
        root = null;
    }
    
    private static class AvlNode<T> {
        T element;
        AvlNode<T> left;
        AvlNode<T> right;
        int height;
        
        AvlNode(T element) {
            this(element, null, null);
        }
        
        AvlNode(T element, AvlNode<T> lt, AvlNode<T> rt) {
            this.element = element;
            this.left = lt;
            this.right = rt;
            this.height = 0;
        }
    }
    
    public void insert(T x) {
        root = insert(x, root);
    }
    
    public void remove(T x) {
        root = remove(x, root);
    }
    
    public void checkBalance() {
        checkBalance(root);
    }
    
    public T findMin() {
        if (isEmpty())
            throw new IllegalStateException();
        return findMin(root).element;
    }
    
    public T findMax() {
        if (isEmpty())
            throw new IllegalStateException();
        return findMax(root).element;
    }
    
    public boolean contains(T x) {
        return contains(x, root);
    }
    
    public void makeEmpty() {
        root = null;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public void printTree() {
        if (isEmpty())
            System.out.println("Empty tree");
        else
            printTree(root);
    }
    
    private AvlNode<T> insert(T x, AvlNode<T> t) {
        if (t == null) {
            return new AvlNode<>(x, null, null);
        }
        
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {
            // Duplicate;do nothing
        }
        return balance(t);
    }
    
    private AvlNode<T> remove(T x, AvlNode<T> t) {
        if (t == null)
            return null;   // Item not found; do nothing
        
        int compareResult = x.compareTo(t.element);
        
        if (compareResult < 0) {
            t.left = remove(x, t.left);
        }
        else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else {
            if (t.left != null && t.right != null) // Two children
            {
                t.element = findMin(t.right).element;
                t.right = remove(t.element, t.right);
            } else {
                t = (t.left != null) ? t.left : t.right;
            }
        }
        return balance(t);
    }
    
    // Assume t is either balanced or within one of being balanced
    private AvlNode<T> balance(AvlNode<T> t) {
        if (t == null)
            return null;
        
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }
    
    private int checkBalance(AvlNode<T> t) {
        if (t == null)
            return -1;
        
        if (t != null) {
            int hl = checkBalance(t.left);
            int hr = checkBalance(t.right);
            if (Math.abs(height(t.left) - height(t.right)) > 1 ||
                    height(t.left) != hl || height(t.right) != hr)
                System.out.println("OOPS!!");
        }
        
        return height(t);
    }
    
    /**
     * Rotate binary tree node with left child.
     * For AVL trees, this is a single rotation for case 1.
     * Update heights, then return new root.
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2) {
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }
    
    /**
     * Rotate binary tree node with right child.
     * For AVL trees, this is a single rotation for case 4.
     * Update heights, then return new root.
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k1) {
        AvlNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }
    
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child.
     * For AVL trees, this is a double rotation for case 2.
     * Update heights, then return new root.
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }
    
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child.
     * For AVL trees, this is a double rotation for case 3.
     * Update heights, then return new root.
     */
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }
    
    private AvlNode<T> findMin(AvlNode<T> t) {
        if (t == null)
            return t;
        
        while (t.left != null)
            t = t.left;
        return t;
    }
    
    private AvlNode<T> findMax(AvlNode<T> t) {
        if (t == null)
            return t;
        
        while (t.right != null)
            t = t.right;
        return t;
    }
    
    private boolean contains(T x, AvlNode<T> t) {
        while (t != null) {
            int compareResult = x.compareTo(t.element);
            if (compareResult < 0)
                t = t.left;
            else if (compareResult > 0)
                t = t.right;
            else
                return true;
        }
        return false;
    }
    
    private void printTree(AvlNode<T> t) {
        if (t != null) {
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }
    
    private int height(AvlNode<T> t) {
        return t == null ? -1 : t.height;
    }
    
    // Test program
    public static void main(String[] args) {
        AvlTree<Integer> t = new AvlTree<>();
        final int SMALL = 40;
        final int NUMS = 1000;  // must be even
        final int GAP = 37;
        
        System.out.println("Checking... (no more output means success)");
        
        for (int i = GAP; i != 0; i = (i + GAP) % NUMS) {
            //    System.out.println( "INSERT: " + i );
            t.insert(i);
            if (NUMS < SMALL)
                t.checkBalance();
        }
        
        for (int i = 1; i < NUMS; i += 2) {
            //   System.out.println( "REMOVE: " + i );
            t.remove(i);
            if (NUMS < SMALL)
                t.checkBalance();
        }
        if (NUMS < SMALL)
            t.printTree();
        if (t.findMin() != 2 || t.findMax() != NUMS - 2)
            System.out.println("FindMin or FindMax error!");
        
        for (int i = 2; i < NUMS; i += 2)
            if (!t.contains(i))
                System.out.println("Find error1!");
        
        for (int i = 1; i < NUMS; i += 2) {
            if (t.contains(i))
                System.out.println("Find error2!");
        }
        
        myTestCase();
    }
    
    public static void myTestCase() {
        AvlTree<Integer> t = new AvlTree<>();
        Integer[] arr1 = new Integer[]{3, 2, 1, 4, 5, 6, 7};
        Integer[] arr2 = new Integer[]{16, 15, 14, 13, 12, 11, 10, 8, 9};
    
        for (Integer i : arr1) {
            t.insert(i);
        }
    
        for (Integer i : arr2) {
            t.insert(i);
        }
    }
}
