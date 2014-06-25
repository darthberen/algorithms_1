/**
 * 
 * @author Matt Price
 *
 */
public class Percolation {
	private int N;
	private WeightedQuickUnionUF ufGrid;
	private boolean []openGrid;
	private int upperPoint;
	private int lowerPoint;
	
	/**
	 * Create a N-by-N grid with all sites blocked
	 * 
	 * @param N height/width of the grid
	 */
	public Percolation(int N) {
		this.N = N;
		ufGrid = new WeightedQuickUnionUF(N*N + 2);
		openGrid = new boolean[N*N];
		for (int i = 0; i < openGrid.length; i++) {
			openGrid[i] = false;
		}
		
		// connect the upper point (position N*N) to the top of the grid
		upperPoint = N*N;
		
		for (int i = 0; i < N; i++) {
			ufGrid.union(upperPoint, i);	
		}
		
		// connect the lower point (position N*N+1) to the bottom of the grid
		lowerPoint = N*N + 1;
		for (int i = (N*(N-1)); i < N*N; i++) {
			ufGrid.union(lowerPoint, i);	
		}
	}
	
	/**
	 * Takes a row and a column and calculates the position in a 1d array
	 * those spots refer to.
	 * 
	 * @param row
	 * @param col
	 * @return position
	 */
	private int getPosition(int row, int col) {
		int pos = ((row - 1) * N) + (col - 1);
		if (pos < 0 || pos >= N*N) {
			pos = -1;
		}
		return pos;
	}

	/**
	 * Open site (row i, column j) if it is not already
	 * 
	 * @param i row
	 * @param j column
	 */
	public void open(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new java.lang.IndexOutOfBoundsException("row and/or must be in the range [1, N]");
		}
		int spot = getPosition(i, j);
		openGrid[spot] = true;

		int tmp = getPosition(i-1, j);
		if (tmp != -1 && openGrid[tmp]) ufGrid.union(tmp, spot);
		tmp = getPosition(i+1, j);
		if (tmp != -1 && openGrid[tmp]) ufGrid.union(tmp, spot);
		tmp = getPosition(i, j-1);
		if (tmp != -1 && openGrid[tmp]) ufGrid.union(tmp, spot);
		tmp = getPosition(i, j+1);
		if (tmp != -1 && openGrid[tmp]) ufGrid.union(tmp, spot);
	}
	
	/**
	 * Is site (row i, column j) open?
	 * 
	 * @param i row
	 * @param j column
	 * @return true if open, false otherwise
	 */
	public boolean isOpen(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new java.lang.IndexOutOfBoundsException("row and/or must be in the range [1, N]");
		}
		return openGrid[getPosition(i, j)];
	}
	
	/**
	 * Is site (row i, column j) full?
	 * 
	 * @param i row
	 * @param j column
	 * @return true if full, false otherwise
	 */
	public boolean isFull(int i, int j) {
		if (i < 1 || i > N || j < 1 || j > N) {
			throw new java.lang.IndexOutOfBoundsException("row and/or must be in the range [1, N]");
		}
		return ufGrid.connected(getPosition(i, j), upperPoint);
	}
	
	/**
	 * Does the system percolate?
	 * 
	 * @return true if percolates, false otherwise
	 */
	public boolean percolates() {
		return ufGrid.connected(upperPoint, lowerPoint);
	}
}