import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxCompleteTree {

    static AtomicInteger idMaker = new AtomicInteger(1);
    static int RAN = 100000;

    public static void main(String[] args) throws Exception {

        BNode head = null;
        while(true) {
            Thread.sleep(100);
            head = makeRandomTree();
            clear(head);
            int brute = bruteForce(head);
            clear(head);
            int solu = solution(head);
            if (brute != solu) {
                BNode.BTreePrinter.printNode(head);
                break;
            }
            System.out.println(idMaker.get());
            idMaker.set(1);
        }
        BNode.BTreePrinter.printNode(head);

    }

    public static int bruteForce(BNode<Integer> head) {
        Queue<BNode> q = new ArrayDeque<>();
        q.add(head);
        int max = 1;
        while(!q.isEmpty()) {
            BNode node = q.poll();

            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
            max = Math.max(bruteFind(node), max);
        }
        return max;
    }

    public static int bruteFind(BNode<Integer> target) {
        Queue<BNode> queue = new ArrayDeque<>();
        target.data = 1;
        queue.add(target);
        while(!queue.isEmpty()) {
            BNode node = queue.poll();
            if (node.left != null) {
                node.left.data = (Integer)node.data + 1;
                queue.add(node.left);
            }
            if (node.right != null) {
                node.right.data = (Integer)node.data + 1;
                queue.add(node.right);
            }
        }

        queue.add(target);
        HashMap<Integer, Integer> map = new HashMap<>();
        while(!queue.isEmpty()) {
            BNode node = queue.poll();
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
            int count = map.getOrDefault(node.data, 0);
            map.put((Integer)node.data, count + 1);
        }

        for (Map.Entry<Integer, Integer> e: map.entrySet()) {
            int t = (int)(Math.pow(2, e.getKey() - 1));
            if (e.getValue() != t) return e.getKey() - 1;
        }

        return Collections.max(map.keySet());
    }

    public static int solution(BNode<Integer> head) {
        Queue<BNode<Integer>> queue = new ArrayDeque<>();
        Queue<BNode<Integer>> actQueue = new ArrayDeque<>();
        Queue<BNode<Integer>> lastQueue = new ArrayDeque<>();
        queue.add(head);
        actQueue.add(head);
        lastQueue.add(head);
        while(!actQueue.isEmpty()) {
            BNode node = actQueue.poll();
            if (node.left != null) {
                queue.add(node.left);
                actQueue.add(node.left);
                lastQueue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
                actQueue.add(node.right);
                lastQueue.add(node.right);
            }
        }

        int max = 1;
        while(!queue.isEmpty()) {
            BNode node = ((ArrayDeque<BNode<Integer>>) queue).pollLast();

            if (node.left == null) node.data = 1;
            else if (node.right == null) node.data = 1;
            else if (node.left.data == node.right.data) node.data = (Integer)node.left.data + 1;
            else {
                int min = Math.min((Integer)node.left.data, (Integer)node.right.data);
                max = Math.max(max, (Integer)node.left.data);
                max = Math.max(max, (Integer)node.right.data);
                node.left.data = min;
                node.right.data = min;
                node.data = min + 1;
            }
            max = Math.max(max, (Integer)node.data);
        }

        return max;
    }


    public static BNode makeRandomTree() {
        int threshold = RAN;
        Random random = new Random();
        BNode<Integer> head = new BNode<>(idMaker.getAndIncrement());
        Queue<BNode> queue = new ArrayDeque<>();
        queue.add(head);

        while(!queue.isEmpty()) {
            BNode node = queue.poll();
            int left = random.nextInt(RAN);
            int right = random.nextInt(RAN);
            if (left < threshold) {
                BNode<Integer> l = new BNode<>(idMaker.getAndIncrement());
                node.left = l;
                queue.add(l);
            }
            if (right < threshold) {
                BNode<Integer> r = new BNode<>(idMaker.getAndIncrement());
                node.right = r;
                queue.add(r);
            }
            threshold *= 0.999;
        }
        return head;
    }

    public static void clear(BNode<Integer> head) {
        Queue<BNode> q = new ArrayDeque<>();
        q.add(head);
        while(!q.isEmpty()) {
            BNode node = q.poll();
            node.data = -1;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);

        }
    }


}
