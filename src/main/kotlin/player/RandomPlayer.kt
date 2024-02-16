package org.example.player

import org.example.model.GameState

class RandomPlayer(override val name: String = "Random") : Player {
    override fun getMove(game: GameState): Pair<Int, Int> {
        return game.getMoves().random()
    }

    override fun toString(): String {
        return name
    }
}