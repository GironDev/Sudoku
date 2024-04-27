package com.example.sudoku.model;

public class Sudoku implements ISudoku {
    private int[][] sudokuGrid;

    public Sudoku() {
        this.sudokuGrid = new int[][] {
                { 3, 0, 9, 0, 7, 2, 5, 0, 0 },
                { 4, 0, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 6, 9, 0, 1, 0, 4, 0 },

                { 5, 0, 0, 8, 0, 0, 9, 2, 0 },
                { 0, 9, 0, 0, 0, 5, 0, 0, 3 },
                { 6, 0, 7, 0, 9, 4, 0, 5, 0 },

                { 9, 0, 4, 0, 2, 0, 6, 0, 0 },
                { 0, 0, 0, 5, 8, 0, 0, 3, 0 },
                { 8, 3, 0, 0, 0, 6, 2, 0, 9 } };
    }

    // Solución Sudoku
//     this.sudokuGrid = new int [][]{
//     {3, 1, 9, 4, 7, 2, 5, 6, 8},
//     {4, 5, 2, 6, 3, 8, 1, 9, 7,},
//     {7, 8, 6, 9, 5, 1, 3, 4, 2},
//     {5, 4, 3, 8, 1, 7, 9, 2, 6},
//     {1, 9, 8, 2, 6, 5, 4, 7, 3},
//     {6, 2 ,7 ,3 ,9, 4 ,8, 5, 1},
//     {9 ,7 ,4 ,1, 2 ,3 ,6 ,8 ,5},
//     {2 ,6 ,1 ,5, 8 ,9, 7, 3, 0},
//     {8 ,3, 5, 7, 4 ,6 ,2, 1, 9}};
//     };

    /**
     * @param number
     * @param row
     * @return
     */
    @Override
    public Boolean isNumberHorizontal(int number, int row) {
        for (int i = 0; i < 9; i++) {
            if (this.sudokuGrid[row][i] == number) {
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
        for (int i = 0; i < 9; i++) {
            if (this.sudokuGrid[i][col] == number) {
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
    public Boolean isNumberInGroupCell(int[][] sudokuGrid, int number, int row, int col) {
//        System.out.println("Fila: " + row + " Columna: " + col);
        if (row >= 0 && row <= 2) {
            if (col >= 0 && col <= 2) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 3 && col <= 5) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 3; j < 6; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 6 && col <= 8) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 6; j < 9; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }

            }
        } else if (row >= 3 && row <= 5) {
            if (col >= 0 && col <= 2) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 0; j < 3; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 3 && col <= 5) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 3; j < 6; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 6 && col <= 8) {
                for (int i = 3; i < 6; i++) {
                    for (int j = 6; j < 9; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }

            }

        } else if (row >= 6 && row <= 8) {
            if (col >= 0 && col <= 2) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 0; j < 3; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 3 && col <= 5) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 3; j < 6; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }
            } else if (col >= 6 && col <= 8) {
                for (int i = 6; i < 9; i++) {
                    for (int j = 6; j < 9; j++) {
                        int numberToVerify = sudokuGrid[i][j];
                        if (numberToVerify != 0) {
                            if (numberToVerify == number) {
                                return true;
                            }
                        }
                    }
                }

            }

        }

        return false;
    }

    /**
     * @param number
     * @param row
     * @param col
     */
    @Override
    public void setNumberInSudokuGrid(int number, int row, int col) {
        this.sudokuGrid[row][col] = number;
    }

    public int[][] getSudokuGrid() {
        return sudokuGrid;
    }

    public void setSudokuGrid(int[][] sudokuGrid) {
        this.sudokuGrid = sudokuGrid;
    }


    public boolean isValidSudoku(int[][] sudokuGrid) {
        // this.sudokuGrid = getSudokuGrid();
        // Verificar Celdas vacías
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sudokuGrid[i][j];
                if (num == 0) {
                    // System.out.println("FALTA UNA CELDA");
                    return false;
                }
            }
        }

        // Verificar filas
        for (int i = 0; i < 9; i++) {
            boolean[] used = new boolean[10];
            for (int j = 0; j < 9; j++) {
                int num = sudokuGrid[i][j];
                if (num != 0) { //
                    if (used[num]) {
                        return false;
                    }
                    used[num] = true;
                }
            }
        }

        // Verificar columnas
        for (int j = 0; j < 9; j++) {
            boolean[] used = new boolean[10];
            for (int i = 0; i < 9; i++) {
                int num = sudokuGrid[i][j];
                if (num != 0) {
                    if (used[num]) {
                        return false;
                    }
                    used[num] = true;
                }
            }
        }

        // Si todas las filas y columnas son válidas, el Sudoku es válido
        // System.out.println("Victoria Legal Legalisima!?");
        return true;
    }

    public void getSolution(){
        // Solución Sudoku
     int solution[][] = new int [][]{
     {3, 1, 9, 4, 7, 2, 5, 6, 8},
     {4, 5, 2, 6, 3, 8, 1, 9, 7,},
     {7, 8, 6, 9, 5, 1, 3, 4, 2},
     {5, 4, 3, 8, 1, 7, 9, 2, 6},
     {1, 9, 8, 2, 6, 5, 4, 7, 3},
     {6, 2 ,7 ,3 ,9, 4 ,8, 5, 1},
     {9 ,7 ,4 ,1, 2 ,3 ,6 ,8 ,5},
     {2 ,6 ,1 ,5, 8 ,9, 7, 3, 4},
     {8 ,3, 5, 7, 4 ,6 ,2, 1, 9}
     };

     setSudokuGrid(solution);

     }


}
