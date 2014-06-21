
public class PercolationStats {

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
		if (N <= 0 && T <= 0) {
			System.out.println("N and T must be greater than 0");
			System.exit(1);
		}
		System.out.println("N: " + N + "\nT: " + T);
		
		
		
	}

	/**
	 * Sample mean of percolation threshold
	 * 
	 * @return mean
	 */
	public double mean() {
		return 0;
	}
	
	/**
	 * Sample standard deviation of percolation threshold
	 * 
	 * @return standard deviation
	 */
	public double stddev() {
		return 0;
	}
	
	/**
	 * Lower bound of the 95% confidence interval
	 * 
	 * @return confidence level
	 */
	public double confidenceLo() {
		return 0;
	}
	
	/**
	 * Upper bound of the 95% confidence interval
	 * 
	 * @return confidence level
	 */
	public double confidenceHi() {
		return 0;
	}
}