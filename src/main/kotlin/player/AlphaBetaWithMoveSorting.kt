package org.example.player

import org.example.model.GameState
import org.example.model.Result

class AlphaBetaWithMoveSorting(override val name: String = "AlphaBetaWithMoveSorting") : Player {
    var evaluationCounter = 0
    val moveOrder = listOf(
        Pair(1, 1),
        Pair(0, 0),
        Pair(0, 2),
        Pair(2, 0),
        Pair(2, 2),
        Pair(0, 1),
        Pair(1, 0),
        Pair(1, 2),
        Pair(2, 1)
    )

    override fun getMove(game: GameState): Pair<Int, Int> {
        // evaluationCounter = 0

        val maximizingPlayer = game.currentPlayer == 0
        val bestMove = minimax(game, Int.MAX_VALUE, Int.MIN_VALUE, Int.MAX_VALUE, maximizingPlayer).second

        // println("$name: $evaluationCounter")
        return bestMove
    }

    private fun minimax(
        game: GameState,
        depth: Int,
        alpha: Int,
        beta: Int,
        maximizingPlayer: Boolean
    ): Pair<Int, Pair<Int, Int>> {
        evaluationCounter += 1

        if (game.state != Result.OPEN || depth == 0) {
            return when (game.state) {
                Result.X -> 1 to Pair(0, 0)
                Result.O -> -1 to Pair(0, 0)
                Result.DRAW -> 0 to Pair(0, 0)
                Result.OPEN -> 0 to Pair(0, 0)
            }
        }

        // alpha: best evaluation the maximizing player can achieve so far
        // beta: best evaluation the minimizing player can achieve so far
        val moves = game.getMoves().sortedBy { moveOrder.indexOf(it) }
        if (maximizingPlayer) {
            var maxEval = alpha
            var bestMove = Pair(0, 0)
            for (move in moves) {
                val (eval, _) = minimax(game.move(move), depth - 1, maxEval, beta, false)
                if (eval > maxEval) {
                    maxEval = eval
                    bestMove = move
                }
                if (beta <= maxEval) break
            }
            return maxEval to bestMove
        } else {
            var minEval = beta
            var bestMove = Pair(0, 0)
            for (move in moves) {
                val (eval, _) = minimax(game.move(move), depth - 1, alpha, minEval, true)
                if (eval < minEval) {
                    minEval = eval
                    bestMove = move
                }
                if (minEval <= alpha) break
            }
            return minEval to bestMove
        }
    }
}