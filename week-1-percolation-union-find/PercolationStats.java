
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private final double[] fractionOfOpenSites;
  private final int trialsArg;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials)
  {
    if (n <= 0 || trials <= 0) throw new IllegalArgumentException("You must enter a valid parameter"); 
    // trialResults = new int[trials]; 
    trialsArg = trials; 
    fractionOfOpenSites = new double[trials];
    for (int i = 0; i < trials; i++)
    {
      Percolation grid = new Percolation(n); 
      while (!grid.percolates())
      {
        int radmRow = StdRandom.uniform(n) + 1;
        int radmCol = StdRandom.uniform(n) + 1; 
        // System.out.println("randmRow is" + " " + radmRow + ";" + "randmCol is" + " " + radmCol);
        grid.open(radmRow, radmCol);
      }
      // trialResults[i] = grid.numberOfOpenSites();
      double openSites = (double) grid.numberOfOpenSites();
      // Double realNumberN = Double(n);
      // Double gridSize = realNumberN * realNumberN; 
      double gridSize = (double) n * n;
      fractionOfOpenSites[i] = openSites / gridSize; 
    }
  }

  // sample mean of percolation threshold
  public double mean()
  {
    /*
    n*n = total cell num; [here, n = 5]
    [9, 14,16] = with n cells open, site percolated; 
    Let xt be the fraction of open sites => e.g.   9/(5 * 5);
    */
    return StdStats.mean(fractionOfOpenSites); 
  }

  // sample standard deviation of percolation threshold
  public double stddev()
  {
    return StdStats.stddev(fractionOfOpenSites);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo()
  {
    return mean() - ((1.96 * stddev()) / Math.sqrt(trialsArg));
  }
  // high endpoint of 95% confidence interval
  public double confidenceHi()
  {
    return mean() + ((1.96 * stddev()) / Math.sqrt(trialsArg));
  }
  // test client (see below)
  public static void main(String[] args)
  {
    int n = Integer.parseInt(args[0]); 
    int trialsArg = Integer.parseInt(args[1]);
    PercolationStats test = new PercolationStats(n, trialsArg);
    // System.out.println("go insane" + " " + Arrays.toString(test.trialResults));
    System.out.println("mean() = " + test.mean());
    System.out.println("stddev() " + test.stddev());
    // 95% confidence interval = [0.666217665216461, 0.6676773347835391]
    System.out.println("95% confidence interval = [" + test.confidenceLo() + "," + test.confidenceHi() + "]");
  }
}