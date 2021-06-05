package com.saptakdas.misc.MatrixCalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static int choice(String message, int numOfChoices){
        Integer choice=null;
        boolean answered = false;
        while(!answered) {
            Scanner sc = new Scanner(System.in);
            System.out.println(message);
            System.out.print("Choice: ");
            try {
                choice = sc.nextInt();
                if (choice >= 1 && choice <= numOfChoices) {answered = true;}else{System.out.println("Please enter a number between 1 and "+numOfChoices+".\n");}
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number!\n");
            }
        }
        System.out.println();
        return choice;
    }
}
