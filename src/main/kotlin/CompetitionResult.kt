package org.example

data class CompetitionResult(
    val numberOfGames: Int,
    val gameResults: Map<String, Int>,
    val thinkingTimes: Map<String, Long>
){
    override fun toString(): String {
        return "Games Played: $numberOfGames \n" +
                "Results: ${gameResults.map { "${it.key}: ${it.value}" }.joinToString(", ")}\n" +
                "Thinking times: ${thinkingTimes.map { "${it.key}: ${it.value / 1_000_000}ms" }.joinToString(", ")}"
    }
}