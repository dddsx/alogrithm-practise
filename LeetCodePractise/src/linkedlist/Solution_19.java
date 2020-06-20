package linkedlist;

public class Solution_19 {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            ListNode p = dummy;
            ListNode q = p;
            // 根据n计算p和q的步长
            while (n-- > 0) {
                q = q.next;
            }

            while (q != null && q.next != null) {
                q = q.next;
                p = p.next;
            }
            p.next = p.next.next;
            return dummy.next;
        }
    }
}
