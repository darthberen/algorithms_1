/**
 * 
 * @author matt
 *
 */
public class PercolationStats {
	private double mean, stddev, confidenceHi, confidenceLo;
	
	/**
	 * Executes the test client.  Two parameters should be provided:
	 * N: size of the grid
	 * T: number of independent computational experiments
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// check that appropriate arguments have been provided
		if (args.length != 2) {
			System.out.println("You need to provide two arguments:");
			System.out.println("\tN: size of the grid");
			System.out.println("\tT: number of independent computational experiments");
			System.exit(1);
		}
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats pt = new PercolationStats(N, T);
		System.out.println("mean                    = " + pt.mean());
		System.out.println("stddev                  = " + pt.stddev());
		System.out.println("95% confidence interval = " + pt.confidenceLo() + ", " + pt.confidenceHi());
	}

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new java.lang.IllegalArgumentException("N and T must be greater than 0");
		}
		double points[] = new double[T];

		for (int t = 0; t < T; t++) {
			Percolation percolator = new Percolation(N);
			int attempts = 0;
			do {
				int row = 0;
				int col = 0;
				do {
					row = StdRandom.uniform(1, N+1);
					col = StdRandom.uniform(1, N+1);
				} while (percolator.isOpen(row, col));
				percolator.open(row, col);
				attempts++;
			} while (!percolator.percolates());

			points[t] = (double) attempts / (N*N);
		}

		
		mean = StdStats.mean(points);
		stddev = StdStats.stddev(points);
		confidenceLo = mean - ((1.96 * stddev) / Math.sqrt(T));
		confidenceHi = mean + ((1.96 * stddev) / Math.sqrt(T));
	}
	/**
	 * Sample mean of percolation threshold
	 * 
	 * @return mean
	 */
	public double mean() {
		return mean;
	}
	
	/**
	 * Sample standard deviation of percolation threshold
	 * 
	 * @return standard deviation
	 */
	public double stddev() {
		return stddev;
	}
	
	/**
	 * Lower bound of the 95% confidence interval
	 * 
	 * @return confidence level
	 */
	public double confidenceLo() {
		return confidenceLo;
	}
	
	/**
	 * Upper bound of the 95% confidence interval
	 * 
	 * @return confidence level
	 */
	public double confidenceHi() {
		return confidenceHi;
	}
}