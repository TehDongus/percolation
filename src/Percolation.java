import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Victor on 5/15/2017.
 */
public class Percolation {

    private WeightedQuickUnionUF uf;
    private boolean[] pointIsBlocked; //value of true means a blocked point
    private int n;
    private int openSites = 0;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        this.n = n;

        pointIsBlocked = new boolean[n*n];
        for (int i = 0; i < pointIsBlocked.length; i++) {
            pointIsBlocked[i] = true;
        }

        uf = new WeightedQuickUnionUF(n*n+2);
        //top row with single point
        for (int i = 0; i < n; i++) {
            uf.union(n*n,i);
        }

        //bottom row with single point
        for (int i = (n*(n-1)); i < n*n; i++) {
            uf.union((n*n+1), i);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if(pointIsBlocked[convertRowColumnToArrayIndex(row, col)]) {
            pointIsBlocked[convertRowColumnToArrayIndex(row, col)] = false;
            openSites++;

            //runs if site to open has 4 touching sites
            if(row < n && row > 1 && col < n && col > 1) {
                //connects to top
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                    uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row,col));
                //connects to left
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row,col-1));
                //connects to right
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col+1)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row,col+1));
                //connects to bottom
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1, col)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row+1,col));

            }
            //top
            else if(row == 1) {
                //top left
                if(col == 1) {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col+1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row,col+1));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1, col)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row+1,col));
                }
                //top right
                else if(col == n) {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row,col-1));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1, col)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row+1,col));
                }
                //top middle
                else {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col-1));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col+1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col+1));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1, col)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row+1, col));
                }

            }
            //bottom
            else if(row == n ){
                //bottom left
                if(col == 1) {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                        uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row, col));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col+1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col+1));
                }
                //bottom right
                else if(col == n) {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                        uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row, col));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col-1));
                }
                //bottom middle
                else {
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                        uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row, col));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col-1));
                    if(!pointIsBlocked[convertRowColumnToArrayIndex(row,col+1)])
                        uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col+1));
                }
            }
            //left
            else if(col == 1) {
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                    uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row, col));
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col+1)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col+1));
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1,col)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row + 1, col));
            }
            //right
            else if(col == n) {
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row-1, col)])
                    uf.union(convertRowColumnToArrayIndex(row-1, col), convertRowColumnToArrayIndex(row, col));
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row, col-1)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row, col-1));
                if(!pointIsBlocked[convertRowColumnToArrayIndex(row+1, col)])
                    uf.union(convertRowColumnToArrayIndex(row, col), convertRowColumnToArrayIndex(row + 1, col));
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        return !pointIsBlocked[convertRowColumnToArrayIndex(row, col)];

    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        //if connected to top and not blocked
        return (uf.connected(convertRowColumnToArrayIndex(row, col), n*n) &&
                !pointIsBlocked[convertRowColumnToArrayIndex(row, col)]);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(n*n, n*n+1);
    }

    private int convertRowColumnToArrayIndex(int row, int col){
        if (row <= n && col <= n && row >= 0 && col >= 0){
            return ((row-1)*n)+(col-1);
        }
        else{
            throw new IndexOutOfBoundsException("The row or column is out of range");
        }
    }


    // test client (optional)
    public static void main(String[] args)  {

    }
}