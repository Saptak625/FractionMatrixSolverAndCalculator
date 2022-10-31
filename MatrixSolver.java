package com.saptakdas.misc.MatrixCalculator;

import com.saptakdas.util.Menu;

import java.util.Scanner;

public class MatrixSolver {
    public static Scanner sc = new Scanner(System.in);
    public int dimension1;
    public int dimension2;
    public Matrix matrix;
    
    public MatrixSolver() throws MatrixError {
        this.matrix = new Matrix(true);
        this.dimension1 = this.matrix.matrix.length;
        this.dimension2 = this.matrix.matrix[0].length;
        //Ask for solving method
        int choice= Menu.choice("1: Gaussian Elimination with Back Substitution\n2: Gauss-Jordan Elimination\n3: Matrix Equations\n4: Cramer's Rule", 4);
        if (choice == 1) {
            this.matrix.gaussianWithBackSubstitution();
            System.out.println("Solution: ");
            for(int i=0; i<AlgebraEquation.variables.length-1; i++) {
                System.out.println((i+1)+ ": " + AlgebraEquation.variables[i]);
            }
        }else if (choice == 2){
            this.matrix.gaussjordanElimination();
            System.out.println("Solution: ");
            for(int i=0; i<this.matrix.matrix.length; i++) {
                System.out.println((i+1)+ ": " + this.matrix.matrix[i][dimension2-1]);
            }
        }else if (choice == 3){
            Fraction[][] leftsubArr=new Fraction[dimension1][dimension1];
            Fraction[][] rightsubArr=new Fraction[dimension1][1];
            for(int i=0; i<dimension1; i++){
                for(int j=0; j<dimension2; j++){
                    if(j<dimension2-1) {
                        leftsubArr[i][j] = this.matrix.matrix[i][j];
                    }else{
                        rightsubArr[i][0] = this.matrix.matrix[i][j];
                    }
                }
            }
            Matrix leftSideMatrix = new Matrix(leftsubArr);
            Matrix rightSideMatrix = new Matrix(rightsubArr);
            System.out.println("A: \n"+leftSideMatrix);
            System.out.println("\nB: \n"+rightSideMatrix);
            Matrix leftInverse = leftSideMatrix.inverse();
            System.out.println("\nA^-1: \n"+leftInverse);
            Matrix solutionMatrix = Matrix.multiply(leftInverse, rightSideMatrix);
            System.out.println("\nSolution Matrix: \n"+solutionMatrix);
            System.out.println("\nSolution: ");
            for(int i=0; i<solutionMatrix.matrix.length; i++) {
                System.out.println((i+1)+ ": " + solutionMatrix.matrix[i][0]);
            }
        }else if (choice == 4){
            Fraction[][] subArr=new Fraction[dimension1][dimension1];
            for(int i=0; i<dimension1; i++){
                for(int j=0; j<dimension2-1; j++){
                    subArr[i][j] = this.matrix.matrix[i][j];
                }
            }
            Matrix leftSideMatrix = new Matrix(subArr);
            Fraction determinant = leftSideMatrix.determinant();
            System.out.println("D: "+determinant);
            Fraction[] otherDeterminants = new Fraction[dimension1];
            for(int i=0; i<otherDeterminants.length; i++){
                //Make deep-copy of original matrix
                Fraction[][] deepCopy = new Fraction[dimension1][dimension1];
                System.arraycopy(subArr, 0, deepCopy, 0, subArr.length);
                for(int j=0; j<leftSideMatrix.matrix.length; j++){
                    Fraction[] destArr = new Fraction[deepCopy[j].length];
                    System.arraycopy(deepCopy[j], 0, destArr, 0, deepCopy[j].length);
                    deepCopy[j] = destArr;
                }
                for(int j=0; j<leftSideMatrix.matrix.length; j++){
                    deepCopy[j][i] = this.matrix.matrix[j][dimension2-1];
                }
                Matrix newMatrix = new Matrix(deepCopy);
                otherDeterminants[i] = newMatrix.determinant();
                System.out.println("D"+(i+1)+": "+otherDeterminants[i]);
            }
            System.out.println("\nSolution: ");
            for(int i=0; i<otherDeterminants.length; i++) {
                System.out.println((i+1)+ ": " + Fraction.multiply(otherDeterminants[i], new Fraction(determinant.denominator, determinant.numerator)));
            }
        }
    }
}