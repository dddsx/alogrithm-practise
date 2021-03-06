package linkedlist;

public class AddTwoNumbers_2 {
    
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        node2.next = new ListNode(9);
        ListNode result = new Solution().addTwoNumbers(node1, node2);
        System.out.print("[");
        while (true){
            if(result.next != null) {
                System.out.print(result.val + ", ");
            }
            else {
                System.out.print(result.val + "]");
                break;
            }
            result = result.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = l1;
            int flag = 0;
            int sum;
            if(l1 != null && l2 != null){
                while (true) {
                    sum = l1.val + l2.val + flag;
                    flag = sum / 10;
                    l1.val = sum % 10;
                    if(l1.next != null && l2.next != null) {
                        l1 = l1.next;
                        l2 = l2.next;
                    } else {
                        break;
                    }
                }
            }
            if(l2.next != null){
                l1.next = l2.next;
            } else if (l1.next == null) {
                if (flag == 1) {
                    l1.next = new ListNode(1);
                    return head;
                }
            }
            while (l1.next != null){
                sum = l1.next.val  + flag;
                flag = sum / 10;
                l1.next.val  = sum % 10;
                l1 = l1.next;
            }
            if(flag == 1){
                l1.next = new ListNode(1);
            }
            return head;
        }
    }
    
    static class PerfectSolution {
        
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode p = l1;
            ListNode q = l2;
            ListNode curr = dummyHead;

            int carry = 0;
            while (p != null || q != null) {
                int x = (p != null) ? p.val : 0;
                int y = (q != null) ? q.val : 0;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p != null) {
                    p = p.next;
                }
                if (q != null) {
                    q = q.next;
                }
            }

            if (carry > 0) {
                curr.next = new ListNode(carry);
            }

            return dummyHead.next;
        }
    }
}

