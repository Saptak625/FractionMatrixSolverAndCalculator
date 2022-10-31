package com.saptakdas.misc.MatrixCalculator;

import com.saptakdas.util.Menu;

import java.util.HashMap;
import java.util.Scanner;

public class MatrixCalculator {
    public static Scanner sc = new Scanner(System.in);
    public static HashMap<String, Matrix> matrixSpace = new HashMap<>();

    public MatrixCalculator() {
        //Initialize matrixSpace
        for(int i=0; i<10; i++){
            matrixSpace.put(Character.toString((char) (65+i)), null);
        }
        boolean notNull = false;
        while(true){
            int choice = 1;
            if(notNull) {
                choice= Menu.choice("Operation: \n1: Enter/Update a Matrix\n2: Find Determinant of a Matrix\n3: Matrix Math\n4: Quit Calculator", 4);
            }else{
                System.out.println("Enter a new Matrix to start using the Matrix Calculator.");
            }
            if(choice == 1){
                Matrix matrix = new Matrix(false);
                int store=Menu.choice("Store in:\n"+getMatrixList(), 10);
                matrixSpace.put(Character.toString((char) (65+store-1)), matrix);
                notNull=true;
            }else if(choice == 2){
                Matrix matrixNull = null;
                int determinantMatrix = 1;
                while(matrixNull==null) {
                    determinantMatrix = Menu.choice("Find Determinant of:\n" + getMatrixList(), 10)-1;
                    matrixNull=matrixSpace.get(Character.toString((char) (65+determinantMatrix)));
                    if(matrixNull==null) {
                        System.out.println("Please select a matrix that has been entered already!");
                    }
                }
                try {
                    System.out.println("Determinant of Matrix "+ (char) (65 + determinantMatrix) +": "+matrixNull.determinant()+"\n");
                } catch (MatrixError matrixError) {
                    System.out.println("Determinant cannot be calculated for a non-square matrix.\n");
                }
            }else if(choice == 3){
                System.out.println("Please only enter basic matrix math at once(Only one operation).");
                int matrixToOperate = Menu.choice("Please select a matrix to operate on:\n" + getMatrixList(), 10) - 1;
                Matrix matrixNull = matrixSpace.get(Character.toString((char) (65 + matrixToOperate)));
                if (matrixNull == null) {
                    System.out.println("Please select a matrix that has been entered already!");
                }
                Matrix answer=null;
                int operationChoice = Menu.choice("Please select an operation choice: \n1: Inverse\n2: Matrix Addition\n3: Matrix Multiplication\n4: Scalar Multiplication", 4);
                try {
                    if(operationChoice == 1) {
                        answer = matrixNull.inverse();
                    }else if(operationChoice == 2 || operationChoice == 3){
                        matrixToOperate = Menu.choice("Please select a matrix to operate on:\n" + getMatrixList(), 10) - 1;
                        Matrix matrixNull2 = matrixSpace.get(Character.toString((char) (65 + matrixToOperate)));
                        if (matrixNull2 == null) {
                            System.out.println("Please select a matrix that has been entered already!");
                        }
                        if(operationChoice == 2){
                            answer = Matrix.add(matrixNull, matrixNull2);
                        }else{
                            answer = Matrix.multiply(matrixNull, matrixNull2);
                        }
                    }else{
                        System.out.print("Scalar Fraction: ");
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
                        Fraction scalarFraction = new Fraction(numerator, denominator);
                        answer=Matrix.multiply(scalarFraction, matrixNull);
                    }
                    System.out.println("\nAnswer: ");
                    System.out.println(answer+"\n");
                    boolean choiceStore = Menu.choice("Do you want to store the answer?\n1: Yes\n2: No", 2) == 1;
                    if(choiceStore) {
                        int store = Menu.choice("Store in:\n" + getMatrixList(), 10);
                        matrixSpace.put(Character.toString((char) (65 + store - 1)), answer);
                        notNull = true;
                    }
                }catch (MatrixError matrixError) {
                    System.out.println(matrixError.getMessage()+"\n");
                }
            }else{
                System.out.println("Quitting Calculator...");
                break;
            }
        }
    }

    public static String getMatrixList(){
        StringBuilder list= new StringBuilder();
        for(int i=0; i<10; i++){
            Matrix matrix = matrixSpace.get(Character.toString((char) (65 + i)));
            list.append((i+1)+": ["+Character.toString((char) (65 + i))+"] "+(matrix==null? "(empty)": matrix.matrix.length+"x"+matrix.matrix[0].length)+"\n");
        }
        return list.toString();
    }
}
