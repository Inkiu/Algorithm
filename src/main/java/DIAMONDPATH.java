import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DIAMONDPATH {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int caseCount = scanner.nextInt();
        for (int c = 0; c < caseCount; c ++) {
            solution();
        }
        scanner.close();
    }

    private static void solution() {
        int maxCol = scanner.nextInt();

        List<Node> maxList = new ArrayList<>();
        maxList.add(new Node(scanner.nextInt()));

        for (int i = 2; i <= maxCol; i ++) {
            maxList = eachLine(i, maxList);
        }
        for (int i = maxCol - 1; i >= 1; i --) {
            maxList = eachLine(i, maxList);
        }

        String directions = getDirection(maxList.get(0));
        System.out.println(maxList.get(0).max + " " + directions);
    }

    private static List<Node> eachLine(int col, List<Node> prevs) {
        List<Node> nexts = scanList(col);

        int left = prevs.size() < nexts.size() ? -1 : 0;
        int right = prevs.size() < nexts.size() ? 0 : 1;

        for (int i = 0; i < nexts.size(); i ++) {
            int leftIdx = i + left;
            int leftVal = leftIdx >= 0 ? prevs.get(leftIdx).max : Integer.MIN_VALUE;
            int rightIdx = i + right;
            int rightVal = rightIdx >= prevs.size() ? Integer.MIN_VALUE : prevs.get(rightIdx).max;

            Node currentNode = nexts.get(i);
            if (leftVal >= rightVal) {
                currentNode.max += leftVal;
                currentNode.prev = prevs.get(leftIdx);
                currentNode.direction = 1;
            } else {
                currentNode.max += rightVal;
                currentNode.prev = prevs.get(rightIdx);
                currentNode.direction = 0;
            }
        }

        return nexts;
    }

    private static List<Node> scanList(int col) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < col; i ++) {
            list.add(new Node(scanner.nextInt()));
        }
        return list;
    }

    private static String getDirection(Node start) {
        if (start.prev == null) return "";
        return getDirection(start.prev) + start.direction;
    }

    private static class Node {
        int val;
        int max;
        int direction;
        Node prev;

        Node(int val) {
            this.val = val;
            this.max = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", max=" + max +
                    ", direction=" + direction +
                    ", prev=" + prev +
                    '}';
        }
    }
}
