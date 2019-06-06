/**
 * Created by Victor on 5/17/2017.
 */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] count;
    private int t;
    private double mean;
    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0){
            throw new IllegalArgumentException("n and trials cannot be <= 0");
        }
        this.t = trials;
        count = new double[trials];
        //StdRandom random = new StdRandom():
        //int count = 0;

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while(!perc.percolates()){
                perc.open(StdRandom.uniform(1,n+1),StdRandom.uniform(1,n+1));
            }
            count[i] = (double)perc.numberOfOpenSites()/(n*n);
        }
        this.mean = mean();
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(count);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(count);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean - (1.96*stddev())/Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean + (1.96*stddev())/Math.sqrt(t);
    }

    // test client (described below)
    public static void main(String[] args){
//        PercolationStats stats = new PercolationStats(200,100);
//        System.out.println(stats.mean());
//        System.out.println(stats.stddev());
//        System.out.println(stats.confidenceLo());
//        System.out.println(stats.confidenceHi());
    }
}