import java.util.*;

public class Main {

    static class Apple {
        int x, y, index;

        Apple(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        Apple[] apples = new Apple[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            apples[i] = new Apple(x, y, i);
        }

        // Sort by row, then by column
        Arrays.sort(apples, (a, b) -> {
            if (a.x != b.x)
                return Integer.compare(a.x, b.x);

            return Integer.compare(a.y, b.y);
        });

        int[] ans = new int[n];
        int eaten = 0;
        boolean reverse = false;

        int i = 0;

        while (i < n) {

            int j = i;

            // Find all apples in the same row
            while (j < n && apples[j].x == apples[i].x) {
                j++;
            }

            if (!reverse) {
                // Left to right
                for (int k = i; k < j; k++) {
                    ans[apples[k].index] = eaten;
                    eaten++;
                }
            } else {
                // Right to left
                for (int k = j - 1; k >= i; k--) {
                    ans[apples[k].index] = eaten;
                    eaten++;
                }
            }

            // Change direction for next occupied row
            reverse = !reverse;

            i = j;
        }

        // Print answers in original input order
        for (int k = 0; k < n; k++) {
            System.out.println(ans[k]);
        }

        sc.close();
    }
}