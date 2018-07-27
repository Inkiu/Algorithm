import java.util.Arrays;
import java.util.Random;

public class MinAbsSumOfTwo {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] {-8, 4, 5, -10, 3}));
        System.out.println(s.bruteForce(new int[] {-8, 4, 5, -10, 3}));

        int[] test = makeRandom();
        for (int i = 0; i < 100; i++) {
            System.out.println(s.solution(test) + " ... " + s.bruteForce(test));
            System.out.println(Arrays.toString(test));
            if (s.solution(test) != s.bruteForce(test)) {
                break;
            }
            test = makeRandom();
        }
    }

    static class Solution {
        int solution(int[] A) {

            Arrays.sort(A); // N logN

            int left = -1;
            int right = -1;

            for (int i = 0; i < A.length; i++) {
                if (A[i] < 0) left = i;
                if (A[i] >= 0 && right == -1) right = i;
            }

            if (left == -1) return Math.abs(A[right] * 2);
            if (right == -1) return Math.abs(A[left] * 2);

            int target, minIndex, next;
            if (Math.abs(A[left]) > Math.abs(A[right])) {
                target = A[left];
                minIndex = right;
                next = 1;
            } else {
                target = A[right];
                minIndex = left;
                next = -1;
            }


            for (int i = minIndex; i < A.length && i > 0; i += next) {
                if (Math.abs(A[minIndex]) < Math.abs(target)) minIndex = i;
                else {
                    int last = Math.abs(A[minIndex] + Math.abs(target));
                    int curr = Math.abs(A[i] + Math.abs(target));
                    if (last > curr) {
                        break;
                    }
                    else {
                        minIndex -= next;
                        break;
                    }
                }
            }

            return Math.min(Math.abs(A[left] + A[left]),
                    Math.min(Math.abs(A[right] + A[right]), Math.abs(target + A[minIndex])));
//            return Math.abs(target + A[minIndex]);
        }

        int bruteForce(int[] A) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < A.length; i++) {
                for (int j = i; j < A.length; j++) {
                    min = Math.min(Math.abs(A[i] + A[j]), min);
                }
            }
            return min;
        }
    }

    static int[] makeRandom() {
        Random r = new Random();
        int[] t = new int[r.nextInt(10) + 1];
        for (int i = 0; i < t.length; i++) {
            t[i] = r.nextInt(200) - 100;
        }
        return t;
    }
}
