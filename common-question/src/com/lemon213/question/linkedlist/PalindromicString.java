package com.lemon213.question.linkedlist;


/**
 * 给定一个单链表存储的字符串，判断是否为回文字符串
 */
public class PalindromicString {

    public static void main(String[] args) {
        String str = "aa";
        Node head = stringToLinkedList(str);
        System.out.println(isPalindromic(head));
    }

    private static Node stringToLinkedList(String str) {
        Node head = new Node();
        Node cur = head;
        for (int i = 0; i < str.length(); i++) {
            cur.val = str.charAt(i);
            if (i != str.length() - 1) {
                cur.next = new Node();
                cur = cur.next;
            }
        }
        return head;
    }

    private static boolean isPalindromic(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        Node pre = null;
        Node next;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;
    }

    static class Node {

        char val;

        Node next;
    }
}
