import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class RunningMedian {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int cs = 0; cs < cases; cs ++) {
            solution(sc);
        }
    }

    private static int getNext(List<Integer> arr, int a, int b) {
        if (arr.size() == 0) {
            arr.add(1983);
        } else {
            long la = (long)a;
            Long before = (arr.get(arr.size() - 1) * la + b) % 20090711;
            arr.add(before.intValue());
        }
        return arr.get(arr.size() - 1);
    }

    private static void solution(Scanner scanner) {
        int arrayCount = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> right = new PriorityQueue<>((o1, o2) -> o1 - o2);

        int mid = getNext(arr, a, b);
        int answer = mid;

        for (int i = 0; i < arrayCount - 1; i ++) {
            int current = getNext(arr, a, b);
            if (left.size() == right.size()) {
                if (current < mid) {
                    right.add(mid);
                    if (left.size() == 0) {
                        mid = current;
                    } else {
                        int l = left.poll();
                        if (l > current) {
                            left.add(current);
                            mid = l;
                        } else {
                            left.add(l);
                            mid = current;
                        }
                    }
                } else {
                    right.add(current);
                }
            } else {
                if (current < mid) {
                    left.add(current);
                } else {
                    left.add(mid);
                    int r = right.poll();
                    if (r < current) {
                        right.add(current);
                        mid = r;
                    } else {
                        right.add(r);
                        mid = current;
                    }
                }
            }
            answer = (answer + mid) % 20090711;
        }
        System.out.println(answer);
    }
}
