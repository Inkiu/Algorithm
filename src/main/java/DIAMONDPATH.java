import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by inkiu on 2018. 5. 19..
 */
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

        List<PathInt> maxList = new ArrayList<>();
        maxList.add(new PathInt(scanner.nextInt()));

        for (int i = 2; i <= maxCol; i ++) {
            maxList = eachLine(i, maxList);
        }
        for (int i = maxCol - 1; i >= 1; i --) {
            maxList = eachLine(i, maxList);
        }

        System.out.println(maxList.get(0));
    }

    private static List<PathInt> eachLine(int col, List<PathInt> prev) {
        List<PathInt> next = scanList(col);

        int left = prev.size() < next.size() ? -1 : 0;
        int right = prev.size() < next.size() ? 0 : 1;

        for (int i = 0; i < next.size(); i ++) {
            int leftIdx = i + left;
            int leftVal = leftIdx >= 0 ? prev.get(leftIdx).val : Integer.MIN_VALUE;
            int rightIdx = i + right;
            int rightVal = rightIdx >= prev.size() ? Integer.MIN_VALUE : prev.get(rightIdx).val;

            List<Integer> newPath = new ArrayList<>();
            if (leftVal >= rightVal) {
                newPath.addAll(prev.get(leftIdx).path);
                newPath.add(1);
            } else {
                newPath.addAll(prev.get(rightIdx).path);
                newPath.add(0);
            }
            next.set(i, new PathInt(
                    Math.max(leftVal, rightVal) + next.get(i).val,
                    newPath
            ));
        }
        return next;
    }

    private static List<PathInt> scanList(int col) {
        List<PathInt> list = new ArrayList<>();
        for (int i = 0; i < col; i ++) {
            list.add(new PathInt(scanner.nextInt()));
        }
        return list;
    }

    private static class PathInt {
        int val;
        List<Integer> path;

        PathInt(int val) {
            this(val, new ArrayList<>());
        }

        PathInt(int val, List<Integer> path) {
            this.val = val;
            this.path = path;
        }

        @Override
        public String toString() {
            return "PathInt{" +
                    "val=" + val +
                    ", path=" + path +
                    '}';
        }
    }
}
