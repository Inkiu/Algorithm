import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AlgorithmTest3 {
    static int arrMaxSize = 20;
    static int arrBound = 100;

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] { 3, 4, 1, 6, 5, 9, 7 }));
        System.out.println(s.solution(new int[] { 2, 1, 6, 4, 3, 7 }));
        System.out.println(s.solution(new int[] { 4, 3, 2, 6, 1 }));
        System.out.println(s.solution(new int[] { 10, 6, 4, 8, 7, 6, 2 }));
        System.out.println(s.solution(new int[] { 1, 2, 3, 4 }));
    }

    static class Solution {
        static int solution(int[] A) {
            int count = 0;
            int start = 0;
            int end = A.length - 1;
            while(start <= end) {
                start = findMinIndex(A, start, end) + 1;
                count ++;
            }
            return count;
        }

        static int findMinIndex(int[] source, int startInclude, int endInclude) {
            int min = Integer.MAX_VALUE, index = 0;
            for (int i = startInclude; i <= endInclude; i++) {
                if (min > source[i]) {
                    min = source[i];
                    index = i;
                }
            }
            return index;
        }

    }

}
