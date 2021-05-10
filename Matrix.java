package com.saptakdas.chemistry.chemicalequation.version4;

/**
 * Simple Matrix class with 2d array representation. Implements all primary matrix operations(row swapping, row addition, row multiplication). Also, includes implementation of a custom gaussian elimination algorithm. Note all terms inside of Matrix are represented as Fractions. Note that row indexes start from 1, NOT 0. Note that matrix is not augmented(other side is assumed to be all 0's).
 */
public class Matrix {
    public Fraction[][] matrix;

    /**
     * Matrix Initializer that converts int array into fraction array
     * @param matrix 2d int array representation of matrix
     */
    public Matrix(int[][] matrix){
        this.matrix = new Fraction[matrix.length][matrix[0].length];
        for(int j=0; j<matrix.length; j++) {
            int[] row = matrix[j];
            Fraction[] fractionRow = new Fraction[row.length];
            for(int i=0; i<fractionRow.length; i++) {
                fractionRow[i] = new Fraction(row[i], 1);
            }
            this.matrix[j] = fractionRow;
        }
    }

    public Matrix(Fraction[][] matrix){
        this.matrix = matrix;
    }

    /**
     * Simple Function that swaps rows
     * @param row1 Row Index 1
     * @param row2 Row Index 2
     */
    public void rowSwap(int row1, int row2){
        Fraction[] temp = this.matrix[row1-1];
        this.matrix[row1-1] = this.matrix[row2-1];
        this.matrix[row2-1] = temp;
    }

    /**
     * Simple Function that adds 2 rows
     * @param row1 Row Index 1
     * @param row2 Row Index 2 (Result is stored in this row)
     */
    public void rowAddition(int row1, int row2){
        Fraction[] addedRow = new Fraction[this.matrix[row1-1].length];
        for(int i=0; i<this.matrix[row1-1].length; i++){
            addedRow[i] = Fraction.add(this.matrix[row1-1][i], this.matrix[row2-1][i]);
        }
        this.matrix[row2-1] = addedRow;
    }

    /**
     * Simple Function that multiplies 2 rows
     * @param scalar Fraction scalar
     * @param row Row Index
     */
    public void rowMultiplication(Fraction scalar, int row){
        for(int i=0; i<this.matrix[row-1].length; i++){
            Fraction f = this.matrix[row-1][i];
            this.matrix[row-1][i] = Fraction.multiply(f, scalar);
        }
    }

    /**
     * Custom Implementation of a Gaussian Elimination Algorithm. Note matrix does not need to be square.
     */
    public void gaussianElimination() {
        for(int column=0; column<this.matrix.length; column++){
            //Check if current column is 1.
            if(!this.matrix[column][column].equals(new Fraction(1, 1))){
                //Find any existing ones
                Integer oneIndex = null;
                Integer otherIndex = null;
                for(int i=column; i<this.matrix.length; i++) {
                    if(this.matrix[i][column].equals(new Fraction(1,1))){
                        if(oneIndex == null) {
                            oneIndex=i;
                        }
                    }else if(!this.matrix[i][column].equals(new Fraction(0,1))) {
                        if(otherIndex == null) {
                            otherIndex=i;
                        }
                    }
                }
                if(oneIndex != null) {
                    this.rowSwap(column + 1, oneIndex + 1);
                }else if(otherIndex != null) {
                    this.rowSwap(column + 1, otherIndex + 1);
                    this.rowMultiplication(new Fraction(this.matrix[column][column].denominator, this.matrix[column][column].numerator), column + 1);
                }else {
                    System.out.println(column);
                    System.out.println();
                    System.out.println("All Zeroes found!");
                    System.exit(0);
                }
            }
            //Substitute all zeroes below.
            for(int i=column+1; i<this.matrix.length; i++) {
                if(!this.matrix[i][column].equals(new Fraction(0, 1))) {
                    Fraction oldScalar = this.matrix[i][column];
                    this.rowMultiplication(Fraction.negate(oldScalar), column+1);
                    this.rowAddition(column+1, i+1);
                    this.rowMultiplication(Fraction.negate(new Fraction(oldScalar.denominator, oldScalar.numerator)), column+1);
                }
            }
        }
    }

    /**
     * @return String Representation of Matrix
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Fraction[] row: this.matrix) {
            string.append("[");
            for(int i=0; i<row.length; i++){
                string.append(row[i].toString());
                if(i<(row.length-1)){
                    string.append(", ");
                }
            }
            string.append("]\n");
        }
        return string.toString();
    }
}
