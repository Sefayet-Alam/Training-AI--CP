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
/* Author                Andrew Lopatin                         */
/*--------------------------------------------------------------*/
 
import java.util.*;
import java.io.*;

public class fenwick_al {
  static final int MaxN = (int)1e5;
  static final int MaxV = (int)1e9;
  static int rInt (String s, int a, int b) {
    int x = Integer.parseInt (s);
    if (x < a || x > b) throw new RuntimeException (x + " is not within " + a + ".." + b);
    return x;
  };
  public static void main (String args []) throws Exception {
    BufferedReader in = new BufferedReader (new FileReader ("fenwick.in"));
    PrintWriter out = new PrintWriter ("fenwick.out");
    int n = rInt (in.readLine (), 1, MaxN);
    StringTokenizer t = new StringTokenizer (in.readLine ());
    long [] a = new long [n + 1];
    for (int i = 1; i <= n; i++)
      a[i] = rInt (t.nextToken (), -MaxV, MaxV);
    if (t.hasMoreTokens ()) throw new Exception ("EOL expected");
    if (in.ready ()) throw new Exception ("EOF expected");
    for (int i = 1; i < n; i++)
      for (int j = 0, k = i; (i & (1 << j)) != 0; k &= ~(1 << j), ++j)
        a[i] -= a[k];

    for (int i = 1; i <= n; i++)
      out.print (a[i] + " ");

    out.close ();
  };
};


