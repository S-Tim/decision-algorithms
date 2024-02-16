package org.example.evaluation

import org.example.model.GameState
import org.example.model.Result

class FinalPositionEvaluator : Evaluator {
    override fun evaluate(gameState: GameState): Int {
        return when (gameState.state) {
            Result.X -> 1
            Result.O -> -1
            Result.DRAW -> 0
            Result.OPEN -> 0
        }
    }
}