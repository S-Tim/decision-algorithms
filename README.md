# TicTacToe

This is project for better understanding decision algorithms in board games. It illustrates different techniques
and performance optimizations and compares them.

## Algorithm Comparisons

| Name                        | Average thinking time per match | Average visited position per match |
|-----------------------------|---------------------------------|------------------------------------|
| Random                      | 0.01ms                          | 0                                  |
| MinMax                      | 209.49ms (100%)                 | 300197 (100%)                      |
| AlphaBeta                   | 7.667ms (3,66%)                 | 9308 (3,1%)                        |
| AlphaBeta with move sorting | 4.116ms (1,97%)                 | 4967 (1,66%)                       |

## Bit Board Optimizations

Changing the game state to a more efficient format from a list of lists to integers drastically increased the
performance.
The state is now represented by an integer for each player and an integer for the empty tiles.
For example empty = 511 is equal to 111_111_111 in binary (underscore just for better visualization).
This means that the board would be completely empty, leaving X = 0 and O = 0.  
A little more complex example would be:

```
empty = 32 (000_100_000)
X = 275    (100_010_011)
O = 204    (011_001_100)
```

resulting in the board

```
X X O
O X -
O O X
```

The state represent the board in the following way

```
876_543_210

0 1 2
3 4 5
6 7 8
```

So, reading the cells row by row from left to right, cell c corresponds to the cth lowest bit in the state or 2^c.

After these optimizations the thinking times for the new thinking times for the algorithms are:

| Name                        | Average thinking time per match. Improvement in brackets |
|-----------------------------|----------------------------------------------------------|
| Random                      | 0.01ms (0.00%)                                           |
| MinMax                      | 28.96ms (86.18%)                                         |
| AlphaBeta                   | 1.263ms (83.53%)                                         |
| AlphaBeta with move sorting | 0.722ms (82.46%)                                         |