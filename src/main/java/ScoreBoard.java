import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class ScoreBoard {

    public static void main(String[] args) throws Exception {
        InputStream source = new ByteArrayInputStream(K_TestCasesKt.getSCORE_BOARD().getBytes());
        BufferedReader bi = new BufferedReader(new InputStreamReader(source));
        int cc = Integer.parseInt(bi.readLine().split(" ")[0]);
        for (int i = 0; i < cc; i++) {
            int line = Integer.parseInt(bi.readLine().split(" ")[0]);
            String[] names = new String[line];
            for (int j = 0; j < line; j++) {
                names[j] = bi.readLine();
            }
            System.out.println(solution(names));
        }
    }

    static long solution(String[] names) {
        Arrays.sort(names);

        long min = Long.MAX_VALUE;

        for (int i = 0; i < names.length; i++) {
            List<String> sub = new ArrayList<>();
            for (int j = 0; j < names.length; j++) {
                if (j != i) sub.add(names[j]);
            }
            min = Math.min(minorSolution(sub.toArray(new String[names.length - 1])), min);
        }
        return min;
    }

    static long minorSolution(String[] names) {
        Node root = new Node('-');
        Node curr;

        for (String s: names) {
            curr = root;
            for (char c: s.toCharArray()) {
                Node a;
                if (curr.contains(new Node(c)))
                    a = curr.get(c);
                else {
                    a = new Node(c);
                    curr.add(a);
                }
                curr = a;
            }
        }

        return recursiveVisit(root);
    }

    static int recursiveVisit(Node root) {
        int sum = 0;
        for (Node n: root.children) {
            sum += recursiveVisit(n);
        }
        if (root.isEmpty()) {
            if (root.mother.getSize() > 1) {
                return root.depth;
            } else {
                root.mother.removeSize();
            }
        }
        return sum;
    }

    static class Node {
        char c;
        List<Node> children = new ArrayList<>();
        int childrenSize;
        Node mother;
        int depth;

        Node(char c) {
            this.c = c;
        }

        void add (Node n) {
            if (children == null) children = new ArrayList<>();
            n.mother = this;
            n.depth = depth + 1;
            children.add(n);
            childrenSize = children.size();
        }

        boolean contains(Node n) {
            return children.contains(n);
        }

        Node get(char c) {
            return children.get(children.indexOf(new Node(c)));
        }

        void removeSize() {
            childrenSize -= 1;
            if (childrenSize < 0) throw new RuntimeException();
        }

        int getSize() {
            return childrenSize;
        }

        boolean isEmpty() {
            return childrenSize == 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return c == node.c;
        }

        @Override
        public int hashCode() {

            return Objects.hash(c);
        }
    }
}
