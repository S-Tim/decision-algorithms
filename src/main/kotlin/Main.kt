package org.example

import org.example.player.MinMax
import org.example.player.RandomPlayer

fun main() {
    val player1 = RandomPlayer()
    val player2 = MinMax()
    val competition = Competition(player1, player2, 100)
    println(competition.play())
    println("${player2.name}: ${player2.evaluationCounter}")
}