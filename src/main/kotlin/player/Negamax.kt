package org.example.player

import org.example.model.GameState
import org.example.model.Result

class Negamax(override val name: String = "Negamax") : EvaluationPlayer() {
    var visitedPositions = 0

    override fun getMove(game: GameState): Int {
        // pick -1000 and 1000 as boundaries here because -Int.MIN_VALUE overflows...
        val color = if (game.currentPlayer == 0) 1 else -1
        return negamax(game, Int.MAX_VALUE, -1000, 1000, color).second
    }

    private fun negamax(game: GameState, depth: Int, alpha: Int, beta: Int, color: Int): Pair<Int, Int> {
        visitedPositions += 1

        if (game.state != Result.OPEN || depth == 0) return evaluator.evaluate(game) * color to 0

        val moves = game.getMoves().shuffled()
        var maxEval = alpha
        var bestMove = 100

        for (move in moves) {
            val eval = -negamax(game.move(move), depth - 1, -beta, -maxEval, -color).first
            if (eval > maxEval) {
                maxEval = eval
                bestMove = move
            }
            if (maxEval >= beta) break
        }

        return maxEval to bestMove
    }
}