import java.util.*;

public class AlgorithmTest2 {
    static int arrMaxSize = 20;
    static int arrBound = 100;

    public static void main(String[] args) {
        Solution s = new Solution();

        int count = 0;

        while (true) {
            Random r = new Random();
            int arrSize = r.nextInt(1000);
            int[] A = KotlinUtil.INSTANCE.makeRandomArray(arrSize, 10000, false, false);
            int[] B = KotlinUtil.INSTANCE.makeRandomArray(arrSize, 10000, false, false);
            int[] C = KotlinUtil.INSTANCE.makeRandomArray(arrSize, 10000, false, false);

            int sol = s.solution(A, B, C);
            int bru = s.bruteForce(A, B, C);

            if (sol != bru) {
                System.out.println(Arrays.toString(A));
                System.out.println(Arrays.toString(B));
                System.out.println(Arrays.toString(C));
                break;
            } else if (count % 100 == 0) {
                System.out.println(A.length + " s: " + sol + " b: " + bru);
            }

            count++;
        }
        System.out.println(s.bruteForce(new int[]{29, 50}, new int[]{61, 37}, new int[]{37, 70}));
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

            int bIndex = B.length - 1;
            int count = 0;
            for (int i = C.length - 1; i >= 0; i--) {
                if (C[i] > B[bIndex]) count++;
                else {
                    bIndex--;
                    i++;
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
                while (bNodeStart < A.length && A[i] >= bNodes[bNodeStart].k) {
                    first -= bNodes[bNodeStart].count;
                    bNodeStart++;
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


        static int bruteForce(int A[], int[] B, int[] C) {
            int count = 0;

            for (int i = 0; i < A.length; i++) {
                int salad = A[i];
                for (int j = 0; j < B.length; j++) {
                    int pizza = B[j];
                    for (int k = 0; k < C.length; k++) {
                        int cake = C[k];

                        if (salad < pizza && pizza < cake) count++;


                    }
                }
            }

            return count;
        }


    }
}
