/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package discretestructureshw2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * 
 * 
 * PURPOSE OF THE PROGRAM: In this program we determine if a function is one to
 * one, onto, bijection and if function is bijection we determine the inverse.
 * f(x)=x+1 is one to one, onto and bijection. 
 * f(x)= x^3 is one to one, not onto, not bijection
 * f(x)= |x| is onto, not one to one, not bijection
 */
public class Test {

    public static int f(int x) { 
//        return x + 1;
//        return (int) Math.pow(x, 3);
        return Math.abs(x);
    }

    public static void main(String[] args) {
        int[] domain = new int[5];
        int relation[][] = new int[5][2];

        for (int i = -2; i <= 2; i++) {  // [-2, 2] constant. This loop add the domain to array 
            domain[i - (-2)] = i;
        }
        for (int i = 0; i < domain.length; i++) { // This loop finds codomain with f function and adds to 2D array
            relation[i][0] = domain[i];
            relation[i][1] = f(domain[i]);
        }
        
        
        System.out.println("is one to one :" + isOneToOne(relation));
        System.out.println("is onto: " + isOnto(relation));
        System.out.println("is bijection: " + isBijection(relation));

        System.out.println(Arrays.deepToString(inverse(relation))); //Takes 2D array and print
    }
    
    public static boolean isOneToOne(int[][] relation) {
        boolean isonetoone = false;

        int[] codomain;
        codomain = new int[5];
        for (int i = 0; i < relation.length; i++) { //Finds codomain
            codomain[i] = relation[i][1];
        }
        int repetition = 0; 
        for (int i = 0; i < codomain.length; i++) { //If an element repeats in this loop function can not be one to one
            for (int j = i + 1; j < codomain.length; j++) {
                if (codomain[i] == (codomain[j])) {
                    repetition++;
                }
            }
        }

        if (repetition == 0) { //Finds that if an element repeats itself or not. So determines. 
            isonetoone = true;
        } else {
            isonetoone = false;
        }

        return isonetoone;
    }
    
    public static boolean isOnto(int[][] relation) {
        boolean isonto = false;

        //Onto function means the codomain does not have a empty element.
        Set<Integer> sequentialArray = new HashSet<>();
        for (int i = 0; i < relation.length; i++) {
            sequentialArray.add(relation[i][1]);
        }

        //In below, it finds the interval of the codomain. It finds min and max to determine the interval
        Integer[] codomain = sequentialArray.toArray(new Integer[sequentialArray.size()]);
        Arrays.sort(codomain);
        int max = codomain[0];
        int min = codomain[0];

        for (int i = 0; i < codomain.length; i++) {
            if (max < codomain[i]) {
                max = codomain[i];
            }

            if (min > codomain[i]) {
                min = codomain[i];
            }
        }

        int[] interval = new int[max - min + 1];
        for (int i = min; i <= max; i++) { //Adds interval to an array so we can determine
            interval[i - (min)] = i;
        }

        Arrays.sort(codomain);
        Arrays.sort(interval);

        // Arrays.equal method cannot compare Object and integer so this loop added it to int array
        int[] newArray = new int[codomain.length]; 
        for (int i = 0; i < codomain.length; i++) { 
            newArray[i] = (int) codomain[i];
        }
        if (Arrays.equals(newArray, interval)) { //It determines if arrays is equal or not.
            isonto = true;
        } else {
            isonto = false;
        }
        return isonto;

    }
    
    public static boolean isBijection(int[][] relation) {
        boolean isBijection;

        //For bijection a function must be one to one and onto
        if (isOneToOne(relation) == true && isOnto(relation) == true) {
            isBijection = true;
        } else {
            isBijection = false;
        }
        return isBijection;
    }

    public static int[][] inverse(int[][] relation) {

        if (isBijection(relation) == true) { // If a function is not bijection it returns null.
            int inverseArray[][] = new int[5][2];

            int[] domain = new int[5];
            for (int i = -2; i <= 2; i++) {
                domain[i - (-2)] = i;
            }
            for (int i = 0; i < domain.length; i++) { // Changes relation and finds inverse
                inverseArray[i][0] = f(domain[i]);
                inverseArray[i][1] = domain[i];
            }
            return inverseArray;
        }
        else{
            return null;
        }
    }
}
