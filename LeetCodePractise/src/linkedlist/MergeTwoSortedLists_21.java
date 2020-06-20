package linkedlist;

public class MergeTwoSortedLists_21 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode p = l1;
            ListNode q = l2;

            // 添加一个头结点，保证代码优雅性
            ListNode n = new ListNode(-1);
            ListNode h = n;

            while (p != null && q != null) {
                if (p.val <= q.val) {
                    n.next = p;
                    p = p.next;
                } else {
                    n.next = q;
                    q = q.next;
                }
                n = n.next;
            }

            // n.next = p == null ? q : p;

            while (p != null) {
                n.next = p;
                n = n.next;
                p = p.next;
            }

            while (q != null) {
                n.next = q;
                n = n.next;
                q = q.next;
            }

            return h.next;
        }
    }
}
