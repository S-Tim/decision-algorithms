package org.example

import org.example.player.AlphaBetaWithMoveSorting
import org.example.player.RandomPlayer

fun main() {
    val player1 = AlphaBetaWithMoveSorting()
    val player2 = RandomPlayer()
    val competition = Competition(player1, player2, 100)

    println(competition.play())
    println("${player1.visitedPositions} visited position for ${player1.name}")
    println("${player2.visitedPositions} visited position for ${player2.name}")
}