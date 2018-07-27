import java.util.*;

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

        System.out.println(s.solutionEfficient(new int[] { 3, 4, 1, 6, 5, 9, 7 }));
        System.out.println(s.solutionEfficient(new int[] { 2, 1, 6, 4, 3, 7 }));
        System.out.println(s.solutionEfficient(new int[] { 4, 3, 2, 6, 1 }));
        System.out.println(s.solutionEfficient(new int[] { 10, 6, 4, 8, 7, 6, 2 }));
        System.out.println(s.solutionEfficient(new int[] { 1, 2, 3, 4 }));

        long st = 0;
        for (int i = 0; i < 100; i++) {
            int[] test = new int[new Random().nextInt(1000000) + 1000000];
            int start = new Random().nextInt(1000);
            for (int j = 0; j < test.length; j++) {
                test[j] = start = start + new Random().nextInt(10);
            }

            Utils.shuffleArray(test);

            test = Utils.shuffleArray(test);
            st = System.currentTimeMillis();
            int sa = s.solution(test);
            System.out.print( System.currentTimeMillis() - st + "\t\t");

            st = System.currentTimeMillis();
            int ba = s.solutionEfficient(test);
            System.out.print(System.currentTimeMillis() - st + "\t\t");
            System.out.println(sa + " " + ba);
        }
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

        static int solutionEfficient(int[] A) {
            PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.data));

            for (int i = 0; i < A.length; i++) q.add(new Node(A[i], i));

            int count = 0;
            int start = 0;

            while (true) {
                while(!q.isEmpty() && start > q.peek().index) q.poll();
                if (q.isEmpty()) break;
                Node n = q.poll();
                start = n.index;
                count ++;
            }

            return count;
        }

        static class Node {
            int data, index;

            public Node(int data, int index) {
                this.data = data;
                this.index = index;
            }
        }



    }

}
