package org.example.model

data class GameState(
    val board: List<List<CellValue>> = (0 until 3).map { (0 until 3).map { CellValue.EMPTY } },
    val currentPlayer: Int = 0,
    val state: Result = Result.OPEN
) {
    fun getMoves(): Set<Pair<Int, Int>> {
        return (board.indices).flatMap { row -> board.indices.map { col -> row to col } }
            .filter { board[it.first][it.second] == CellValue.EMPTY }.toSet()
    }

    fun move(position: Pair<Int, Int>): GameState {
        val moves = getMoves()
        if (position !in moves) throw IllegalArgumentException("Move is not legal")

        val nextBoard = mutableListOf<MutableList<CellValue>>()
        for (i in board.indices) {
            val inner = mutableListOf<CellValue>()
            for (j in board.indices) {
                if (i == position.first && j == position.second) inner.add(if (currentPlayer == 0) CellValue.X else CellValue.O)
                else inner.add(board[i][j])
            }
            nextBoard.add(inner)
        }

        val nextState = calculateGameState(nextBoard)
        val nextPlayer = if (nextState != Result.OPEN) currentPlayer else (currentPlayer + 1) % 2
        return this.copy(
            board = nextBoard,
            currentPlayer = nextPlayer,
            state = nextState
        )
    }

    override fun toString(): String {
        return board.joinToString("\n") { it.joinToString(" ") }
    }

    companion object {
        private fun calculateGameState(board: List<List<CellValue>>): Result {
            for (i in board.indices) {
                // horizontal
                if (board[i].all { it == board[i][0] && board[i][0] != CellValue.EMPTY }) return cellValueToResult(board[i][0])

                // vertical
                if (board.indices.map { board[it][i] }
                        .all { it == board[0][i] && board[0][i] != CellValue.EMPTY }) return cellValueToResult(board[0][i])
            }

            // diagonal
            if (board.indices.map { board[it][it] }
                    .all { it == board[0][0] && board[0][0] != CellValue.EMPTY }) return cellValueToResult(board[0][0])
            if (board.indices.map { board[it][board.size - it - 1] }
                    .all { it == board[0][board.size - 1] && board[0][board.size - 1] != CellValue.EMPTY })
                return cellValueToResult(board[0][board.size - 1])

            return if (board.all { row -> row.all { it != CellValue.EMPTY } }) Result.DRAW else Result.OPEN
        }

        private fun cellValueToResult(value: CellValue): Result = if (value == CellValue.X) Result.X else Result.O
    }
}