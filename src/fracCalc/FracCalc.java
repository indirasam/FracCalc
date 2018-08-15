package fracCalc;

import java.util.Scanner;

public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter an equation to solve");
    	String input = sc.nextLine();
    	while(!input.equalsIgnoreCase("quit")) {
    		System.out.println(produceAnswer(input));
    		System.out.println("Enter an equation to solve (quit to exit)");
    		input = sc.nextLine();
    	}
    	sc.close();
    	System.out.println("Program complete");
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        // split the input into its parts - operands and operator
    	int firstSpace = input.indexOf(" ");
    	int secondSpace = input.indexOf(" ", firstSpace + 1);
    	
    	// make sure we found both spaces
    	if(firstSpace < 0 || secondSpace < 0) {
    		return "***ERROR: Input was not in correct format (input = " + input + ")";
    	}
    	
    	String op1 = input.substring(0, firstSpace);
    	String op2 = input.substring(secondSpace + 1);
    	String operator = input.substring(firstSpace + 1, secondSpace);
    	
    	int w1 = getWholeNumber(op1);
    	int n1 = getNumerator(op1);
    	int d1 = getDenominator(op1);
    	int w2 = getWholeNumber(op2);
    	int n2 = getNumerator(op2);
    	int d2 = getDenominator(op2);
    	
        //return "whole:" + w2 + " numerator:" + n2 + " denominator:" + d2;
        return "LCM = " + leastCommonMultiple(d1, d2) + ", GCD = " + greatestCommonDivisor(d1, d2);
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static int getWholeNumber(String input) {
    	// look for an underscore and slash
    	int underscore = input.indexOf("_");
    	int slash = input.indexOf("/");
    	
    	// no slash and no underscore means it's just a whole number
    	if(underscore < 0 && slash < 0) return Integer.parseInt(input);
    	
    	// no underscore with slash means it's all fraction
    	if(underscore < 0 && slash >= 0) return 0;
    	
    	// whole number is portion before underscore
    	return Integer.parseInt(input.substring(0, underscore));
    }
    
    public static int getNumerator(String input) {
    	// look for an underscore and slash
    	int underscore = input.indexOf("_");
    	int slash = input.indexOf("/");

    	// no slash and no underscore means it's just a whole number
    	if(underscore < 0 && slash < 0) return 0; 
    	
    	// numerator is portion after underscore and before slash
    	return Integer.parseInt(input.substring(underscore + 1, slash));
    }
    
    public static int getDenominator(String input) {
    	// look for slash
    	int slash = input.indexOf("/");
    	
    	// no slash means it's just a whole number
    	if(slash < 0) return 1;
    	
    	// denominator is portion after slash
    	return Integer.parseInt(input.substring(slash + 1));
    }
    
    public static int greatestCommonDivisor(int n1, int n2) {
    	if(n2 == 0) return n1;
    	return greatestCommonDivisor(n2, n1 % n2);
    }
    
    public static int leastCommonMultiple(int n1, int n2) {
    	return Math.abs(n1 * n2) / greatestCommonDivisor(n1, n2);
    }
}
