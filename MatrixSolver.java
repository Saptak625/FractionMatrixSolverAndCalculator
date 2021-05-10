package com.saptakdas.misc;

import com.saptakdas.chemistry.chemicalequation.version4.AlgebraEquation;
import com.saptakdas.chemistry.chemicalequation.version4.Fraction;
import com.saptakdas.chemistry.chemicalequation.version4.Matrix;

import java.util.Scanner;

public class MatrixSolver {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Enter Size of Matrix without Augmented Side: ");
        System.out.print("Columns: ");
        int dimension1=sc.nextInt();
        System.out.print("Rows: ");
        int dimension2=sc.nextInt();
        System.out.println("Enter matrix such that augmented matrix is on same side as other variables. In other words, get all equations into x+y+z+...+3=0 form");
        Fraction[][] fractionArr = new Fraction[dimension1][dimension2+1];
        Matrix matrix = new Matrix(fractionArr);
        for(int i=0; i<dimension1*(dimension2+1); i++){
            int position1 = i/(dimension2+1);
            int position2 = i%(dimension2+1);
            System.out.println("\nPosition ("+(position1+1)+", "+(position2+1)+")");
            System.out.print("Fraction: ");
            String fractionString = sc.next();
            Integer numerator = null;
            Integer denominator = null;
            if(fractionString.contains("/")){
                String[] splitString = fractionString.split("/");
                numerator = Integer.parseInt(splitString[0]);
                denominator = Integer.parseInt(splitString[1]);
            }else{
                numerator = Integer.parseInt(fractionString);
                denominator = 1;
            }
            fractionArr[position1][position2] = new Fraction(numerator, denominator);
        }
        matrix.gaussianElimination();
        AlgebraEquation.setSize(dimension2+1);
        for(int i=matrix.matrix.length-1; i>=0; i--) {
            //Iterating through reverse
            AlgebraEquation newAlgebraEquation = new AlgebraEquation(matrix.matrix[i]);
        }
        System.out.println("Solution: ");
        for(int i=0; i<AlgebraEquation.variables.length-1; i++) {
            System.out.println((i+1)+ ": " + AlgebraEquation.variables[i]);
        }
    }
}