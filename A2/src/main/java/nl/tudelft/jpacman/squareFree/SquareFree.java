package nl.tudelft.jpacman.squareFree;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A standalone Java class, SquareFree, with the functionality
 * to take an integer n and return true if n is squarefree, and false otherwise
 *
 * @author Tong Shen
 */
public class SquareFree {

    static void main(String[]) {
        return;
    }

	public static void startSquareFree () {
		int input;
		boolean quit = false;
		Scanner scanner = new Scanner(System.in);
		
		while (!quit) {
			// Prompt for the user's name
		    System.out.print("Enter your integer (or \"quit\" to exit): ");
			
		    // Check if the scanner's next token is an int
		    if (scanner.hasNextInt()) {
		    	input = scanner.nextInt();
		    	
		    	if (input > 0) {
		    		if(isSquareFree(input)) {
		    			System.out.println("Yes, it is squarefree.");
		    		}
		    		else {
		    			System.out.println("No, it is not squarefree.");
		    		}
		    		
		    	}
		    	else {
		    		System.out.println("Input integer needs to be greater than 0");
		    	}
		    }
		    else if (scanner.next().equals("quit")) {
		    	quit = true;
		    }
		    else {
		    	System.out.println("INVALID INPUT");
		    }
		}

	}
	
    /**
     * Take an integer n and return true if n is squarefree, and false otherwise
     *
     * @param myInt
     *            The integer n
     */
    public static boolean isSquareFree(int myInt) {
    	int n = myInt;
    	int squared;

    	// Non-positive integers are not squarefree
    	if (n <= 0)
    	{
    		return false;
    	}
    
    	// By the Fundamental theorem of arithmetic, every positive integer
    	// greater than 1 can be written as a product of prime numbers.
    	// Therefore, if there exists a square of integer m (which is smaller than n)
    	// that divides the input integer n, then the square of one of the primes that
    	// make up integer m must also divide integer n.
    	
    	// Start with the smallest prime which is 2
        for (int i = 2; i < n; i++)
        {
            // See if i is a factor
            if (n % i == 0)
            {
                squared = i * i;
                
                // If it divides again, it must not be squarefree
                if (n % squared == 0)
                {
                	return false;
                }
            }
        }
        
        return true;
    }

}
