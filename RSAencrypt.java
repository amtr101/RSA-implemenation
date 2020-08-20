import java.util.*; 
import java.io.*; 

/*****************************************
 * Amy Trevaskis: 15129275
 * FCC assignment 2: sem 1 2019
 * RSAencrypt: the encryption program 
 * using RSA. It reads in a file, puts 
 * each char through the encryption algorthm 
 * and writes out the encrypted message line
 * by line
 * ***************************************/

public class RSAencrypt
{
	public static void main(String[] args) throws IOException
	{
	
		int m; //message
		int c; //cypher text
		
		long p = prime(); //create prime numbers p and q
        	long q = prime();


		Scanner sc = new Scanner(System.in); 
		System.out.println("file name: "); 
		String filename = sc.next(); 

		FileReader readMsg = null; 
		FileWriter writeCy = null; 

		readMsg = new FileReader(filename); //read in file to encrpyt
		writeCy = new FileWriter("encrypted_file"); //write to file

		
		while((m = readMsg.read()) != -1)
		{
			c = encrypt(m, p, q); 
		        writeCy.write(c +"\n"); //c is written on new line
						//for easy read when decrypting	
		} 

	
		readMsg.close();  
		writeCy.close(); 


	}

/***********************************
* write n and d to a seperate 
 * file to be read in RSAdecryption
 * *********************************/ 

static void write_PrivateKey(int n, int d) throws IOException
{
	FileWriter write_key = null; 

	write_key = new FileWriter("private_key"); 

	write_key.write(n + "\n"); //n is always the first line
	write_key.write(d + "\n"); //d the second

	write_key.close(); 

} 


/************************************
 * the encryption algorthm. Takes in 
 * m (message) p and q and uses 
 * the power algorthm (modular exponentiation)
 * also calls write_PrivateKey to
 * write d and n to file to be used
 * by RSAdecryption
 * **********************************/ 

static int encrypt(int m, long p, long q)
{

	long c, n,e,t,d ;  

	n = p*q; 
	e = 65537; 

	t = (p - 1)*(q - 1); 
	
	d = modInverse((int)e, (int)t); //use extended euclidian algorthm

	
	try 
	{
  		write_PrivateKey((int)n, (int)d); //write n and do to file
	}
	catch(IOException x) 
	{
  		x.printStackTrace();
	}
	
	c = power((long)m, e, n);  //encrypt

	return (int)c; 
	
}   
 
/*********************************
 * the standard euclidian Algorthm
 * ********************************/ 

public static int euclid(int a, int b)
{
        int  gcd = 0;

        if(b == 0)
        {
                gcd = a;
        }
        else
        {
          gcd = euclid(b, a % b);
        }

        return gcd;

}


/*******************************
 * implementation of modular exponentiation
 * to calculate the encryption
 * ******************************/ 

static int power(long x, long y, long p) 
{ 
        long res = 1;      
        x = x % p;  
      
        while (y > 0) 
        { 
           
            if((y & 1)==1) 
                res = (res * x) % p; 
      
           
            y = y >> 1;  
            x = (x * x) % p;  
        } 
        
	return (int)res; 
} 


/**********************************************
 * prime: uses a for and while 
 * loop to determine if the randomly generated
 * number is prime. If it passes lehmanns
 * algorthm 100 times it is determined prime
 * *****************************************/  

static int prime()
{
	int r = 0; 	
        long a = 0;
      	long p = 0; 	
	boolean isPrime = false; 
	int primeCount = 0; 
	
	while(primeCount < 100) 
	{	                 
		primeCount = 0; //reset count 
		p = (long)(Math.random()*((10000-1000)+1))+1000; //generate p

	
		for(int i = 0; i < 100; i++) //run p through 100 times
		{
			a = (long)(Math.random()*((p - 1)+1))+1; //generate a

			r = power(a, ((p - 1) /2), p); //modular exponentiation

			if( r == 1 || r == (p - 1))
			{
				primeCount++; 
			}	 
		}	 

	} 
	
		return (int)p; 
}

/***************************
 * the extended euclidian algorthm
 * **************************/ 

static int modInverse(int a, int m)
{
        
	int m0 = m;
	int y = 0, x = 1;

        if (m == 1)
	{
		x = 0;
	}
        
	
	while (a > 1)
        {
         	int q = a / m;
		int t = m;
		m = a % m;
		a = t;
		t = y;

       
		y = x - q * y;
		x = t;
        }

        if (x < 0)
	{
		x += m0;
	} 

        return x;

} 


}  


