import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TrianglePath {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cases = sc.nextInt();
        for (int cs = 0; cs < cases; cs ++) {
            solution(sc);
        }
    }

    private static void solution(Scanner scanner) {
        List<List<Integer>> sourceList = getSourceList(scanner, scanner.nextInt());

        for (int step = 1; step < sourceList.size(); step ++) {
            List<Integer> prev = sourceList.get(step - 1);
            List<Integer> curr = sourceList.get(step);

            for (int i = 0; i < curr.size(); i ++) {
                int up = i == step ? 0 : prev.get(i) + curr.get(i);
                int left = i == 0 ? 0 : prev.get(i - 1) + curr.get(i);
                curr.set(i, Math.max(up, left));
            }
        }

        int max = 0;
        for (int i : sourceList.get(sourceList.size() - 1)) {
            max = Math.max(i, max);
        }
        System.out.println(max);

    }

    private static List<List<Integer>> getSourceList(Scanner scanner, int count) {
        List<List<Integer>> result = new ArrayList<>();
        for (int c = 0; c < count; c ++) {
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < c + 1; i ++) list.add(scanner.nextInt());
            result.add(list);
        }
        return result;
    }
}
