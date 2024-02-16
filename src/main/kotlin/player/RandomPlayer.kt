package org.example.player

import org.example.model.GameState

class RandomPlayer(override val name: String = "Random") : Player {
    val visitedPositions = 0

    override fun getMove(game: GameState): Int {
        return game.getMoves().random()
    }
}