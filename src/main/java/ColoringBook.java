import java.util.*;

public class ColoringBook {

    public static void main(String[] args) {
        while(true) {

            int[][] map = new int[new Random().nextInt(100) + 1][new Random().nextInt(100) + 1];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] = new Random().nextInt(100);
                }
            }

            int[][] map2 = copy(map);
            int[][] map3 = copy(map);
            int[] s1 = new ColoringBook().solution(0, 0, map, false);
            int[] s2 = new Solution().solution(map.length, map[0].length, map2);

            if (map.length > 98 && map[0].length > 98)
                System.out.println("Doing...");

            if (s1[0] != s2[0] || s1[1] != s2[1]) {
                System.out.println(Arrays.toString(s1));
                System.out.println(Arrays.toString(s2));
                System.out.println(Arrays.toString(new ColoringBook().solution(0, 0, map3, true)));
                break;
            }
        }

    }

    public static void print(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] copy(int[][] origin) {
        int[][] dest = new int[origin.length][origin[0].length];
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[0].length; j++) {
                dest[i][j] = origin[i][j];
            }
        }
        return dest;
    }

    public int[] solution(int m, int n, int[][] picture, boolean debug) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[0].length; j++) {
                if (!isInside(i, j, picture)) continue;
                int target = picture[i][j];
                if (target == 0) continue;
                if (debug) System.out.println();
                int space = stackDFS(picture, i, j, target);
                if (debug) print(picture);
                numberOfArea += 1;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, space);
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int stackDFS(int[][] map, int x, int y, int targetColor) {
        Stack<Node> stack = new Stack<>();
        int visitCount = 1;
        Node node = new Node(x, y);
        map[node.x][node.y] = 0;
        stack.push(node);

        while (!stack.isEmpty()) {
            Node n = stack.peek();
            Node next = null;

            if (isInside(n.x + 1, n.y, map) && map[n.x + 1][n.y] == targetColor) {
                next = new Node(n.x + 1, n.y);
            } else if (isInside(n.x - 1, n.y, map) && map[n.x - 1][n.y] == targetColor) {
                next = new Node(n.x - 1, n.y);
            } else if (isInside(n.x, n.y + 1, map) && map[n.x][n.y + 1] == targetColor) {
                next = new Node(n.x, n.y + 1);
            } else if (isInside(n.x, n.y - 1, map) && map[n.x][n.y - 1] == targetColor) {
                next = new Node(n.x, n.y - 1);
            }

            if (next != null) { // 인접 노드 있음
                stack.push(next);
                map[next.x][next.y] = 0;
                visitCount += 1;
            } else {
                stack.pop();
            }
        }
        return visitCount;
    }

    private boolean isInside(int x, int y, int[][] map) {
        return x > -1 && y > -1 && x < map.length && y < map[0].length;
    }

    class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Solution {

        static boolean visited[][];
        static int dx [] = {-1,0,1,0};
        static int dy [] = {0,1,0,-1};

        class Pair{
            int x;
            int y;
            Pair(int x, int y){
                this.x = x;
                this.y = y;
            }
        }

        int bfs(int x, int y, int[][] picture, int m, int n){
            Queue<Pair> q = new LinkedList<Pair>();
            q.add(new Pair(x,y));
            visited[x][y] = true;
            int sum = 1;

            while(!q.isEmpty()){
                Pair p = q.remove();

                int nextX, nextY;
                for(int i=0; i<4; i++){
                    nextX = p.x + dx[i];
                    nextY = p.y + dy[i];

                    if(nextX>=0 && nextY>=0 && nextX<m && nextY<n && !visited[nextX][nextY]
                            && picture[p.x][p.y] == picture[nextX][nextY]){
                        visited[nextX][nextY] = true;
                        q.add(new Pair(nextX, nextY));
                        sum++;
                    }
                }
            }

            return sum;

        }

        public int[] solution(int m, int n, int[][] picture) {
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;
            Map<Integer, Integer> map = new HashMap<>();
            visited = new boolean[m][n];

            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(!visited[i][j] && picture[i][j]!=0){
                        int val = picture[i][j];
                        if(map.get(val)==null){
                            map.put(val, 0);
                        }
                        map.put(val, Math.max(map.get(val), bfs(i,j,picture,m,n)));
                        //maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i,j,picture,m,n));
                        numberOfArea++;
                    }
                }
            }

            Iterator<Integer> iter = map.keySet().iterator();
            while (iter.hasNext()) {
                int key = iter.next();
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, map.get(key));
                //System.out.println("key: " + key + " value: " + map.get(key));
            }



            int[] answer = new int[2];
            answer[0] = numberOfArea;
            answer[1] = maxSizeOfOneArea;
            return answer;
        }
    }

}
