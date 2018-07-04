import java.util.Stack;

/**
 * 문제 : https://app.codility.com/programmers/lessons/8-leader/equi_leader/
 */

public class EquiLeader {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] { 4, 3, 4, 4, 4, 2 }));
        System.out.println(s.solution(new int[] { 4 }));
        System.out.println(s.solution(new int[] { 4, 2, 2, 2, 2, 2 }));
    }

    static class Solution {
        public int solution(int[] A) {
            Stack<Integer> stack = new Stack<>();
            for (int a: A) {
                if (stack.empty()) stack.push(a);
                else if (stack.peek() != a) stack.pop();
                else stack.push(a);
            }
            if (stack.empty()) return 0;
            int leaderCandidate = stack.pop();
            int targetCount = A.length / 2 + 1;
            int count = 0;
            for (int a: A) {
                if (a == leaderCandidate) count ++;
            }
            if (targetCount > count) return 0;

            int leader = leaderCandidate;
            int leftCount = 0;
            int leftLength = 0;
            int rightCount = count;
            int rightLength = A.length;
            int equalCount = 0;
            for (int i = 0; i < A.length; i ++) {
                if (A[i] == leader) {
                    leftCount ++;
                    rightCount --;
                }
                leftLength ++;
                rightLength --;

                if (leftLength / 2 + 1 <= leftCount && rightLength / 2 + 1 <= rightCount) equalCount ++;
            }

            return equalCount;
        }

    }


}
