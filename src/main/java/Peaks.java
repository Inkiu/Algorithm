import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 문제 : https://app.codility.com/programmers/lessons/10-prime_and_composite_numbers/peaks/
 */

public class Peaks {

    public static void main(String[] args) {
        Solution s = new Solution();
        BruteForce b = new BruteForce();
        System.out.println(s.solution(new int[] { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 }));
        System.out.println(b.solution(new int[] { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 }));
    }

    static class Solution {
        public int solution(int[] A) {
            List<Integer> peakIndexes = new ArrayList<>();
            boolean[] peaks = new boolean[A.length];
            for (int i = 1; i < A.length - 1 ; i ++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
                    peakIndexes.add(i);
                    peaks[i] = true;
                }
            }

            Set<Integer> composites = new HashSet<>();
            for (int i = 1; i < A.length / 2; i ++) {
                if (A.length % i == 0) {
                    composites.add(i);
                    composites.add(A.length / i);
                }
            }

            List<Integer> comps = new ArrayList<>(composites);

            int ret = 0;
            for (Integer comp : comps) {
                boolean ok = true;
                for (int j = 0; j < A.length / comp; j++) {
                    boolean passed = false;
                    for (int k = j * comp; k < (j + 1) * comp; k++) {
                        passed |= peaks[k];
                    }
                    ok &= passed;
                }
                if (ok) ret ++;
            }

            return ret;

//            int[] peaks = new int[peakIndexes.size()];
//            for (int i = 0; i < peakIndexes.size(); i ++) peaks[i] = peakIndexes.get(i);

//            int maxDistance = 0;
//            for (int i = 0; i < peaks.length; i ++) {
//                if (i == 0) maxDistance = Math.max(maxDistance, peaks[i]);
//                else if (i == peaks.length - 1) maxDistance = Math.max(maxDistance, A.length - peaks[i]);
//                else maxDistance = Math.max(maxDistance, peaks[i + 1] - peaks[i]);
//            }
//
//            int ret = 0;
//            for (int i = 1; i < A.length; i ++) {
//                if (A.length % i != 0) continue;
//                int comp = A.length / i;
//                if (comp < maxDistance) break;
//                ret ++;
//            }
//            return ret;
        }
    }

    static class BruteForce {
        public int solution(int[] A) {
            List<Integer> source = new ArrayList<>(A.length);
            for (int a: A) source.add(a);

            List<Integer> peakIndexes = new ArrayList<>();
            for (int i = 1; i < source.size() - 1 ; i ++) {
                if (A[i] > A[i - 1] && A[i] > A[i + 1]) peakIndexes.add(i);
            }

            int ret = 0;
            for (int i = 1; i <= source.size(); i ++) {
                if (source.size() % i != 0) continue;

                boolean ok = true;
                for (int j = 0; j < source.size() / i; j ++) {
                    ok = ok && containsAny(j * i, (j + 1) * i, peakIndexes);
                }
                if (ok) ret ++;
            }

            return ret;
        }

        boolean containsAny(int startInclude, int endExclude, List<Integer> target) {
            boolean contains = false;
            for (int i = startInclude; i < endExclude; i ++) contains = contains || target.contains(i);
            return contains;
        }
    }
}
