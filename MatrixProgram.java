package com.saptakdas.misc.MatrixCalculator;

public class MatrixProgram {
    public static void main(String[] args) throws MatrixError {
        //Ask whether to access calculator or solver
        int choice=Menu.choice("---Matrix Program---\n1: Matrix Calculator\n2: Matrix Solver", 2);
        if(choice==1){
            MatrixCalculator calculator=new MatrixCalculator();
        }else{
            MatrixSolver solver=new MatrixSolver();
        }
    }
}
