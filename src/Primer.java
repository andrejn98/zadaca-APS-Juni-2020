import java.util.Scanner;

public class Primer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a[] = {-2, 11, -4, 13, -5, -2};
        int b = sc.nextInt();

        System.out.println(max_subsequence_sum1(a, b));
    }

    public static int max_subsequence_sum(int a[], int n) {
        int max_sum = 0;
        int best_i = -1;
        int best_j = -1;
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++) {
                int this_sum = 0;
                for (int k = i; k <= j; k++)
                    this_sum += a[k];
                if (this_sum > max_sum) {
                    /* update max_sum, best_i, best_j */
                    max_sum = this_sum;
                    best_i = i;
                    best_j = j;
                }
            }
        return max_sum;
    }

    public static int max_subsequence_sum1(int a[], int n) {
        int max_sum = 0;
        int best_i = -1;
        int best_j = -1;
        for (int i = 0; i < n; i++) {
            int this_sum = 0;
            for (int j = i; j <= n; j++) {
                this_sum += a[j];
                if (this_sum > max_sum) {
                    /* update max_sum, best_i, best_j */
                    max_sum = this_sum;
                    best_i = i;
                    best_j = j;
                }
            }
        }
        return max_sum;
    }
}