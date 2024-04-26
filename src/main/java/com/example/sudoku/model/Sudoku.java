package com.example.sudoku.model;

import java.util.Random;

public class Sudoku implements ISudoku{
    private int[][] sudokuGrid; // Matriz para almacenar los números del Sudoku (sin resolver)
//    private int[][] gridNumbers; // Matriz para almacenar los números predeterminados por cuadrícula
//    private Random random = new Random(); // Generador de números aleatorios

    public Sudoku(){
        this.sudokuGrid = new int [][]{
                {3,0,9, 0,7,2, 5,0,0},
                {4,0,0, 0,0,0, 1,0,7},
                {0,0,6, 9,0,1, 0,4,0},

                {5,0,0, 8,0,0, 9,2,0},
                {0,9,0, 0,0,5, 0,0,3},
                {6,0,7, 0,9,4, 0,5,0},

                {9,0,4, 0,2,0, 6,0,0},
                {0,0,0, 5,8,0, 0,3,0},
                {8,3,0, 0,0,6, 2,0,9}};
    }


    /**
     * @param number
     * @param row
     * @return
     */
    @Override
    public Boolean isNumberHorizontal(int number, int row) {
        for(int i=0; i<9; i++){
            if(this.sudokuGrid[row][i] == number){
                return true;
            }
        }
        return false;
    }

    /**
     * @param number
     * @param col
     * @return
     */
    @Override
    public Boolean isNumberVertical(int number, int col) {
        for(int i=0; i<9; i++){
            if(this.sudokuGrid[i][col] == number){
                return true;
            }
        }
        return false;
    }

    /**
     * @param number
     * @param row
     * @param col
     * @return
     */
    @Override
    public Boolean isNumberInGroupCell(int number, int row, int col) {
        return true;
    }

    /**
     * @param number
     * @param row
     * @param col
     */
    @Override
    public void setNumberInSudokuGrid(int number, int row, int col) {
        this.sudokuGrid[row] [col] = number;
    }

    public int[][] getSudokuGrid() {
        return sudokuGrid;
    }

    public void setSudokuGrid(int[][] tableSudoku) {
        this.sudokuGrid = sudokuGrid;
    }


}
