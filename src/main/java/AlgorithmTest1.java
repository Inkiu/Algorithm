import javafx.util.Pair;

import java.util.*;

public class AlgorithmTest1 {

    static int CHILDREN_MAX_COUNT = 1000;

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] exampleTest = new int[] { 1, 1, 4, 9, 0, 4, 8, 9, 0, 1 };

//        System.out.println(Arrays.toString(s.solution(exampleTest )));

        int[] t = new int[] {0, 0, 0, 0, 0, 4, 4, 6, 1, 7, 2, 5, 1, 10};
        System.out.println(Arrays.toString(s.solution(t)));
        System.out.println(Arrays.toString(s.solutionPerfect(t)));
        System.out.println(Arrays.toString(s.bruteForce(t)));

//        float min = Float.MAX_VALUE, max = Float.MIN_VALUE;
//        int minSize = 0, maxSize = 0;
//        for (int i = 1; i <= 100; i++) {
//            CHILDREN_MAX_COUNT = i;
//            float probability = evaluate();
//            if (probability <= min) {
//                minSize = i;
//                min = probability;
//            }
//            if (probability != 1.0f && probability >= max) {
//                maxSize = i;
//                max = probability;
//            }
//        }
//
//        System.out.println("min : " + min + " at " + minSize);
//        System.out.println("max : " + max + " at " + maxSize);

    }

    static float evaluate() {
        Solution s = new Solution();

        int testCount = 1000;
        int correct = 0;
        int failure = 0;
        for (int i = 0; i < testCount; i++) {
            int[] t = makeTreeArray();
            if (t.length < 2) {
                i --;
                continue;
            }
            int[] perfect = s.solutionPerfect(t);
            int[] solution = s.solution(t);

            boolean fail = false;
            for (int j = 0; j < perfect.length; j++) {
                if (perfect[j] != solution[j]) {
                    fail = true;
                    break;
                }
            }

            if (fail) failure ++;
            else correct ++;
//            if (i % Math.min(100, testCount / 10) == 0) System.out.println();
//            System.out.print(t.length + " ");
        }
//        System.out.println();
        System.out.println("children max : " + CHILDREN_MAX_COUNT + "\tsuccess probability : " + (float)correct / testCount);
        return (float)correct / testCount;
    }

    static class Solution {

        static int[] bruteForce(int[] A) {
            Map<Integer, List<Integer>> map = new HashMap<>();
            int capital = -1;
            for (int i = 0; i < A.length; i++) {
                if (i == A[i]) {
                    capital = i;
                    continue;
                }
                List<Integer> list = map.getOrDefault(A[i], new ArrayList<>());
                list.add(i);
                map.put(A[i], list);
            }

            int[] ret = new int[A.length - 1];
            Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
            Pair<Integer, Integer> cap = new Pair<>(capital, 0);
            q.add(cap);

            while(!q.isEmpty()) {
                Pair<Integer, Integer> n = q.poll();
                if (n.getValue() != 0) ret[n.getValue() - 1] += 1;

                List<Integer> children = map.getOrDefault(n.getKey(), new ArrayList<>());
                for (Integer child : children) {
                    Pair<Integer, Integer> p = new Pair<>(child, n.getValue() + 1);
                    q.add(p);
                }
            }

            return ret;
        }

        static int[] solutionPerfect(int[] A) {
            if (A.length == 1) return new int[0];
            List<List<Integer>> map = new ArrayList<>();
            for (int i = 0; i < A.length; i++) map.add(new ArrayList<>());

            int capital = -1;
            for (int i = 0; i < map.size(); i++) {
                map.get(A[i]).add(i);
                map.get(i).add(A[i]);
                if (i == A[i]) capital = i;
            }

            int[] ret = new int[map.size() - 1];
            Node cap = new Node(-1, capital, 0, map.get(capital));
            Queue<Node> q = new ArrayDeque<>();
            q.add(cap);

            while(!q.isEmpty()) {
                Node n = q.poll();
                if (n.motherDepth != 0) ret[n.motherDepth - 1] += 1;
                for (int i = 0; i < n.child.size(); i++) {
                    if (n.data == n.child.get(i)) continue;
                    Node c = new Node(n.data, n.child.get(i),
                            n.motherDepth + 1,
                            map.get(n.child.get(i)));
                    q.add(c);
                }
            }

            return ret;
        }

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

            int[] ret = new int[map.size() -1];

            Node cap = new Node(-1, capital, 0, map.get(capital));
            Queue<Node> q = new ArrayDeque<>();
            q.add(cap);

            ret[0] = cap.child.size();

            int index = 1;
            while(!q.isEmpty()) {
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

        }
    }

    static int[] makeTreeArray() {
        return makeArrayFromTree(makeRandomTree());
    }

    static int[] makeArrayFromTree(N root) {
        int nodeCount = 0;
        Queue<N> q = new ArrayDeque<>();
        Queue<N> b = new ArrayDeque<>();
        q.add(root);
        b.add(root);

        while(!q.isEmpty()) {
            N n = q.poll();
            q.addAll(n.children);
            b.addAll(n.children);
            nodeCount ++;
        }

        int[] arr = new int[nodeCount];
        arr[root.data] = root.data;
        while (!b.isEmpty()) {
            N n = b.poll();
            for (N c: n.children) {
                arr[c.data] = n.data;
                b.add(c);
            }
        }
        return arr;
    }

    static class N {
        int data;
        int depth;
        List<N> children = new ArrayList<>();
    }

    static N makeRandomTree() {
        Random r = new Random();
        N root = new N();
        Queue<N> q = new ArrayDeque<>();
        List<N> b = new LinkedList<>();
        q.add(root);
        b.add(root);
        float dis = 1.0f;
        int nodeCount = 0;

        while (!q.isEmpty()) {
            N node = q.poll();
            int childSize = r.nextInt((int)(CHILDREN_MAX_COUNT * dis) + 1);
            childSize = Math.max(1, childSize);
//            childSize += 1;
            if (nodeCount > 1000) break;
            for (int i = 0; i < childSize; i++) {
                N ch = new N();
                ch.depth = node.depth + 1;
                node.children.add(ch);
                q.add(ch);
                b.add(ch);
                nodeCount ++;
            }
//            dis *= 0.9;
        }

        int randomData = 0;
        while (!b.isEmpty()) {
            int index = r.nextInt(b.size());
            N node = b.remove(index);
            node.data = randomData;
            randomData ++;
        }

        return root;
    }
}
