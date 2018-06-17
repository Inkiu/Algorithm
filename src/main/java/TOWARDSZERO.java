import java.util.*;

public class TOWARDSZERO {

    static final String TEST =
            "4\n" +
                    "2\n" +
                    "3 1\n" +
                    "-3 5 7\n" +
                    "6 10 -2 20 " +
                    "-7 -5 -8 " +
                    "10 8\n" +
                    "7";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(TEST);
        int middle = scanner.nextInt();

        Line next = null;
        for (int i = 1; i < middle; i ++) {
            int[] inputs = new int[i];
            for (int j = 0; j < i; j ++) {
                inputs[j] = scanner.nextInt();
            }
            Line newLine = new Line(inputs, next);
            if (next != null) next.prev = newLine;
            next = newLine;
        }

        for (int i = middle; i > 0; i --) {
            int[] inputs = new int[i];
            for (int j = 0; j < i; j ++) {
                inputs[j] = scanner.nextInt();
            }
            Line newLine = new Line(inputs, next);
            next.prev = newLine;
            next = newLine;
        }

        System.out.println(solution(next));
        scanner.close();
    }

    static int solution(Line head) {
        eachLine(head);
        do {
            head = head.next;
            eachLine(head);
        } while (head.next != null);

        List<Integer> ret = new ArrayList<>();
        for (Integer n: head.nodes[0].cases) {
            ret.add(Math.abs(n));
        }
        ret.sort(Integer::compareTo);
        return ret.get(0);
    }

    static void eachLine(Line cur) {
        if (cur.prev == null) {
            for (Node n: cur.nodes) {
                Set<Integer> e = new HashSet<>();
                e.add(0);
                calAll(n.val, e, n.cases);
            }
        } else {
            int left = cur.prev.nodes.length < cur.nodes.length ? -1 : 0;
            int right = cur.prev.nodes.length < cur.nodes.length ? 0 : 1;
            for (int i = 0; i < cur.nodes.length; i ++) {
                if (i + left >= 0) {
                    calAll(cur.nodes[i].val, cur.prev.nodes[i + left].cases, cur.nodes[i].cases);
                }
                if (i + right < cur.prev.nodes.length) {
                    calAll(cur.nodes[i].val, cur.prev.nodes[i + right].cases, cur.nodes[i].cases);
                }
            }
        }
    }

    static void calAll(int num, Set<Integer> from, Set<Integer> to) {
        for (int f : from) {
            to.add(f + num);
            to.add(f - num);
        }
    }

    static class Line {
        Line next;
        Line prev;
        Node[] nodes;

        Line(int[] inputs, Line next) {
            this.next = next;
            this.nodes = new Node[inputs.length];
            for (int i = 0; i < nodes.length; i ++) {
                nodes[i] = new Node(inputs[i]);
            }
        }
    }

    static class Node {
        int val;
        Set<Integer> cases;

        Node(int val) {
            this.val = val;
            this.cases = new HashSet<>();
        }
    }


}
