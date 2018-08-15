import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Cleaner {

    public static void main(String[] args) throws Exception {
        InputStream source = new ByteArrayInputStream(TestCases.CLEANER.getBytes());

        BufferedReader bi = new BufferedReader(new InputStreamReader(source));
        int cc = Integer.parseInt(bi.readLine().split(" ")[0]);
        for (int i = 0; i < cc; i++) {
            String[] s = bi.readLine().split(" ");
            int[][] map = new int[Integer.parseInt(s[0])][Integer.parseInt(s[1])];

            for (int j = 0; j < map.length; j++) {
                String[] ss = bi.readLine().split(" ");
                for (int k = 0; k < ss.length; k++) {
                    map[j][k] = Integer.parseInt(ss[k]);
                }
            }

            System.out.println(solution(map));
        }
    }

    public static long solution(int[][] map) {
        return recursiveVisit(map,
                0, 0, 0,
                map.length * map[0].length - 1,
                0);
    }

    public static long recursiveVisit(int[][] map,
                                      int x, int y,
                                      int depth, int target,
                                      long sum) {
        if (x < 0 || y < 0 || map.length <= x || map[0].length <= y) return 0;
        if (map[x][y] != -1 && map[x][y] != depth) return 0;
        if (depth == target && x == map.length - 1 && y == map[0].length - 1) return 1;

        int originCell = map[x][y];
        map[x][y] = depth;

        int answer = 0;
        answer += recursiveVisit(map,x + 1, y, depth + 1, target, sum);
        answer += recursiveVisit(map,x - 1, y, depth + 1, target, sum);
        answer += recursiveVisit(map, x, y + 1, depth + 1, target, sum);
        answer += recursiveVisit(map, x, y - 1, depth + 1, target, sum);

        sum += answer;
        sum %= 1000000007;

        map[x][y] = originCell;

        return sum;
    }
}
