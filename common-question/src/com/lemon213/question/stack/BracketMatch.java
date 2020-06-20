package com.lemon213.question.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BracketMatch {

    public static void main(String[] args) {
        String str = "{][()[{}]}";
        System.out.println(isMatch(str));
    }

    public static boolean isMatch(String str) {
        Stack<Character> stack = new Stack<>();
        char[] chars = str.toCharArray();
        for (char b : chars) {
            if (stack.empty()) {
                stack.push(b);
                continue;
            }

            Character a = stack.peek();
            if (isMatch(a, b)) {
                stack.pop();
            } else {
                stack.push(b);
            }
        }

        return stack.empty();
    }

    private static boolean isMatch(Character a, Character b) {
        if (a == null || b == null) {
            return false;
        }

        Map<Character, Character> matchs = new HashMap<Character, Character>() {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};

        return matchs.get(a) == b;
    }
}
