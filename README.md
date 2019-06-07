# Assignment 1: Percolation
From Coursera course:  [Princeton University Algorithms 1 with Robbert Sedgewick and Kevin Wayne.](https://www.coursera.org/learn/algorithms-part1) 

[Given a composite systems comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.](http://coursera.cs.princeton.edu/algs4/assignments/percolation.html)

Uses edu.princeton.cs.algs4.WeightedQuickUnionUF from their [free library](https://algs4.cs.princeton.edu/code/).

My submission got an 99/100 with backwash resolved. The missing point was due to failing a minor test (I called some functions 2-3 more times in percolationstats than was necessary, however this has no real impact on performance)

Snippit of feedback log:
Count calls to StdStats.mean() and StdStats.stddev()
calls StdStats.mean() the wrong number of times - number of student calls to StdStats.mean() = 2 - number of reference calls to StdStats.mean() = 1 - calls StdStats.stddev().
