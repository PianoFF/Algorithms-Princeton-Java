
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private final WeightedQuickUnionUF id;
  private int[] open; 
  private final int percolationARg;
  private final int sudoTop;
  private final int sudoBottom;
  private int cellsOpenCount; 


  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n)
  {
    if (n <= 0) throw new IllegalArgumentException("You must enter a valid parameter"); 
    sudoTop = n * n;
    sudoBottom = sudoTop + 1; 
    id = new WeightedQuickUnionUF(n*n+2);
    open = new int[n * n]; 
    percolationARg = n; 
    cellsOpenCount = 0; 
    for (int i = 0; i < n*n; i++) 
    // 0 = closed 1 = open
    {
      open[i] = 0; 
    } 
  }

  private int rowColToInd(int row, int col)
  {
    int ind = ((row * percolationARg)-1)-(percolationARg-col);
    return ind; 
  }
  // opens the site (row, col) if it is not open already
  public void open(int row, int col)
  {
    int ind = rowColToInd(row, col); 
    if (open[ind] != 1) 
    {
      open[ind] = 1; 
      cellsOpenCount++; 
      if (ind < percolationARg) id.union(ind, sudoTop);
      if (ind >= percolationARg * (percolationARg-1)) id.union(ind, sudoBottom);
      // left (col - 1) = 0; right (col + 1) < percolationARg; top (row-1, col) ; bottom (row + 1, col);
      if (col != 1 && isOpen(row, col-1)) id.union(ind, rowColToInd(row, col-1));
      if (col != percolationARg && isOpen(row, col+1)) id.union(ind, rowColToInd(row, col+1));
      if (row != 1 && isOpen(row-1, col)) id.union(ind, rowColToInd(row-1, col));
      if (row != percolationARg && isOpen(row+1, col)) id.union(ind, rowColToInd(row+1, col));
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col)
  {
    return open[rowColToInd(row, col)] == 1; 
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col)
  {
    if (!isOpen(row, col)) return false; 
    return id.find(sudoTop) == id.find(rowColToInd(row, col)); 
  }

  // returns the number of open sites
  public int numberOfOpenSites()
  {
    return cellsOpenCount; 
  }
  // does the system percolate?
  public boolean percolates()
  {
    return id.find(sudoTop) == id.find(sudoBottom);
  }
  // test client (optional)
  public static void main(String[] args)
  {
    Percolation grid = new Percolation(5); 
    grid.open(1, 3);
    grid.open(2, 3); 
    grid.open(3, 3); 
    grid.open(4, 3);
    grid.open(5, 3);
    System.out.println("go insane" + " " + grid.percolates());
  }
}
