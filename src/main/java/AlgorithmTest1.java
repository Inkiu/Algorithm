import java.util.*;

public class AlgorithmTest1 {
    static int arrMaxSize = 20;
    static int arrBound = 100;

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[] { 1, 1, 4, 9, 0, 4, 8, 9, 0, 1 } )));
        System.out.println(Arrays.toString(s.solution(new int[] { 0 })));
        System.out.println(Arrays.toString(s.solution(new int[] { 0, 0, 0, 0, 0, 4, 4, 6, 1, 7, 2, 5, 1 })));
    }

    static class Solution {
        static int[] solution(int[] A) {
            if (A.length == 1) return new int[0];
            List<List<Integer>> map = new ArrayList<>();
            for (int i = 0; i < A.length; i++) map.add(new ArrayList<>());

            int capital = -1;
            for (int i = 0; i < map.size(); i++) {
                map.get(A[i]).add(i);
                map.get(i).add(A[i]);
                if (i == A[i]) capital = i;
            }

            for (int i = 0; i < map.size(); i++) {
                System.out.println(i + " to " + map.get(i));
            }

            int[] ret = new int[map.size() -1];

            Node cap = new Node(-1, capital, 0, map.get(capital));
            Queue<Node> q = new ArrayDeque<>();
            q.add(cap);
            ret[0] = cap.child.size();

            int index = 1;
            while(!q.isEmpty()) {
                System.out.println(q);
                Node n = q.poll();
                int count = 0;
                for (int i = 0; i < n.child.size(); i++) {
                    if (n.data == n.child.get(i)) continue;
                    Node node = new Node(n.data, n.child.get(i), n.motherDepth + 1, map.get(n.child.get(i)));
                    q.add(node);
                    count += node.child.size();
                }
                if (index < ret.length) ret[index] = count;
                if (count != 0) index ++;
            }
            return ret;
        }

        static class Node {
            List<Integer> child = new ArrayList<>();
            int data;
            int motherDepth;

            public Node(int motherData, int data, int motherDepth, List<Integer> ch) {
                for (int a : ch) {
                    if (a != data && a != motherData) {
                        child.add(a);
                    }
                }
                this.motherDepth = motherDepth;
                this.data = data;
            }

            @Override
            public String toString() {
                return "Node{" +
                        "data=" + data +
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
