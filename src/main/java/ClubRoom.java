import java.awt.*;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ClubRoom {

    public static void main(String[] args) throws Exception {
        InputStream source = new ByteArrayInputStream(K_TestCasesKt.getCLUB_ROOM().getBytes());

        BufferedReader bi = new BufferedReader(new InputStreamReader(source));

        int cc = Integer.parseInt(bi.readLine().split(" ")[0]);
        for (int i = 0; i < cc; i++) {
            int[] fr = parse(bi.readLine().split(" "));
            Point[] f = new Point[fr[0]];
            for (int j = 0; j < f.length; j++) {
                int[] p = parse(bi.readLine().split(" "));
                f[j] = new Point(p[0], p[1]);
            }
            Point[] r = new Point[fr[1]];
            for (int j = 0; j < r.length; j++) {
                int[] p = parse(bi.readLine().split(" "));
                r[j] = new Point(p[0], p[1]);
            }

            String[] ans = solution(f, r);
            for (int j = 0; j < ans.length - 1; j++) {
                System.out.print(ans[j] + " ");
            }
            System.out.println(ans[ans.length - 1]);
        }
    }

    static String[] solution(Point[] facs, Point[] rooms) {

        String[] ans = new String[rooms.length];

        for (int i = 0; i < rooms.length; i++) {
            if (i == 0) {
                ans[i] = "G";
                continue;
            }

            Rectangle intersected = createFacilityRect(rooms[i], facs[0]);
            for (int j = 1; j < facs.length; j++) {
                intersected = intersected.intersection(createFacilityRect(rooms[i], facs[j]));
                if (intersected.isEmpty()) break;
            }

            if (intersected.isEmpty()) ans[i] = "G";
            else {
                boolean contains = false;
                for (int j = 0; j < i; j++) {
                    contains = insideNotEdge(intersected, rooms[j]);
                    if (contains) break;
                }
                ans[i] = contains ? "B" : "G";
            }

        }

        return ans;
    }

    /* Utils */
    static boolean insideNotEdge(Rectangle rect, Point target) {
        return target.x > rect.x && target.x < (rect.x + rect.width)
                && target.y > rect.y && target.y < (rect.y + rect.height);
    }

    static Rectangle createFacilityRect(Point room, Point facility) {
        int dist = Math.max(Math.abs(room.x - facility.x), Math.abs(room.y - facility.y));
        return new Rectangle(facility.x - dist, facility.y - dist, dist * 2, dist * 2);
    }

    static int[] parse(String[] s) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        return a;
    }
}
