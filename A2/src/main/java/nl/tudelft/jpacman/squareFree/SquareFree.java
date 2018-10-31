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

    public static void main(String[] args) {
        SquareFree sf = new SquareFree();
        sf.isSquareFree(7);
    }

	public static void startSquareFree () {
        SquareFree sf = new SquareFree();
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
		    		if(sf.isSquareFree(input)) {
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
    public boolean isSquareFree(int myInt) {
    	// Non-positive integers are not squarefree
    	if (myInt <= 0)
    	{
    		return false;
    	}
    
    	// By the Fundamental theorem of arithmetic, every positive integer
    	// greater than 1 can be written as a product of prime numbers.
    	// Therefore, if there exists a square of integer m (which is smaller than n)
    	// that divides the input integer n, then the square of one of the primes that
    	// make up integer m must also divide integer n.
    	
    	// Start with the smallest prime which is 2
        for (int i = 2; i < myInt; i++)
        {
            int curPrime = i;
            int n = myInt;
            int squared = 0;

            // Get squared value
            while (curPrime > 0) {
                squared += i;
                curPrime -= 1;
            }

            // Determine divisibility
            while (n >= 0) {
                if (n == 0) {
                    return false;
                }

                n -= squared;
            }

        }
        
        return true;
    }

}
