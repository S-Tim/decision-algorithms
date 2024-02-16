package org.example.evaluation

import org.example.model.GameState

interface Evaluator {
    fun evaluate(gameState: GameState): Int
}