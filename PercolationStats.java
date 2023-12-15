import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private int t;
    private double[] thresholds;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0) {
            throw new java.lang.IllegalArgumentException(
                    "Hold up, N & T have to be greater than zero.");
        }

        this.n = n;
        this.t = t;
        thresholds = new double[t];

        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);

            int openCount = 0;

            while (!p.percolates()) {
                openRandomNode(p);
                openCount++;
            }

            thresholds[i] = (double) openCount / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(t));
    }

    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(t));
    }

    private void openRandomNode(Percolation p) {
        boolean openNode = true;
        int row = 0;
        int col = 0;

        while (openNode) {
            row = StdRandom.uniform(1, n + 1);
            col = StdRandom.uniform(1, n + 1);

            openNode = p.isOpen(row, col);
        }

        p.open(row, col);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int t = Integer.parseInt(args[1]);

        PercolationStats s = new PercolationStats(n, t);

        System.out.println("mean: = " + s.mean());
        System.out.println("stddev: = " + s.stddev());
        System.out.println(
                "95% confidence interval: = " + s.confidenceLo() + ", " + s.confidenceHi());
    }
}
