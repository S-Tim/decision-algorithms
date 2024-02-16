package org.example.player

import org.example.model.GameState

interface Player {
    val name: String
    fun getMove(game: GameState): Int
}