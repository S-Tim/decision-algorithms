package org.example

import org.example.player.*

fun main() {
    val player1 = AlphaBeta()
    val player2 = Negamax()
    val competition = Competition(player1, player2, 1000)

    println(competition.play())
    println("${player1.visitedPositions} visited position for ${player1.name}")
    println("${player2.visitedPositions} visited position for ${player2.name}")
}