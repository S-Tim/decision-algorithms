package org.example

import org.example.player.MiniMaxPlayer
import org.example.player.RandomPlayer

fun main() {
    val competition = Competition(RandomPlayer(), MiniMaxPlayer(), 100)
    println(competition.play())
}