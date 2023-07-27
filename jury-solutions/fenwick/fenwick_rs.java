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
/* Author                Roman Satukov                          */
/*--------------------------------------------------------------*/
 
import java.io.*;
import java.util.StringTokenizer;

/**
 * @author Roman V Satyukov
 */
public class fenwick_rs implements Runnable {
	private final String problemID = getClass().getName().split("_")[0].toLowerCase();
	private BufferedReader in;
	private PrintWriter out;
	
	private void check(boolean expr, String message) {
		if (!expr) {
			throw new AssertionError(message);
		}
	}
	
	private void solve() throws IOException {
		int n = Integer.parseInt(in.readLine());
		check((1 <= n) && (n <= 200000), "n is not in [1..200,000]");
		long[] a = new long[n];
		long[] result = new long[n];
		StringTokenizer st = new StringTokenizer(in.readLine());
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			check(Math.abs(a[i]) <= 1000000000, "some of |a[i]| is greater than 1000,000,000.");
			result[i] = a[i];
			p[i] = i;
		}
		while (true) {
			for (int i = 0; i + 1 < n; i += 4) {
				result[p[i]] -= a[i];
				a[i] = 0;
			}
			int j = 0;
			for (int i = 0; i + 1 < n; i += 4) {
				a[j] = a[i + 1];
				if (i + 2 < n) {
					a[j] += a[i + 2];
				}
				p[j] = p[i + 1];
				j++;
				if (i + 3 < n) {
					a[j] = a[i + 3];
					p[j] = p[i + 3];
					j++;
				}
			}
			n = j;
			if (n <= 1) {
				break;
			}
		}
		for (int i = 0; i < result.length; i++) {
			if (i > 0) {
				out.print(" ");
			}
			out.print(result[i]);
		}

		out.println();
	}
	
	public static void main(String[] args) {
		new Thread(new fenwick_rs()).start();
	}

	public void run() {
		try {
			in = new BufferedReader(new FileReader(new File(problemID + ".in")));
			out = new PrintWriter(new File(problemID + ".out"));
			solve();
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
