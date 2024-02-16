package org.example.player

import org.example.model.GameState
import org.example.model.Result
import kotlin.math.max
import kotlin.math.min

class MinMax(override val name: String = "MinMax") : EvaluationPlayer() {
    var visitedPositions = 0

    override fun getMove(game: GameState): Int {
        val maximizingPlayer = game.currentPlayer == 0
        val moves = game.getMoves()
        val evaluations = moves.associateWith { move -> minmax(game.move(move), Int.MAX_VALUE, !maximizingPlayer) }

        // Randomly return one of the best moves
        val bestEval =
            if (maximizingPlayer) evaluations.maxBy { it.value }.value else evaluations.minBy { it.value }.value
        return evaluations.filter { it.value == bestEval }.keys.random()
    }

    private fun minmax(game: GameState, depth: Int, maximizingPlayer: Boolean): Int {
        visitedPositions += 1

        if (game.state != Result.OPEN || depth == 0) return evaluator.evaluate(game)

        val moves = game.getMoves()
        if (maximizingPlayer) {
            var maxEval = Int.MIN_VALUE
            for (move in moves) {
                val eval = minmax(game.move(move), depth - 1, false)
                maxEval = max(maxEval, eval)
            }
            return maxEval
        } else {
            var minEval = Int.MAX_VALUE
            for (move in moves) {
                val eval = minmax(game.move(move), depth - 1, true)
                minEval = min(minEval, eval)
            }
            return minEval
        }
    }
}