import java.util.*;

public class DiscIntersections {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 5, 2, 1, 4, 0}));
        System.out.println(s.solution(new int[]{1, 2147483647, 0}));
        System.out.println(s.solution(new int[]{100, 99, 98, 97, 96, 95}));
    }


    static class Solution {
        public int solution(int[] A) {
            long[] starts = new long[A.length];
            long[] ends = new long[A.length];
            for (int i = 0; i < A.length; i++) {
                starts[i] = (long) i - A[i];
                ends[i] = (long) i + A[i];
            }
            Arrays.sort(starts);
            Arrays.sort(ends);

            int result = 0;
            int left_idx = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = left_idx; j < A.length; j++) {
                    if (starts[j] > ends[i]) break;
                    left_idx = j;
                }
                result = result + left_idx - i;
                if (result > 10000000) return -1;
            }

            return result;
        }
    }
}
