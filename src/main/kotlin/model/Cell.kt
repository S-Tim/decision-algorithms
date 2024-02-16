package org.example.model

enum class CellValue(private val label: String) {
    X("X"),
    O("O"),
    EMPTY("-");

    override fun toString(): String = this.label
}
