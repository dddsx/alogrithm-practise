package chapter2;

public class PrintListReversingly_6 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {

        public int[] reversePrint(ListNode head) {
            if (head == null) {
                return new int[0];
            }

            int len = 1;
            ListNode curr = head;
            while ((curr = curr.next) != null) {
                len++;
            }

            int[] result = new int[len];
            printNext(head, result, len - 1);
            return result;
        }

        private void printNext(ListNode node, int[] arr, int idx) {
            if (node.next != null) {
                printNext(node.next, arr, idx - 1);
            }

            arr[idx] = node.val;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,2};

        ListNode head = new ListNode(arr[0]);
        ListNode curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new ListNode(arr[i]);
            curr = curr.next;
        }

        new Solution().reversePrint(head);
    }
}
