package org.example.player

import org.example.model.GameState

class AlphaBetaWithMoveSorting(override val name: String = "AlphaBetaWithMoveSorting") : AlphaBeta() {
    // Center, corner, edges
    private val moveOrder = listOf(4, 0, 2, 6, 8, 1, 3, 5, 7)

    override fun getMoves(gameState: GameState): List<Int> {
        return gameState.getMoves().sortedBy { moveOrder.indexOf(it) }
    }
}