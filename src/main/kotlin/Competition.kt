package org.example

import org.example.model.GameState
import org.example.model.Result
import org.example.player.Player
import kotlin.time.measureTime

class Competition(private val player1: Player, private val player2: Player, private val numberOfGames: Int = 100) {
    fun play(): CompetitionResult {
        val results = mutableMapOf<String, Int>()
        val thinkingTimes = mutableMapOf<String, Long>()

        repeat(numberOfGames) {
            val shuffledPlayers = listOf(player1, player2).shuffled()
            var game = GameState()
            var currentPlayer = 0

            while (game.state == Result.OPEN) {
                val player = shuffledPlayers[currentPlayer]

                val move: Int
                val thinkingTime = measureTime { move = player.getMove(game) }.inWholeNanoseconds
                thinkingTimes[player.name] = thinkingTimes.getOrDefault(player.name, 0) + thinkingTime
                game = game.move(move)

                currentPlayer = (currentPlayer + 1) % shuffledPlayers.size
            }

            if (game.state == Result.DRAW) {
                results["Draw"] = results.getOrDefault("Draw", 0) + 1
            } else {
                val winner = shuffledPlayers[game.currentPlayer].name
                results[winner] = results.getOrDefault(winner, 0) + 1
            }
        }

        return CompetitionResult(numberOfGames, results, thinkingTimes)
    }
}