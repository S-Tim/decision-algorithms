# TicTacToe

This is project for better understanding decision algorithms in board games. It illustrates different techniques
and performance optimizations and compares them.

## Algorithm Comparisons

| Name                        | Average thinking time per match | Average visited position per match |
|-----------------------------|---------------------------------|------------------------------------|
| Random                      | 0.01ms                          | 0                                  |
| MiniMax                     | 209.49ms (100%)                 | 300197 (100%)                      |
| AlphaBeta                   | 7.667ms (3,66%)                 | 9308 (3,1%)                        |
| AlphaBeta with move sorting | 4.116ms (1,97%)                 | 4967 (1,66%)                       |