import java.util.Stack;

/**
 * 문제 : https://app.codility.com/programmers/lessons/7-stacks_and_queues/
 */

public class StoneWall {

    public static void main(String[] args) {
        Solution s = new Solution();
        BruteForce b = new BruteForce();
        System.out.println(s.solution(new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 }));
        System.out.println(b.solution(new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 }));
    }

    static class Solution {
        public int solution(int[] A) {
            Stack<Integer> stack = new Stack<>();
            int ret = 0;
            for (int i = 0; i < A.length; i++) {
                int a = A[i];
                if (stack.empty() || stack.peek() < a) stack.push(a);
                else if (stack.peek() == a) continue;
                else { // stack.peek() > a
                    stack.pop();
                    ret ++;
                    i = i - 1;
                }
            }

            return ret + stack.size();
        }
    }

    static class BruteForce {
        public int solution(int[] A) {
            return Integer.MIN_VALUE;
        }

        int[] removeMin(int[] source) {
            return null;
        }
    }
}
