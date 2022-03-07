import java.util.Scanner;

public class NajdolgaOpagjachkaSekvenca {

    private static int najdolgaOpagackaSekvenca(int[] a) {
        int pogolem=1, counter=1;
        for(int i=0;i<a.length;i++){
            try {
                int tmp=a[i], tmp1=a[i+1];
                if (tmp > tmp1) {
                    pogolem++;
                    counter = pogolem;
                } else if (tmp < tmp1) {
                    pogolem = 1;
                } else {
                    continue;
                }
            }catch(Exception e){continue;}
        }
        return counter;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}
