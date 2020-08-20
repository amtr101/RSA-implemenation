import java.util.*; 

/*****************************
 * Amy Trevaskis: 15129275
 * a program that uses the 
 * Euclidian algorthmn to 
 * find the GCD of two 
 * positive integers
 * **************************/ 

public class euclidAlg
{
	public static void main(String [] args)
	{

		int a, b, gcd;   

	
		Scanner sc = new Scanner(System.in); 

		System.out.println("enter a"); 
		a = sc.nextInt(); 

		System.out.println("enter b"); 
		b = sc.nextInt(); 


		while(b >= a || b < 0)
		{
			System.out.println("b must be less than a and more than 0");     
			b = sc.nextInt();
		} 

		gcd = euclid(a, b); 

		System.out.println("the GCD of " + a +" and " + b + " is " + gcd); 

		if(gcd == 1)
		{
			System.out.println(a + " and " + b + " are coprime"); 
		} else
		{
			 System.out.println(a + " and " + b + " are not coprime");
		} 



	
	}  
	



/**************************
 * a recursive implementation of
 * the Euclidian algorthm
 * *****************************/ 

public static int euclid(int a, int b)
{
	int  gcd = 0;

	if(b == 0)
	{
		gcd = a; 	 
	}
	else 
	{
	  System.out.println("a is " + a);
          System.out.println("b is " + b);
          System.out.println("r is " + a % b);
          System.out.println("                 ");
	  gcd =	euclid(b, a % b); 

	}

	return gcd; 

}	



} 		
