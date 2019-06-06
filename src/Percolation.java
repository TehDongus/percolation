import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * Created by Victor Avelar on 6/4/2019
 * This new version deals with backwash by creating another instance of WeightedQuickUnionUF, whilest the first version
 * failed the backwash test.
 */
public class Percolation{
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backwashUF;
    private boolean[] grid;
    private int openSites = 0;
    private int n;
    private final int TOP, BOTTOM; //imaginary top and bottom index

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0){
            throw new IllegalArgumentException("n cannot be smaller than 1");
        }
        this.n = n;
        int size = (n*n)+2;
        TOP = (n*n);
        BOTTOM = (n*n)+1;

        this.grid = new boolean[size]; //Use top left corner as 0,0
        this.uf = new WeightedQuickUnionUF(size); //top left corner as 0,0
        this.backwashUF = new WeightedQuickUnionUF(size);

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateBounds(row, col);

        //if site is closed (i.e. false) set to open (true) and increment openSites
        if(!grid[toIndex(row,col)]){
            grid[toIndex(row,col)] = true;
            openSites++;
            //connects to virtual top
            if(row == 1){
                union(toIndex(row, col), TOP);
            }
            //connects to top if open
            if(row != 1 && grid[toIndex(row-1,col)]){
                union(toIndex(row, col), toIndex(row-1, col));
            }
            //connects to right if open
            if(col != n && grid[toIndex(row, col+1)]){
                union(toIndex(row, col), toIndex(row, col+1));
            }
            //connects to left if open
            if(col != 1 && grid[toIndex(row,col-1)]){
                union(toIndex(row, col), toIndex(row, col-1));
            }
            //connects to bottom if open
            if(row != n && grid[toIndex(row+1,col)]){
                union(toIndex(row, col), toIndex(row+1, col));
            }
            //connects to virtual bottom
            if(row >= n){
                backwashUF.union(toIndex(row, col), BOTTOM);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col){
        validateBounds(row, col);
        return grid[toIndex(row,col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col){
        validateBounds(row, col);
        return uf.connected(toIndex(row,col), TOP) && isOpen(row,col);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return backwashUF.connected(TOP, BOTTOM);
    }

    private void validateBounds(int row, int col) {
        if(row <= 0 || row > n || col <= 0 || col > n){
            throw new IllegalArgumentException("row and col must be inclusively in the range of 1 and n");
        }
    }

    //convert 2d array index i(1 <= i <= n) to 1d array index i (0 <= i < n)
    private int toIndex(int row, int col){
        return ((row-1)*n)+(col-1);
    }

    private void union(int p, int q){
        uf.union(p, q);
        backwashUF.union(p, q);
    }

    //test client (optional)
    public static void main(String[] args){
//        Percolation perc = new Percolation(10);
//        perc.open(2,2);
//        perc.open(3,2);
//        perc.open(2,1);
//        perc.open(2,3);
//        perc.open(1,2);
//        System.out.println(perc.uf.connected(perc.toIndex(2,2), perc.toIndex(3,2)));
    }
}
