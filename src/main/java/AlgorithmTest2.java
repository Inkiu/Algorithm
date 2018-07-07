import java.util.*;

public class AlgorithmTest2 {
    static int arrMaxSize = 20;
    static int arrBound = 100;

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[] { 29, 50 }, new int[] { 50, 37 }, new int[] { 37, 70 } ));
        System.out.println(s.solution(new int[] { 29, 50 }, new int[] { 61, 37 }, new int[] { 37, 70 } ));
        System.out.println(s.solution(new int[] { 29, 29 }, new int[] { 51, 51 }, new int[] { 70, 70 } ));
        System.out.println(s.solution(new int[] { 1, 2, 3 }, new int[] { 3, 4, 5 }, new int[] { 4, 5, 6 } ));
        System.out.println(s.solution(new int[] { 5 }, new int[] { 5 }, new int[] { 5 } ));
    }

    static class Solution {
        static int solution(int[] A, int[] B, int[] C) {
            Arrays.sort(A);
            Arrays.sort(B);
            Node[] bNodes = new Node[B.length];
            for (int i = 0; i < bNodes.length; i++) {
                bNodes[i] = new Node(B[i], 0);
            }
            Arrays.sort(C);

            int bIndex = B.length -1;
            int count = 0;
            for (int i = C.length - 1; i >= 0; i--) {
                if (C[i] > B[bIndex]) count ++;
                else {
                    bIndex --;
                    i ++;
                }
                if (bIndex < 0) break;
                bNodes[bIndex] = new Node(B[bIndex], count);
            }
            for (int i = 0; i < bIndex; i++) {
                bNodes[i] = new Node(B[i], count);
            }

            int first = 0;
            for (int i = 0; i < bNodes.length; i++) {
                first += bNodes[i].count;
            }

            int ret = 0;
            int bNodeStart = 0;
            for (int i = 0; i < A.length; i++) {
                while(bNodeStart < A.length && A[i] >= bNodes[bNodeStart].k) {
                    first -= bNodes[bNodeStart].count;
                    bNodeStart ++;
                }
                ret += first;
            }
            return ret;
        }

        static class Node {
            int k;
            int count;
            public Node(int k, int count) {
                this.k = k;
                this.count = count;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "k=" + k +
                        ", count=" + count +
                        '}';
            }
        }



    }

    static int[] makeRandom() {
        Random r = new Random();
        int[] a = new int[r.nextInt(arrMaxSize)];
        for (int i = 0; i < a.length; i++) {
            a[i] = r.nextInt(arrBound) - arrBound / 2;
        }
        return a;
    }

    static int[] makeDistinctRandom() {
        Random r = new Random();
        List<Integer> a = new ArrayList<>();
        int size = r.nextInt(arrMaxSize);
        for (int i = 0; i < size; i++) {
            int candidate = r.nextInt(arrBound) - arrBound / 2;
            while (a.contains(candidate)) {
                candidate = r.nextInt(arrBound) - arrBound / 2;
            }
            a.add(candidate);
        }
        int[] ret = new int[size];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = a.get(i);
        }
        return ret;
    }
}
