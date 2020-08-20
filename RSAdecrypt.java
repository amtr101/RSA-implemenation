import java.util.*;
import java.io.*;
/*****************************************
 * Amy Trevaskis: 15129275
 * FCC assignment 2: sem 1 2019
 * RSAdecrypt: the decryption program 
 * for RSA. It takes the encrypted 
 * file from RSAencryption and decrypts it
 * using the PrivateKey object (created from
 * a seperate file containing n and d written
 * at the initial encryption) 
 * ***************************************/ 

public class RSAdecrypt
{
        public static void main(String[] args)
        {

                int m;  //message
                int c;  //cyphertext
		
		PrivateKey key; 

		key = readKey(); 

		//input filename from the terminal 
                Scanner sc = new Scanner(System.in);
                System.out.println("file name:");
                String filename = sc.next(); 

 		BufferedReader readCy; 
		FileWriter write_decrypt; 

		try
		{ 
			readCy = new BufferedReader(new FileReader(filename)); 
			write_decrypt = new FileWriter("decrypted_file"); 
			String line = readCy.readLine(); 

			//read the encrypted file line by line 

			//write the decrpyted file char by char
			while(line != null)
			{
				c = Integer.parseInt(line); 
				m = decrypt(key, c);  
				write_decrypt.write((char)m); 

				line = 	readCy.readLine(); 
	 		} 		

			readCy.close(); 
			write_decrypt.close(); 

		} 
		catch(IOException e)
		{
			e.printStackTrace();
		} 

	}	


/*****************************
 * the decrpytion algorthm: 
 * takes n and d from the private key
 * and implements the Modular exponentiation 
 * same as the encryption algorthm
 * ********************************/ 

static int decrypt(PrivateKey key, int c)
{
	long n, d, m;  

	n = (long)key.getN(); 
	d = (long)key.getD(); 

	m = power((long)c, d, n); 

	return(int)m; 
} 


/*******************************
 * power: Modular exponentiation 
 * algorthm
 * *****************************/ 

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

/*********************************
 * readKey: read the key file and
 * creates the PrivateKey object 
 * to be used in the decryption
 * *****************************/ 

static PrivateKey readKey() 
{
	 
	BufferedReader readk;

        PrivateKey key = new PrivateKey();         
	int n, d; 
	
	  try{
		readk = new BufferedReader(new FileReader("private_key"));
					    
		String line = readk.readLine(); //read n and d by line
		n = Integer.parseInt(line); //convert string to int
		line =  readk.readLine();
		d = Integer.parseInt(line);
		
		readk.close();

		key.setN(n); 
		key.setD(d); 	

	   }
  	   catch(IOException e)
           {
		e.printStackTrace();
           }
	
	return key; 


} 





}
