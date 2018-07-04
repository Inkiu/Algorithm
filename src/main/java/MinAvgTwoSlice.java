import java.util.Arrays;
import java.util.Random;

/**
 * 문제 : https://app.codility.com/programmers/lessons/15-caterpillar_method/abs_distinct/
 */
public class MinAvgTwoSlice {
    public static void main(String[] args) {
        Solution s = new Solution();
        BruteForce b = new BruteForce();
        System.out.println(s.solution(new int[] { 4, 2, 2, 5, 1, 5, 8 }));
        System.out.println(b.solution(new int[] { 4, 2, 2, 5, 1, 5, 8 }));

        while(true) {
            int[] randoms = getRandomArray();
            if (s.solution(randoms) != b.solution(randoms)) {
                System.out.println(Arrays.toString(randoms));
                break;
            }
        }
    }

    static class Solution {
        private int solution(int[] A) {
            int[] pref = new int[A.length];
            pref[0] = A[0];
            for (int i = 1; i < A.length; i++) pref[i] = pref[i - 1] + A[i];

            float globalMin = Integer.MAX_VALUE;
            int globalIndex = -1;
            int globalSize = -1;

            for (int size = 2; size <= 3; size++) {
                float localMin = Integer.MAX_VALUE;
                int localIndex = -1;
                for (int i = 0; i + size <= A.length; i ++) {
                    if (localMin > getAvg(pref, i, i + size - 1)) {
                        localMin = getAvg(pref, i, i + size - 1);
                        localIndex = i;
                    }
                }
                if (globalMin > localMin) {
                    globalMin = localMin;
                    globalIndex = localIndex;
                    globalSize = size;
                }
            }
            return globalIndex;
        }

        private float getAvg(int[] pref, int startInclude, int endInclude) {
            int start = startInclude - 1 < 0 ? 0 : pref[startInclude - 1];
            return (float)(pref[endInclude] - start) / (endInclude - startInclude + 1);
        }
    }

    static class BruteForce {
        private int solution(int[] A) {
            int[] pref = new int[A.length];
            pref[0] = A[0];
            for (int i = 1; i < A.length; i++) pref[i] = pref[i - 1] + A[i];

            float globalMin = Integer.MAX_VALUE;
            int globalIndex = -1;
            int globalSize = -1;

            for (int size = 2; size <= A.length; size++) {
                float localMin = Integer.MAX_VALUE;
                int localIndex = -1;
                for (int i = 0; i + size <= A.length; i ++) {
                    if (localMin > getAvg(pref, i, i + size - 1)) {
                        localMin = getAvg(pref, i, i + size - 1);
                        localIndex = i;
                    }
                }
                if (globalMin > localMin) {
                    globalMin = localMin;
                    globalIndex = localIndex;
                    globalSize = size;
                }
            }
            return globalIndex;
        }

        private float getAvg(int[] pref, int startInclude, int endInclude) {
            int start = startInclude - 1 < 0 ? 0 : pref[startInclude - 1];
            return (float)(pref[endInclude] - start) / (endInclude - startInclude + 1);
        }
    }

    private static int[] getRandomArray() {
        Random r = new Random();
        int[] ran = new int[r.nextInt(90) + 10];
        for (int i = 0; i < ran.length; i++) {
            ran[i] = r.nextInt(20000) - 10000;
        }
        return ran;
    }
}
