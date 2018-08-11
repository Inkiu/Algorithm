import java.io.*;
import java.util.*;

public class K_Huffman {

    public static Random random = new Random();

    public static void main(String[] args) throws Exception {
//        InputStream source = new ByteArrayInputStream(TestCases.HUFFMAN.getBytes());

        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        int cc = Integer.parseInt(bi.readLine().split(" ")[0]);
        for (int i = 0; i < cc; i ++) {
            String[] s = bi.readLine().split(" ");
            int texts = Integer.parseInt(s[0]);
            int ary = Integer.parseInt(s[1]);
            int[] textCounts = new int[texts];
            String[] s2 = bi.readLine().split(" ");
            for (int j = 0; j < s2.length; j++) {
                textCounts[j] = Integer.parseInt(s2[j]);
            }
            System.out.println(solution(texts, ary, textCounts));
        }

    }

    static long solution(int text, int arity, int[] textCounts) {
        PriorityQueue<Node> heap = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.count)
        );

        for (int i = 0; i < text; i ++) {
            Node n = new Node();
            n.count = textCounts[i];
            heap.add(n);
        }

        // first
        int firstGroup = (heap.size() - arity) % (arity - 1) + 1;
        Node bottom = new Node();
        bottom.nodes = new Node[arity];
        if (firstGroup != 1) {
            for (int i = 0; i < firstGroup; i++) {
                Node n = heap.poll();
                bottom.count += n.count;
                bottom.nodes[i] = n;
            }
            heap.add(bottom);
        }

        // make full k-ary tree
        while (heap.size() > 1) {
            int monCount = 0;
            Node[] children = new Node[arity];
            for (int i = 0; i < arity; i++) {
                if (!heap.isEmpty()) {
                    Node n = heap.poll();
                    monCount += n.count;
                    children[i] = n;
                }
            }
            Node mom = new Node();
            mom.count = monCount;
            mom.nodes = children;
            heap.add(mom);
        }


        Node root = heap.poll();
        if (text > arity)
            for (int i = 0; i < root.nodes.length; i++) {
                if (root.nodes[i] == null) throw new RuntimeException();
            }

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.depth = 0;

        // using stack
        long result = 0;
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            if (n == null) continue;
            if (n.nodes == null) {
                result += n.count * n.depth;
            } else {
                for (Node child: n.nodes) {
                    if (child != null) child.depth = n.depth + 1;
                    stack.push(child);
                }
            }
        }

        return result;
    }

    static class Node {
        int depth;
        int count;
        Node[] nodes;
    }
}
