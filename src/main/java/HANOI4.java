import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class HANOI4 {
    public static void main(String[] args) throws IOException {

        InputStream source = new ByteArrayInputStream(TESTCASES.HANOI.getBytes());

        BufferedReader bi = new BufferedReader(new InputStreamReader(source));
        int cc = Integer.parseInt(bi.readLine().split(" ")[0]);
        for (int i = 0; i < cc; i++) {
            int disks = Integer.parseInt(bi.readLine().split(" ")[0]);
            int firstState = 0;
            int targetState = Integer.MAX_VALUE >> (31 - disks * 2);

            for (int j = 0; j < 4; j++) {
                String[] stringInts = bi.readLine().split(" ");
                for (int k = 1; k < stringInts.length; k++) {
                    firstState = setStatus(firstState, j, Integer.parseInt(stringInts[k]) - 1);
                }
            }
            System.out.println(solution(disks, firstState, targetState));
        }

//        for (int i = 0; i < 100; i++) {
//            int[] problem = makeRandomStatus();
//            System.out.println(solution(problem[0], problem[1], problem[2]));
//        }
    }

    /*
     * front 와 rear 의 depth 는 같이 진행되어야 함
     * 어떤 동일한 status1 에 대해서 rear 의 depth 가 먼저 진행되어서
     * front : 2 depth, rear : 6 depth -> shortest = 8 depth
     *
     * 하지만 depth 가 같이 진행된다면 어떤 status2 에 대해서
     * front : 3, rear : 3 -> shortest = 6 depth 가 있을 수 있다.
     */
    static int solution(int disks, int firstStatus, int targetStatus) {
        if (targetStatus == firstStatus) return 0;

        Queue<Integer> f = new ArrayDeque<>();
        Queue<Integer> r = new ArrayDeque<>();

        int[] visit = new int[targetStatus + 1];

        ((ArrayDeque<Integer>) f).push(firstStatus);
        ((ArrayDeque<Integer>) r).push(targetStatus);
        visit[firstStatus] = 1;
        visit[targetStatus] = -1;

        boolean breakWhile = false;

        while (!breakWhile) {
            int frontStatus = f.peek();
            int rearStatus = r.peek();

            int frontVisit = visit[frontStatus];
            int rearVisit = visit[rearStatus];

            if (Math.abs(frontVisit) + 1 == Math.abs(rearVisit)) {
                f.remove();
                int ret = doSearch(frontStatus, visit, f, disks);
                if (ret != 0) return ret;
            }

            if (Math.abs(rearVisit) == Math.abs(frontVisit)) {
                r.remove();
                int ret = doSearch(rearStatus, visit, r, disks);
                if (ret != 0) return ret;
            }
        }

        return -1;
    }

    static int doSearch(int status, int[] visits, Queue<Integer> q, int disks) {
        int curDepth = visits[status];
        int[] tops = getTops(status, disks);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i != j && tops[i] != 13 && tops[i] < tops[j]) {
                    int candidate = setStatus(status, j, tops[i]);
                    int candiDepth = visits[candidate];
                    if (candiDepth == 0) { // 방문한 적 없음
                        visits[candidate] = curDepth < 0 ? curDepth - 1 : curDepth + 1;
                        q.offer(candidate);
                    } else if (curDepth * candiDepth < 0) { // 서로 다른 방향 일때
                        return Math.abs(candiDepth) + Math.abs(curDepth) - 1;
                    }
                }
            }
        }
        return 0;
    }

    static int[] getTops(int status, int disks) {
        int[] tops = new int[4];
        for (int i = 0; i < 4; i++) {
            int val = 13;
            int copy = status;
            for (int j = 0; j < disks; j++) {
                if ((~(copy ^ i) & 3) == 3) val = j;
                if (val != 13) break;
                copy = copy >> 2;
            }
            tops[i] = val;
        }
        return tops;
    }

    static int setStatus(int status, int pos, int val) {
        int bit = pos << (val) * 2;
        int clearBit = ~(3 << (val) * 2);
        return (status & clearBit) | bit;
    }

    static int[] makeRandomStatus() {
        Random random = new Random();
        int disks = random.nextInt(12) + 1;

        int[] arrays = new int[disks];
        for (int i = 0; i < arrays.length; i++) {
            arrays[i] = i;
        }
        int status = 0;
        for (int i = 0; i < disks; i++) {
            status = setStatus(status, random.nextInt(4), arrays[i]);
        }

        return new int[] {disks, status, Integer.MAX_VALUE >> (31 - disks * 2)};
    }
}
