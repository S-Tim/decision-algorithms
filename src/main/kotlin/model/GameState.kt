package org.example.model

data class GameState(
    val xCells: Int = 0,
    val oCells: Int = 0,
    val emptyCells: Int = 511,
    val currentPlayer: Int = 0,
    val state: Result = Result.OPEN
) {
    fun getMoves(): Set<Int> {
        val result = mutableSetOf<Int>()
        var start = emptyCells
        for (i in 0..8) {
            if (start and 1 != 0) {
                result.add(i)
            }
            start = start shr 1
        }
        return result
    }

    fun move(position: Int): GameState {
        val moves = getMoves()
        if (position !in moves) throw IllegalArgumentException("Move is not legal")

        val nextXCells = if (currentPlayer == 0) xCells or (1 shl position) else xCells
        val nextOCells = if (currentPlayer == 1) oCells or (1 shl position) else oCells
        val nextEmptyCells = (nextXCells or nextOCells).inv() and 0b111_111_111
        val nextState = calculateGameState(nextXCells, nextOCells, nextEmptyCells)

        val nextPlayer = if (nextState != Result.OPEN) currentPlayer else (currentPlayer + 1) % 2
        return this.copy(
            xCells = nextXCells,
            oCells = nextOCells,
            emptyCells = nextEmptyCells,
            currentPlayer = nextPlayer,
            state = nextState
        )
    }

    override fun toString(): String {
        var board = ""
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                val tile = i * 3 + j
                val binary = 1 shl tile
                board += if (binary and xCells != 0) "X " else if (binary and oCells != 0) "O " else "- "
            }
            board += "\n"
        }
        return board
    }

    companion object {
        private val winning_positions = listOf(
            0b000_000_111,
            0b000_111_000,
            0b111_000_000,
            0b001_001_001,
            0b010_010_010,
            0b100_100_100,
            0b100_010_001,
            0b001_010_100
        )

        fun calculateGameState(xCells: Int, oCells: Int, emptyCells: Int): Result {
            if (winning_positions.any { it and xCells == it }) return Result.X
            if (winning_positions.any { it and oCells == it }) return Result.O
            if (emptyCells == 0) return Result.DRAW

            return Result.OPEN
        }
    }
}