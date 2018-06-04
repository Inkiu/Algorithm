import java.util.*;

public class JOGGER {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while(true) {
            int houseCount = scanner.nextInt();
            if (houseCount == 0) break;
            int secPerMeter = scanner.nextInt();
            int restPerNode = scanner.nextInt();
            int[][] houseWeights = new int[houseCount][houseCount];

            for (int i = 0; i < houseCount; i ++) { // scanning matrix
                for (int j = 0; j < houseCount; j ++) {
                    houseWeights[i][j] = scanner.nextInt();
                }
            }

            System.out.println(solution(houseWeights, secPerMeter, restPerNode));
        }
    }

    static int solution(int[][] distances, int secPerMeter, int restPerNode) {
        int longest = 0;
        for (int from = 0; from < distances.length; from++) {
            for (int to = 0; to < distances.length; to++) {

                Set<Integer> ap_distances = new HashSet<>();
                for (int leaf = 0; leaf < distances.length; leaf++) {
                    if (from == leaf || to == leaf) continue;
                    //  AP = (        AL            -         BL          +         AB         ) / 2;
                    int ap = (distances[from][leaf] - distances[to][leaf] + distances[from][to]) / 2;
                    ap_distances.add(ap);
                }

                longest = Math.max(
                        longest,
                        distances[from][to] * secPerMeter + restPerNode * ap_distances.size()
                );

            }
        }
        return longest;
    }
}
