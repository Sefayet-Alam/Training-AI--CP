/*--------------------------------------------------------------*/
/* ACM ICPC 2008-2009, NEERC                                    */
/* Northern Subregional Contest                                 */
/* St Petersburg, November 1, 2008                              */
/*--------------------------------------------------------------*/
/* Problem F. Fenwick Tree                                      */
/*                                                              */
/* Original idea         Roman Satukov                          */
/* Problem statement     Andrew Stankevich                      */
/* Testset               Andrew Stankevich                      */
/*--------------------------------------------------------------*/
/* Solution                                                     */
/*                                                              */
/* Author                Georgiy Korneev                        */
/*--------------------------------------------------------------*/
 
import java.util.*;
import java.io.*;

public class fenwick_gk {
    static Scanner in;
    static PrintWriter out;

    public void run() {
        int n = in.nextInt();
        long[] a = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = in.nextInt();

            long sum = 0;
//            System.out.print(i);
            for (int j = 1; (i & j) == 0; j *= 2) {
                sum += a[i - j];
//                System.out.print(" " + (i - j));
            }
//            System.out.println();
            a[i - 1] -= sum;
        }

        for (int i = 1; i <= n; i++) {
            out.println(a[i]);
        }
    }

    public static void main(String[] args) throws Exception {        
        in = new Scanner(new File("fenwick.in"));
        out = new PrintWriter("fenwick.out");

        new fenwick_gk().run();

        in.close();
        out.close();
    }
}
