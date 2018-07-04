/**
 * 문제 : https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
 * 스코어 : https://app.codility.com/demo/results/trainingQHQCWZ-5SW/
 */
public class AbsDistinct {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] { -5, -3, -1, 0, 3, 6 }));
        System.out.println(s.solution(new int[] { -1, -1, -1, 0, 1, 2 }));
        System.out.println(s.solution(new int[] { -3, -2, -1, 1, 2 }));
        System.out.println(s.solution(new int[] { -3, -2, -1, -1, -1 }));
        System.out.println(s.solution(new int[] { 1, 2, 3, 3 }));
    }

    static class Solution {
        public int solution(int[] A) {
            int ret = 0;
            int indexOfZero = -1;
            for (int i = 0; i < A.length; i++) {
                if (A[i] == 0) {
                    indexOfZero = i;
                    break;
                }
            }

            if (indexOfZero == -1) {
                // inserting zero
                int[] newA = new int[A.length + 1];
                int positiveIndex = A.length;
                for (int i = 0; i < A.length; i++) {
                    if (A[i] > 0) {
                        positiveIndex = i;
                        break;
                    }
                }
                int j = 0;
                for (int i = 0; i < A.length; i++) {
                    if (positiveIndex == j) {
                        newA[j] = 0;
                        j ++;
                    }
                    newA[j] = A[i];
                    j ++;
                }
                A = newA;
                indexOfZero = positiveIndex;
            } else {
                ret ++;
            }

            // 0 <= zeroIndex < A.length
            int toLeft = indexOfZero;
            int toRight = indexOfZero;

            int currentLeft = 0;
            int currentRight = 0;
            while (toLeft > 0 || toRight < A.length - 1) {
                int left = (toLeft <= 0) ? Integer.MAX_VALUE : Math.abs(A[toLeft - 1]);
                int right = (toRight >= A.length - 1) ? Integer.MAX_VALUE : Math.abs(A[toRight + 1]);
                if (left == right) {
                    toLeft --; toRight ++;
                    ret ++;
                    currentLeft = left;
                    currentRight = right;
                } else if (left < right) {
                    toLeft --;
                    if (currentLeft != left) ret ++;
                    currentLeft = left;
                } else if (right < left) {
                    toRight ++;
                    if (currentRight != right) ret ++;
                    currentRight = right;
                }
            }

            return ret;
        }
    }
}
