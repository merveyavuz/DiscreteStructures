/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bp3_project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 
 * 
 * PURPOSE OF THE PROGRAM: In this programming assignment, we want a number from user and then the program finds the consecutive positive
 * integers not exceeding a predefined number that are perfect powers. 
 */
public class PerfectPower {
    
     public static Integer[] findPerfectPowers(int maxVal){ //We define this method for find the perfect powers
        int maxm = (int)Math.ceil(Math.sqrt(maxVal));
        int maxk = (int)Math.ceil(Math.log(maxVal) / Math.log(2));
        Set<Integer> ppSet = new HashSet<>();
        for(int m = 2; m < maxm; m++){ // With this loop we turn the number that we take power
            for(int k = 2; k < maxk; k++){ // With this loop we turn the power number
                int perfectPower = (int)Math.pow(m,k); // Finds perfect power
                if(perfectPower >= maxVal) //If the number exceeds bound, loop ends.
                    break;
                ppSet.add(perfectPower); //We add the perfect powers to array
            }
        }
        Integer[] perfectPowers = ppSet.toArray(new Integer[ppSet.size()]);
        Arrays.sort(perfectPowers);
        return perfectPowers;
    }
    
    
    public static ArrayList<Integer[]> findConsecutives(Integer[] perfectPowers){ // We define this method for find the consecutive numbers
        ArrayList<Integer[]> consecutives = new ArrayList<>(2); 
        for(int i = 0; i < perfectPowers.length-1; i++){ 
            if(perfectPowers[i]+1 == perfectPowers[i+1]) //We control if the numbers are consecutive
                consecutives.add(new Integer[]{perfectPowers[i], perfectPowers[i+1]}); //We add consecutive numbers to array.
        }
        return consecutives;
    }
    
    
    public static void printResult(int maxVal, boolean printPerfectPowers){
        Integer[] pps = findPerfectPowers(maxVal); // Assign the perfect powers to integer array pps
        if(printPerfectPowers){ 
            System.out.println("Perfect powers not exceeding " + maxVal + ":"); // That prints the number that user gave
            for(int i : pps){
                System.out.print(i + " "); //That prints the numbers not exceeding the value that user gave
            }
            System.out.println();
        }
        ArrayList<Integer[]> consecutives = findConsecutives(pps);
        System.out.println("Consecutive integers:");
        for(int i = 0; i < consecutives.size(); i++){   //We turn this loop until the size of array to pick the pair of consetive numbers.
            Integer[] consecutivePair = consecutives.get(i);
            System.out.print('(');
            for(int pp : consecutivePair)// With this for loop we print the consecutive numbers
                System.out.print(pp + ",");
            System.out.print("\b) ");
        }
    }
    
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        System.out.println("Enter a number: "); // We want a number from user
        int maxval = scanner.nextInt(); // The number that user entered is our max value 
        printResult(maxval, true); // We print result.
    }
    
    
    
}
