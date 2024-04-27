package com.example.sudoku.model;

/**
 * La interfaz ISudoku define los métodos necesarios para trabajar con un
 * tablero de Sudoku.
 * Implementa métodos para verificar la presencia de un número en una fila,
 * columna o subcuadrícula,
 * y para establecer un número en una celda específica del tablero.
 * 
 * @version 1.0
 * @since 1.0
 */
public interface ISudoku {
    /**
     * Verifica si un número está presente en una fila específica del tablero de
     * Sudoku.
     * 
     * @param number El número a verificar.
     * @param row    La fila en la que se realiza la verificación.
     * @return true si el número está presente en la fila, false de lo contrario.
     */
    Boolean isNumberHorizontal(int number, int row);

    /**
     * Verifica si un número está presente en una columna específica del tablero de
     * Sudoku.
     * 
     * @param number El número a verificar.
     * @param col    La columna en la que se realiza la verificación.
     * @return true si el número está presente en la columna, false de lo contrario.
     */
    Boolean isNumberVertical(int number, int col);

    /**
     * Verifica si un número está presente en una subcuadrícula de 3x3 del tablero
     * de Sudoku.
     * 
     * @param sudokuGrid El tablero de Sudoku.
     * @param number     El número a verificar.
     * @param row        La fila de la celda en la subcuadrícula.
     * @param col        La columna de la celda en la subcuadrícula.
     * @return true si el número está presente en la subcuadrícula, false de lo
     *         contrario.
     */
    Boolean isNumberInGroupCell(int[][] sudokuGrid, int number, int row, int col);

    /**
     * Establece un número en una celda específica del tablero de Sudoku.
     * 
     * @param number El número a establecer.
     * @param row    La fila de la celda.
     * @param col    La columna de la celda.
     */
    void setNumberInSudokuGrid(int number, int row, int col);
}