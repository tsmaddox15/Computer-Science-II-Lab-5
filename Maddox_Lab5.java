/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maddox_lab5;

import java.util.Scanner;
import java.awt.Point;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Taylor Maddox 
 * Instructor: Rick Volkers 
 * TA: Sai Polamarasetty
 */
public class Maddox_Lab5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        //Ask for a value for the rows and columns
        System.out.println("Enter the number of columns and rows you want.(Must be a value from 4-50)");
        Stack<Point> stack = new Stack<>();
        int size = keyboard.nextInt();

        //If the user doesn't type a value from 4-5- allows them to retry.
        while (size < 4 || size > 50) {
            System.out.println("Must enter a value from 4-50, try again.");
            System.out.println("Enter the number of columns and rows you want.(Must be a value from 4-50)");
            size = keyboard.nextInt();
        }

        //Arrays to check if they are safe which is set to true.
        boolean[] rows = new boolean[size];
        boolean[] columns = new boolean[size];
        boolean[] diagonalLeft = new boolean[2 * size - 1];
        boolean[] diagonalRight = new boolean[2 * size - 1];
        Arrays.fill(rows, true);
        Arrays.fill(columns, true);
        Arrays.fill(diagonalLeft, true);
        Arrays.fill(diagonalRight, true);

        int y = 0; //index for rows
        int x = 0; //index for the columns
        Point pt = new Point(x, y);

        //checkss if there are columns left.
        for (x = 0; x < size; x++) {

            //checks if rows are left than puts the queen in the safe spot and switchs correct array index to turn to unsafe
            // unsafe == false.
            for (y = 0; y < size; y++) {
                if (columns[x] == true && rows[y] == true && diagonalRight[(size - 1) + (x - y)] == true && diagonalLeft[(x + y)] == true) {
                    pt = new Point(x, y);
                    stack.push(pt);
                    diagonalRight[(size - 1) + (x - y)] = false;
                    diagonalLeft[(x + y)] = false;
                    rows[y] = false;
                    columns[x] = false;
                    y = 0;
                } 
                // use to backtrack if needed and switchs values to true when the queen is popped of the stack.
                else {
                    if (y == size - 1 && columns[x] == true) {
                        pt = stack.pop();
                        x = (int) pt.getX();
                        y = (int) pt.getY();
                        rows[y] = true;
                        columns[x] = true;
                        diagonalRight[(size - 1) + (x - y)] = true;
                        diagonalLeft[(x + y)] = true;

                        if (y == size - 1) {
                            pt = stack.pop();
                            x = (int) pt.getX();
                            y = (int) pt.getY();
                            rows[y] = true;
                            columns[x] = true;
                            diagonalRight[(size - 1) + (x - y)] = true;
                            diagonalLeft[(x + y)] = true;
                        }
                    }

                }
            }

        }

        // Prints stack values.
        System.out.println(stack);

        //Prints a figure where X's is open spots and Q's are spots where the queen is.
        for (int i = 0; i < size; i++) {
            pt = stack.pop();
            for (int r = 0; r < size; r++) {
                if (r == (int) pt.getY()) {
                    System.out.print("Q");
                } else {
                    System.out.print("X");
                }
                if (r == size - 1) {
                    System.out.println("");
                }
            }
        }
    }
}
