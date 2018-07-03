import java.util.*;

/**
 * 문제 : https://app.codility.com/programmers/lessons/6-sorting/number_of_disc_intersections/
 * PDF : https://github.com/Inkiu/Algorithm/blob/master/src/main/resources/DiskIntersection.pdf
 */

public class DiscIntersections {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 5, 2, 1, 4, 0}));
        System.out.println(s.solution(new int[]{1, 2147483647, 0}));
        System.out.println(s.solution(new int[]{100, 99, 98, 97, 96, 95}));
    }


    static class Solution {
        public int solution(int[] A) {
            long[] lefts = new long[A.length];
            long[] rights = new long[A.length];
            for (int i = 0; i < A.length; i++) {
                lefts[i] = (long) i - A[i];
                rights[i] = (long) i + A[i];
            }
            Arrays.sort(lefts);     // 원의 좌측 좌표들
            Arrays.sort(rights);    // 원의 우측 좌표들

            int result = 0;
            int smallThanRight = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = smallThanRight; j < A.length; j++) {
                    if (lefts[j] > rights[i]) break; // right 보다 smallThanRight 가 커질 때
                    smallThanRight = j;
                }
                // smallThanRight = 지금의 right 보다 작은 left 의 갯수
                // i = 이미 앞에서 계산된 원들을 빼는 역할
                result = result + smallThanRight - i;
                if (result > 10000000) return -1;
            }

            return result;
        }
    }
}
